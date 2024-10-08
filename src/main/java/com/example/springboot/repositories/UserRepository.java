package com.example.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
