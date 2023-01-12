package com.example.newrelic.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(name = "cpf_cnpj", unique = true)
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @Column(unique = true, nullable = false)
    private String phone;

    private String password;

    private String pin;

    private BigDecimal balance;

    private String ag;

    private String acc;

    @OneToMany(mappedBy = "userReceiverId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Extract> extractList;

}
