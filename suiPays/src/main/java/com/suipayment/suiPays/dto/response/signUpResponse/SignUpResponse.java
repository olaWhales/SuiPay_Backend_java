package com.suipayment.suiPays.dto.response.signUpResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class SignUpResponse {
    private String message ;
    private String token ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
