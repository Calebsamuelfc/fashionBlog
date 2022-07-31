package com.kay.fashion_blog.controller;

import com.kay.fashion_blog.dto.LoginDto;
import com.kay.fashion_blog.dto.SignUpDto;
import com.kay.fashion_blog.exceptions.BadCredentialsException;
import com.kay.fashion_blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
       var response = authenticationService.signUp(signUpDto);
       return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) throws BadCredentialsException {
        var response = authenticationService.login(loginDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        var response = authenticationService.logout();
        return ResponseEntity.ok().body(response);
    }
}
