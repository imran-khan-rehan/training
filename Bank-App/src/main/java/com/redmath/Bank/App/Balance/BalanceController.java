package com.redmath.Bank.App.Balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/balance")
@RestController
public class BalanceController {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/all")
    public ResponseEntity<List<Balance>> getAll() {
        return ResponseEntity.ok(balanceService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Balance> checkAmount(@PathVariable Long userId) {
        Balance balance = balanceService.checkAmount(userId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/add")
    public ResponseEntity<Balance> addAmount(@RequestBody Map<String, Object> request) {
        Long userId = ((Number) request.get("userId")).longValue();
        Double amount = ((Number) request.get("amount")).doubleValue();
        Balance balance = balanceService.addAmount(userId, amount);
        return ResponseEntity.ok(balance);
    }
}
