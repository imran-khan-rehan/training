package com.redmath.Bank.App.Transaction;

import com.redmath.Bank.App.Balance.Balance;
import com.redmath.Bank.App.Balance.BalanceRepository;
import com.redmath.Bank.App.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserService userService;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> allTransactionUser(Long userId) {
        return transactionRepository.findBySenderIdOrReceiverId(userId, userId);
    }

    public boolean checkUserExist(Long userId) {
        return userService.findUser(userId).getId() == null;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // Update sender's balance
        Balance senderBalance = balanceRepository.findByAccountHolder(transaction.getSender())
                .orElseThrow(() -> new RuntimeException("Sender balance not found"));

        if (senderBalance.getAmount() < transaction.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        senderBalance.setAmount(senderBalance.getAmount() - transaction.getAmount());
        balanceRepository.save(senderBalance);

        // Update receiver's balance
        Balance receiverBalance = balanceRepository.findByAccountHolder(transaction.getReceiver())
                .orElseGet(() -> {
                    Balance newBalance = new Balance();
                    newBalance.setAccountHolder(transaction.getReceiver());
                    newBalance.setAmount(0.0);
                    return newBalance;
                });

        receiverBalance.setAmount(receiverBalance.getAmount() + transaction.getAmount());
        balanceRepository.save(receiverBalance);

        // Save transaction
        return transactionRepository.save(transaction);
    }
}
