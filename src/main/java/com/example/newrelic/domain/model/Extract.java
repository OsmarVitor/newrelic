package com.example.newrelic.domain.model;

import com.example.newrelic.domain.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(nullable = false)
    //@Column(name = "user_depositor_id")
    private User userDepositorId;

    @ManyToOne
    //@Column(name = "user_receiver_id")
    private User userReceiverId;

    //    @ManyToMany
//    @JoinTable(name = "transfer_users_id",
//            joinColumns = @JoinColumn(name = "send_user_id"),
//            inverseJoinColumns = @JoinColumn(name = "receive_user_id"))
//   private List<User> users = new ArrayList<>();
}
