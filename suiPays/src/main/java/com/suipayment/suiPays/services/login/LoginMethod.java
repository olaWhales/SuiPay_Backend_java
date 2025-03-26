package com.suipayment.suiPays.services.login;

import com.suipayment.suiPays.dto.response.loginResponse.LoginRequest;
import com.suipayment.suiPays.dto.response.loginResponse.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginMethod implements Login{

    @Override
    public LoginResponse loginResponse(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();

        return null;
    }
}
