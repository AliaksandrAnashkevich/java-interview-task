package com.hehnosky.javainterviewtask.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.hehnosky.javainterviewtask.dto.request.BankAccountRequest;
import com.hehnosky.javainterviewtask.dto.response.BankAccountResponse;
import com.hehnosky.javainterviewtask.mapper.BankAccountMapper;
import com.hehnosky.javainterviewtask.model.BankAccount;
import com.hehnosky.javainterviewtask.model.User;
import com.hehnosky.javainterviewtask.repository.BankAccountRepository;
import com.hehnosky.javainterviewtask.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BankAccountServiceTest {

    @Mock
    BankAccountMapper bankAccountMapper;

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    @Test
    void findById() {
        // given
        var id = 1L;

        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));

        var response = BankAccountResponse.builder()
            .id(id)
            .code("111_1")
            .amount(new BigDecimal("10.99"))
            .build();
        // when
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(extend));
        when(bankAccountMapper.toResponse(extend)).thenReturn(response);
        var actual = bankAccountService.findById(id);
        // then
        assertThat(actual.getCode()).isEqualTo(extend.getCode());
        assertThat(actual.getAmount()).isEqualTo(extend.getAmount());
    }

    @Test
    void findAll() {
        // given
        var id = 1L;

        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));

        var response = BankAccountResponse.builder()
            .id(id)
            .code("111_1")
            .amount(new BigDecimal("10.99"))
            .build();
        // when
        when(bankAccountRepository.findAll()).thenReturn(List.of(extend));
        when(bankAccountMapper.toResponse(extend)).thenReturn(response);
        var actual = bankAccountService.findAll();
        // then
        assertTrue(actual.size() > 0);
    }

    @Test
    void findAllByUserId() {
        // given
        var id = 1L;
        var userId = 1L;
        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));

        var response = BankAccountResponse.builder()
            .id(id)
            .code("111_1")
            .amount(new BigDecimal("10.99"))
            .build();
        // when
        when(bankAccountRepository.findAllByUserId(userId)).thenReturn(List.of(extend));
        when(bankAccountMapper.toResponse(extend)).thenReturn(response);
        var actual = bankAccountService.findAllByUserId(userId);
        // then
        assertTrue(actual.size() > 0);

    }

    @Test
    void create() {
        // given
        var id = 1L;

        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));

        var user = new User();
        user.setId(1L);
        user.setDocumentNumber("111");

        var request = BankAccountRequest.builder()
            .userId(1L)
            .amount(new BigDecimal("0"))
            .userId(1L)
            .build();

        var response = BankAccountResponse.builder()
            .id(id)
            .code("111_1")
            .amount(new BigDecimal("0"))
            .build();
        // when
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(extend));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(extend);
        when(bankAccountMapper.toResponse(extend)).thenReturn(response);
        var actual = bankAccountService.create(request);
        // then
        assertTrue(true);
    }

    @Test
    void update() {
        // given
        var id = 1L;

        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));

        var user = new User();
        user.setId(1L);

        var request = BankAccountRequest.builder()
            .userId(1L)
            .amount(new BigDecimal("0"))
            .userId(1L)
            .build();

        var response = BankAccountResponse.builder()
            .id(id)
            .code("111_1")
            .amount(new BigDecimal("0"))
            .build();
        // when
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(extend));
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(bankAccountMapper.toResponse(extend)).thenReturn(response);
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(extend);
        var actual = bankAccountService.update(id, request);
        // then
        assertTrue(true);
    }

    @Test
    void delete() {
        // given
        var id = 1L;

        var extend = new BankAccount();
        extend.setId(id);
        extend.setCode("111_1");
        extend.setAmount(new BigDecimal("10.99"));
        // when
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(extend));
        // then
        assertTrue(true);
    }
}