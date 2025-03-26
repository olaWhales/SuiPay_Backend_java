package com.suipayment.suiPays.dto.request.signUp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestUsername {
    private String email;
    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
