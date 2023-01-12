package com.example.newrelic.domain.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferDTO {
    @JsonProperty("cpf_cnpj_receiver_user")
    private String cpfCnpjReceiverUser;

    @JsonProperty("cpf_cnpj_depositor_user")
    private String cpfCnpjDepositorUser;

    private BigDecimal value;
}
