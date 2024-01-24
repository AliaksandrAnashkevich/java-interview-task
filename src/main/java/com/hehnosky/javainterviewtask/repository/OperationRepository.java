package com.hehnosky.javainterviewtask.repository;

import com.hehnosky.javainterviewtask.model.Operation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByAccountId(Long bankAccountId);
}
