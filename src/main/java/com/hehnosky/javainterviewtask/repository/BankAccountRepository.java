package com.hehnosky.javainterviewtask.repository;

import com.hehnosky.javainterviewtask.model.BankAccount;
import com.hehnosky.javainterviewtask.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findAllByUserId(Long userId);
}
