package com.example.newrelic.domain.service.impl;

import com.example.newrelic.domain.exception.EntityNotFoundException;
import com.example.newrelic.domain.exception.TransactionBadRequestException;
import com.example.newrelic.domain.exception.TransferBadRequestException;
import com.example.newrelic.domain.model.DTO.DepositDTO;
import com.example.newrelic.domain.model.DTO.TransferDTO;
import com.example.newrelic.domain.model.Extract;
import com.example.newrelic.domain.model.User;
import com.example.newrelic.domain.model.enums.TransactionType;
import com.example.newrelic.domain.repository.ExtractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class WalletServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ExtractRepository extractRepository;

    @Value("${mock-url}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    public Extract deposit(DepositDTO depositDTO){
        logger.info("Start deposit on AG " + depositDTO.getAgToDeposit() + " and ACC " + depositDTO.getAccToDeposit());
        if(depositDTO.getValue().compareTo(BigDecimal.ZERO) < 0){
            logger.error("Error on deposit of AG" + depositDTO.getAgToDeposit() + " and ACC " + depositDTO.getAccToDeposit());
            throw new TransactionBadRequestException("value cannot be negative");
        }

        User userReceive = userService.findUserByAgAndAcc(depositDTO.getAgToDeposit(), depositDTO.getAccToDeposit())
                .orElseThrow(() -> new EntityNotFoundException());
        userReceive.setBalance(userReceive.getBalance().add(depositDTO.getValue()));
        logger.info("Finalizing deposit on AG" + depositDTO.getAgToDeposit() + " and ACC " + depositDTO.getAccToDeposit());
        return extractRepository.save(createExtract(TransactionType.DEPOSIT, depositDTO.getValue(), userReceive, userReceive));
    }

    public Extract transfer(TransferDTO transferDTO){
        User userDepositor = userService.findUserByCpfCNPJ(transferDTO.getCpfCnpjDepositorUser())
                .orElseThrow(() -> new EntityNotFoundException());
        User userReceive = userService.findUserByCpfCNPJ(transferDTO.getCpfCnpjReceiverUser())
                .orElseThrow(() -> new EntityNotFoundException());

        logger.info("Start transfer from" + transferDTO.getCpfCnpjDepositorUser() + " to " + transferDTO.getCpfCnpjReceiverUser());

        if(userDepositor.getBalance().compareTo(transferDTO.getValue()) < 0){
            logger.error("Error on transfer, insufficient balance");
            throw new TransactionBadRequestException("insufficient balance");
        }
        if(!restTemplate.getForEntity(url, String.class).getStatusCode().is2xxSuccessful()){
            logger.error("Service temporarily unavailable");
            throw new TransferBadRequestException();
        }

        userDepositor.setBalance(userDepositor.getBalance().subtract(transferDTO.getValue()));
        userReceive.setBalance(userReceive.getBalance().add(transferDTO.getValue()));
        logger.info("Finalizing transfer from" + transferDTO.getCpfCnpjDepositorUser() + " to " + transferDTO.getCpfCnpjReceiverUser());
        return extractRepository.save(createExtract(TransactionType.TRANSFER, transferDTO.getValue(), userReceive, userDepositor));
    }

    public List<Extract> getExtract(long userId){
        User user = userService.find(userId);
        return extractRepository.findByUserReceiverId(user);
    }

    private Extract createExtract(TransactionType transactionType, BigDecimal value, User userReceive, User userDepositor){
        logger.info("Creating extract of" + transactionType + " of " + userReceive);
        Extract extract = new Extract();
        extract.setTransactionType(transactionType);
        extract.setValue(value);
        extract.setUserReceiverId(userReceive);
        extract.setUserDepositorId(userDepositor);
        userReceive.setExtractList(Collections.singletonList(extract));
        return extract;
    }
}