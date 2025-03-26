package com.suipayment.suiPays.services.login;

import com.suipayment.suiPays.dto.response.loginResponse.LoginRequest;
import com.suipayment.suiPays.dto.response.loginResponse.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface Login {
    LoginResponse loginResponse(LoginRequest loginRequest);
}
