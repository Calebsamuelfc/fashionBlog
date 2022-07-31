package com.kay.fashion_blog.service;

import com.kay.fashion_blog.dto.LoginDto;
import com.kay.fashion_blog.dto.SignUpDto;
import com.kay.fashion_blog.entity.Users;
import com.kay.fashion_blog.exceptions.BadCredentialsException;
import com.kay.fashion_blog.exceptions.UserAlreadyExistsException;

public interface AuthenticationService {
    String signUp(SignUpDto signUpDto) throws UserAlreadyExistsException;

    String login(LoginDto loginDto) throws BadCredentialsException;

    String logout();
    String signUpAdmin(SignUpDto signUpDto);
}
