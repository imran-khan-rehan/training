package com.redmath.Bank.App.Balance;

import com.redmath.Bank.App.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByAccountHolder(User user);
    Optional<Balance> findByAccountHolderId(Long userId);
}
