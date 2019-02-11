package com.bnov.bfb.bfbcore.dao;

import com.bnov.bfb.bfbcore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
