package com.hehnosky.javainterviewtask.service;

import com.hehnosky.javainterviewtask.dto.request.OperationRequest;
import com.hehnosky.javainterviewtask.dto.response.OperationResponse;
import com.hehnosky.javainterviewtask.exception.BankAccountNotFoundException;
import com.hehnosky.javainterviewtask.exception.MoneyFormatException;
import com.hehnosky.javainterviewtask.exception.OperationNotFoundException;
import com.hehnosky.javainterviewtask.mapper.OperationMapper;
import com.hehnosky.javainterviewtask.model.BankAccount;
import com.hehnosky.javainterviewtask.model.Operation;
import com.hehnosky.javainterviewtask.model.OperationStatus;
import com.hehnosky.javainterviewtask.model.OperationType;
import com.hehnosky.javainterviewtask.repository.BankAccountRepository;
import com.hehnosky.javainterviewtask.repository.OperationRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final BankAccountRepository bankAccountRepository;

    @Transactional(readOnly = true)
    public OperationResponse findById(Long id) {
        Operation operation = operationRepository.findById(id)
            .orElseThrow(() -> new OperationNotFoundException(id));
        return operationMapper.toResponse(operation);
    }

    @Transactional(readOnly = true)
    public List<OperationResponse> findAll() {
        return operationRepository.findAll().stream()
            .map(operationMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<OperationResponse> findAllByBankAccountId(Long bankAccountId) {
        return operationRepository.findAllByAccountId(bankAccountId).stream()
            .map(operationMapper::toResponse)
            .toList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public OperationResponse payment(OperationRequest operationRequest) {
        paymentScaleValidation(operationRequest.getPayment());

        OperationType operationType = OperationType.getByName(operationRequest.getType());
        BankAccount bankAccount = bankAccountRepository.findById(operationRequest.getBankAccount())
            .orElseThrow(() -> new BankAccountNotFoundException(operationRequest.getBankAccount()));

        Operation operation;
        BigDecimal amount = bankAccount.getAmount();
        if ((OperationType.SUBTRACT.equals(operationType)) && (amount.compareTo(operationRequest.getPayment()) < 0)) {
            operation = createOperation(bankAccount, operationRequest, OperationStatus.FAILURE, operationType);
        } else {
            amount = payOperation(operationRequest.getPayment(), amount, operationType);
            bankAccount.setAmount(amount);
            bankAccountRepository.save(bankAccount);
            operation = createOperation(bankAccount, operationRequest, OperationStatus.SUCCESS, operationType);
        }
        operationRepository.save(operation);
        return operationMapper.toResponse(operation);
    }

    private Operation createOperation(BankAccount bankAccount, OperationRequest operationRequest,
        OperationStatus status, OperationType type) {
        Operation operation = new Operation();
        operation.setAmountPayment(operationRequest.getPayment());
        operation.setAmountAfter(bankAccount.getAmount());
        operation.setDateTime(LocalDateTime.now());
        operation.setAccount(bankAccount);
        operation.setStatus(status);
        operation.setType(type);
        return operation;
    }

    private void paymentScaleValidation(BigDecimal payment) {
        if (payment.scale() > 2) {
            throw new MoneyFormatException();
        }
    }

    private BigDecimal payOperation(BigDecimal amountPayment, BigDecimal amountAccount, OperationType type) {
        BigDecimal amount;
        if (type.equals(OperationType.ADD)) {
            amount = amountAccount.add(amountPayment);
        } else {
            amount = amountAccount.subtract(amountPayment);
        }
        return amount;
    }
}
