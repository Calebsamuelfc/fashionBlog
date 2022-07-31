package com.kay.fashion_blog.service.serviceImplementation;


import com.kay.fashion_blog.constants.Roles;
import com.kay.fashion_blog.dto.LoginDto;
import com.kay.fashion_blog.dto.SignUpDto;
import com.kay.fashion_blog.entity.Users;
import com.kay.fashion_blog.constants.DefaultMessage;
import com.kay.fashion_blog.exceptions.BadCredentialsException;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.UserAlreadyExistsException;
import com.kay.fashion_blog.repositories.UsersRepository;
import com.kay.fashion_blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepository usersRepository;
    private final HttpSession httpSession;

    @Override
    public String signUp(SignUpDto signUpDto) throws UserAlreadyExistsException {
        String email = signUpDto.getEmail();
        String fullName = signUpDto.getFullName();
        String password = signUpDto.getPassword();

        if (usersRepository.existsByEmail(email)){
            //use custom exceptions
            throw new UserAlreadyExistsException(DefaultMessage.DUPLICATE_CREDENTIAL_ERROR);
        }

        Users users = Users.builder()
                .name(fullName)
                .email(email)
                .roles(Roles.CUSTOMER)
                .password(password)
                .build();
        usersRepository.save(users);

        return DefaultMessage.SUCCESSFUL_REGISTRATION;
    }
    public String signUpAdmin(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        String fullName = signUpDto.getFullName();
        String password = signUpDto.getPassword();
        Roles role = (Roles) httpSession.getAttribute("Role");//TODO
        if (!role.equals(Roles.ADMIN)){
            return DefaultMessage.NOT_AUTHORIZED_ERROR;
//            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        if (usersRepository.existsByEmail(email)){
            return DefaultMessage.DUPLICATE_CREDENTIAL_ERROR;
//            throw new UserAlreadyExistsException(DefaultMessage.DUPLICATE_CREDENTIAL_ERROR);
        }
        Users users = Users.builder()
                .name(fullName)
                .email(email)
                .roles(Roles.ADMIN)
                .password(password)
                .build();
        usersRepository.save(users);
        return DefaultMessage.SUCCESSFUL_REGISTRATION;
    }

    @Override
    public String login(LoginDto loginDto) throws BadCredentialsException {
        String password = loginDto.getPassword();
        String email = loginDto.getEmail();

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(DefaultMessage.INCORRECT_LOGIN_CREDENTIALS));

        if (!users.getPassword().equals(password)){
            throw new BadCredentialsException(DefaultMessage.INCORRECT_LOGIN_CREDENTIALS);
        }

        httpSession.setAttribute("user_id", users.getId());
        httpSession.setAttribute("Role", users.getRoles());
        httpSession.setAttribute("user", users);

        return DefaultMessage.SUCCESSFUL_LOGIN;
    }


    @Override
    public String logout() {
        httpSession.invalidate();
        return DefaultMessage.SUCCESSFUL_LOGOUT;
    }
}
