package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {

	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/userRegister")
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
		var userModel = new UserModel();
		BeanUtils.copyProperties(userRecordDto, userModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
		
	}
	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> allUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());	
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> userUpdate( @PathVariable(value = "id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto){
		Optional<UserModel> userO = userRepository.findById(id);
		if(userO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não encontrado");
			
		}
		var userModel = userO.get();
		BeanUtils.copyProperties(userRecordDto,userModel);
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
	}
	
	
}
