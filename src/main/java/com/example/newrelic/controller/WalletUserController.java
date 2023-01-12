package com.example.newrelic.controller;

import com.example.newrelic.domain.model.DTO.DepositDTO;
import com.example.newrelic.domain.model.DTO.TransferDTO;
import com.example.newrelic.domain.model.Extract;
import com.example.newrelic.domain.service.impl.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/wallet")
public class WalletUserController {

    @Autowired
    private WalletServiceImpl walletService;

    @PostMapping("/deposit")
    public ResponseEntity<Extract> deposit(@RequestBody DepositDTO depositDTO) {
        Extract extract = walletService.deposit(depositDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(extract);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Extract> transfer(@RequestBody TransferDTO transferDTO) {
        Extract extract = walletService.transfer(transferDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(extract);
    }

    @GetMapping("/extract/{userId}")
    public ResponseEntity<List<Extract>> extract(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(walletService.getExtract(userId));
    }
}
