package com.suipayment.suiPays.controller;

import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
import com.suipayment.suiPays.services.signup.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private SignUp signUpService;

    // Endpoint to register with a phone number
    @PostMapping("/register/")
    public ResponseEntity<?> registerWithPhoneNumber(@RequestBody SignUpRequestEmail request) {
        try {
            return new ResponseEntity<>(signUpService.response(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to verify passkey
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPasskey(@RequestBody SignUpRequestPasskey request) {
        try{
            return new ResponseEntity<>(signUpService.verifyPasskey(request), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to save username after verification
    @PostMapping("/username")
    public ResponseEntity<?> saveUsername(@RequestBody SignUpRequestUsername request) {
        try{
            return new ResponseEntity<>(signUpService.saveUsername(request), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
