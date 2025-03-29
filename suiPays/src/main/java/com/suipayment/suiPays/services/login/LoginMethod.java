package com.suipayment.suiPays.services.login;

import com.suipayment.suiPays.data.model.Users;
import com.suipayment.suiPays.data.repository.SignUpRepository;
import com.suipayment.suiPays.dto.response.loginResponse.LoginRequest;
import com.suipayment.suiPays.dto.response.loginResponse.LoginResponse;
import com.suipayment.suiPays.securityConfig.JwtService;
import org.springframework.stereotype.Service;

@Service
public class LoginMethod implements Login {

    private final SignUpRepository signUpRepository;
    private final JwtService jwtService;

    public LoginMethod(SignUpRepository signUpRepository, JwtService jwtService) {
        this.signUpRepository = signUpRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse loginResponse(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("You must provide your email address before you can proceed.");
        }

        Users user = signUpRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid login"));
        if(user ==  null){
            throw new IllegalArgumentException("Invalid login");
        }
        String token = jwtService.GenerateToken(email);
        LoginResponse response = new LoginResponse();
        response.setMessage("LOGIN SUCCESSFUL");
        response.setToken(token);
        return response;
    }
}
