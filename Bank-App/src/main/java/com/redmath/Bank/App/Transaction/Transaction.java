package com.redmath.Bank.App.Transaction;

import com.redmath.Bank.App.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "sender",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_SENDER", foreignKeyDefinition = "FOREIGN KEY (sender) REFERENCES user(id) ON DELETE SET NULL")
    )
    private User sender;

    @ManyToOne
    @JoinColumn(
            name = "receiver",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_RECEIVER", foreignKeyDefinition = "FOREIGN KEY (receiver) REFERENCES user(id) ON DELETE SET NULL")
    )
    private User receiver;

    private Double amount;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
