package com.redmath.Bank.App.Balance;

import com.redmath.Bank.App.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents the balance for an account holder.
 */
@Entity(name = "Account")
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
  @JoinColumn(name = "accountHolder", nullable = false)
    private User accountHolder;

    private Double amount;
    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
  }

    /**
     * Sets the amount.
     *
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the account holder.
     *
     * @return the account holder
     */
    public User getAccountHolder() {
        return accountHolder;
    }

    /**
     * Sets the account holder.
     *
     * @param accountHolder the account holder to set
     */
    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }
}
