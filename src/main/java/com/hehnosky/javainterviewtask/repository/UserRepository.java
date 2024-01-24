package com.hehnosky.javainterviewtask.repository;

import com.hehnosky.javainterviewtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
