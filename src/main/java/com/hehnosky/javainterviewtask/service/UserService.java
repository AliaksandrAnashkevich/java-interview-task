package com.hehnosky.javainterviewtask.service;

import com.hehnosky.javainterviewtask.dto.request.UserRequest;
import com.hehnosky.javainterviewtask.dto.response.UserResponse;
import com.hehnosky.javainterviewtask.exception.UserNotFoundException;
import com.hehnosky.javainterviewtask.mapper.UserMapper;
import com.hehnosky.javainterviewtask.model.DocumentType;
import com.hehnosky.javainterviewtask.model.User;
import com.hehnosky.javainterviewtask.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toResponse)
            .toList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setDocumentType(DocumentType.getByName(userRequest.getDocumentType()));

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        user = userMapper.toEntity(userRequest, user);
        user.setDocumentType(DocumentType.getByName(userRequest.getDocumentType()));

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }
}
