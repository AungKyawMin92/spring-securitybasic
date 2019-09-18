package com.boot.oauth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.oauth.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByUserName(String userName);
}
