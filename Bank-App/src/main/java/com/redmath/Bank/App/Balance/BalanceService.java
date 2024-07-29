package com.redmath.Bank.App.Balance;

import com.redmath.Bank.App.Transaction.Transaction;
import com.redmath.Bank.App.Transaction.TransactionRepository;
import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Balance> getAll() {
        return balanceRepository.findAll();
    }

    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    public Balance checkAmount(Long userId) {
        return balanceRepository.findByAccountHolderId(userId)
                .orElseThrow(() -> new RuntimeException("no account"));
    }

    public Balance addAmount(Long userId, Double amount) {
        Balance balance = balanceRepository.findByAccountHolderId(userId).orElseGet(() -> {
            Balance newBalance = new Balance();
            newBalance.setAccountHolder(new User(userId));
            newBalance.setAmount(0.0);
            return newBalance;
        });
        balance.setAmount(balance.getAmount() + amount);
        balanceRepository.save(balance);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("no account"));
        Transaction transaction = new Transaction();
        transaction.setSender(user);
        transaction.setReceiver(user);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        return balance;
    }

    public Balance createBalanceForUser(String email, Double initialAmount) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Balance balance = new Balance();
        balance.setAccountHolder(user);
        balance.setAmount(initialAmount);
        return balanceRepository.save(balance);
    }
}
