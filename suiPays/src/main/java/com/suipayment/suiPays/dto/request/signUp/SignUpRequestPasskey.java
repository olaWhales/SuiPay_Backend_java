package com.suipayment.suiPays.dto.request.signUp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestPasskey {
    private String email;
    private String passkey;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasskey() {
        return passkey;
    }

    public void setPasskey(String passkey) {
        this.passkey = passkey;
    }
}
