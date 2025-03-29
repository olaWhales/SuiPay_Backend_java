package com.suipayment.suiPays.controller;

import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
import com.suipayment.suiPays.dto.response.loginResponse.LoginRequest;
import com.suipayment.suiPays.services.login.LoginMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class LoginController {

    @Autowired
    private LoginMethod loginMethod;

    @PostMapping("/register/")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return new ResponseEntity<>(loginMethod.loginResponse(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
