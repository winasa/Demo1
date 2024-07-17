package com.demotruper.demo.controller;

import com.demotruper.demo.dto.LoginRequest;
import com.demotruper.demo.dto.UserDTO;
import com.demotruper.demo.entity.UserEntity;
import com.demotruper.demo.jwt.AuthResponse;
import com.demotruper.demo.jwt.AuthService;
import com.demotruper.demo.jwt.RegisterRequest;
import com.demotruper.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class controller {

    private UserService userService;

    private final AuthService authService;


    public controller(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/getUsers")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return new  ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDTO userDTO){
        return new  ResponseEntity<>(userService.saveUser(userDTO),HttpStatus.OK);
    }
    @PostMapping("/getUsersById")
    public ResponseEntity<UserEntity> getUsersById(@RequestBody UserDTO userDTO){
        return new  ResponseEntity<UserEntity>(userService.getUsersById(userDTO.getId()).get(),HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return new  ResponseEntity<UserDTO>(userService.updateUserEntity(userDTO),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Integer id){
        userService.deleteUserById(id);
        return new  ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping("hello")
    public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello "+name+"!!";
    }
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login    (request));
    }
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

}
