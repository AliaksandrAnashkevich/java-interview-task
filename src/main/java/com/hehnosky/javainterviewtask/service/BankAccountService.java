package com.hehnosky.javainterviewtask.service;

import com.hehnosky.javainterviewtask.dto.request.BankAccountRequest;
import com.hehnosky.javainterviewtask.dto.response.BankAccountResponse;
import com.hehnosky.javainterviewtask.exception.BankAccountNotFoundException;
import com.hehnosky.javainterviewtask.exception.MoneyFormatException;
import com.hehnosky.javainterviewtask.exception.UserNotFoundException;
import com.hehnosky.javainterviewtask.mapper.BankAccountMapper;
import com.hehnosky.javainterviewtask.model.BankAccount;
import com.hehnosky.javainterviewtask.model.User;
import com.hehnosky.javainterviewtask.repository.BankAccountRepository;
import com.hehnosky.javainterviewtask.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountMapper bankAccountMapper;
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public BankAccountResponse findById(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
            .orElseThrow(() -> new BankAccountNotFoundException(bankAccountId));
        return bankAccountMapper.toResponse(bankAccount);
    }

    @Transactional(readOnly = true)
    public List<BankAccountResponse> findAll() {
        return bankAccountRepository.findAll().stream()
            .map(bankAccountMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<BankAccountResponse> findAllByUserId(Long userId) {
        return bankAccountRepository.findAllByUserId(userId).stream()
            .map(bankAccountMapper::toResponse)
            .toList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public BankAccountResponse create(BankAccountRequest bankAccountRequest) {
        User user = userRepository.findById(bankAccountRequest.getUserId())
            .orElseThrow(() -> new UserNotFoundException(bankAccountRequest.getUserId()));
        BankAccount bankAccount = createNewBankAccount(user);
        bankAccountRepository.save(bankAccount);
        return bankAccountMapper.toResponse(bankAccount);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public BankAccountResponse update(Long id, BankAccountRequest bankAccountRequest) {
        paymentScaleValidation(bankAccountRequest.getAmount());
        BankAccount bankAccount = bankAccountRepository.findById(id)
            .orElseThrow(() -> new BankAccountNotFoundException(id));
        bankAccount.setAmount(bankAccountRequest.getAmount());
        bankAccountRepository.save(bankAccount);
        return bankAccountMapper.toResponse(bankAccount);
    }

    @Transactional
    public void delete(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
            .orElseThrow(() -> new BankAccountNotFoundException(id));
        bankAccountRepository.delete(bankAccount);
    }

    private void paymentScaleValidation(BigDecimal payment) {
        if (payment.scale() > 2) {
            throw new MoneyFormatException();
        }
    }

    private BankAccount createNewBankAccount(User user) {
        BankAccount bankAccount = new BankAccount();

        String code = user.getDocumentNumber()
            .concat("_")
            .concat(String.valueOf(user.getBankAccounts().size() + 1));
        bankAccount.setAmount(new BigDecimal("0.00"));
        bankAccount.setCode(code);
        bankAccount.setUser(user);
        return bankAccount;
    }
}
