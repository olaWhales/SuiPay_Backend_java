package com.suipayment.suiPays.services.signup;


import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;

public interface SignUp {
    SignUpResponse response(SignUpRequestEmail request);

    SignUpResponse verifyPasskey(SignUpRequestPasskey request);

    SignUpResponse saveUsername(SignUpRequestUsername request);
}
