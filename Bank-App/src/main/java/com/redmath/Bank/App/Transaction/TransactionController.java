package com.redmath.Bank.App.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {
        boolean existingUser = transactionService.checkUserExist(transaction.getReceiver().getId());
        if (!existingUser) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Transaction saved = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionUser(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.allTransactionUser(userId);
        return ResponseEntity.ok(transactions);
    }
}
