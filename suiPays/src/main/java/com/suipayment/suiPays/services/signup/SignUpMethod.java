//package com.suipayment.suiPays.services.signup;
//
//import com.suipayment.suiPays.data.model.Users;
//import com.suipayment.suiPays.data.repository.SignUpRepository;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
//import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;
//import com.suipayment.suiPays.utils.TemporaryStorage;
//import com.twilio.Twilio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@Service
//public class SignUpMethod implements SignUp {
//    @Autowired
//    private SignUpRepository signUpRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private static final String ACCOUNT_SID = "your_account_sid";
//    private static final String AUTH_TOKEN = "your_auth_token";
//    private static final String TWILIO_PHONE_NUMBER = "+your_twilio_number";
//
//    static {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//    }
//    @Override
//    public SignUpResponse response(SignUpRequestEmail request) {
//        SignUpResponse response = new SignUpResponse();
//        if (signUpRepository.existsByPhoneNumber(request.getEmail())) {
//            response.setMessage("Phone number already registered.");
//            return response;
//        }
//        // Generate a Passkey (e.g., OTP)
//        String passkey = generatePasskey(); // Create a helper method for this
//        saveTemporaryPasskey(request.getEmail(), passkey); // Save passkey temporarily
//        // Simulate sending passkey via SMS
//        System.out.println("Passkey sent to " + request.getEmail() + ": " + passkey);
//        response.setMessage("Passkey sent successfully.");
//        return response;
//    }
//    // Helper method to generate passkey
//    private String generatePasskey() {
//        return String.valueOf(new Random().nextInt(999999 - 100000) + 100000); // 6-digit passkey
//    }
//    // Helper method to save the passkey temporarily
//    private void saveTemporaryPasskey(String phoneNumber, String passkey) {
//        // Use a temporary storage mechanism (e.g., HashMap, Redis)
//        TemporaryStorage.save(phoneNumber, passkey);
//    }
//
//
//    @Override
//    public SignUpResponse verifyPasskey(SignUpRequestPasskey request) {
//        SignUpResponse response = new SignUpResponse();
//        String savedPasskey = TemporaryStorage.get(request.getEmail());
//        if (savedPasskey != null && savedPasskey.equals(request.getPasskey())) {
//            // Passkey verified
//            Users user = new Users();
//            user.setEmail(request.getEmail());
//            signUpRepository.save(user);
//            response.setMessage("Phone number verified. Proceed to input username.");
//        } else {
//            response.setMessage("Invalid passkey.");
//        }
//        return response;
//    }
//
//
//    @Override
//    public SignUpResponse saveUsername(SignUpRequestUsername request) {
//        SignUpResponse response = new SignUpResponse();
//        // Retrieve user using phone number (or session info)
//        Users user = signUpRepository.findByPhoneNumber(request.getEmail());
//
//        if (user != null) {
//            user.setUserName(request.getUserName());
//            signUpRepository.save(user); // Update user with username
//            response.setMessage("Username saved successfully.");
//        } else {
//            response.setMessage("User not found. Please verify your phone number first.");
//        }
//        return response;
//    }
//}
//
package com.suipayment.suiPays.services.signup;

import com.suipayment.suiPays.data.model.Users;
import com.suipayment.suiPays.data.repository.SignUpRepository;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;
import com.suipayment.suiPays.emailSpringEventPackage.EmailService;
import com.suipayment.suiPays.utils.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SignUpMethod implements SignUp {

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    @Override
    public SignUpResponse response(SignUpRequestEmail request) {
        SignUpResponse response = new SignUpResponse();
        if (signUpRepository.existsByEmail(request.getEmail())) {
            response.setMessage("Email already registered.");
            return response;
        }
//         Generate a Passkey (e.g., OTP)
        String subject = "`Welcome to SuiPay platform";
        String passkey = generatePasskey();
        saveTemporaryPasskey(request.getEmail(), passkey);

        // Send OTP via SMS
        emailService.sendEmail(request.getEmail() , subject , passkey);
//        response.setEmail(request.getEmail());
        response.setMessage("Passkey sent successfully to your email. " );
        return response;
    }
//     Helper method to generate passkey
    private String generatePasskey() {
        return String.valueOf(new Random().nextInt(999999 - 100000) + 100000); // 6-digit passkey
    }
//     Helper method to save the passkey temporarily
    private void saveTemporaryPasskey(String email, String passkey) {
        TemporaryStorage.save(email , passkey);
    }

//    @Override
//    public SignUpResponse verifyPasskey(SignUpRequestPasskey request) {
//        SignUpResponse response = new SignUpResponse();
//        String savedPasskey = TemporaryStorage.get(request.getPasskey());
//
//        if (savedPasskey != null && savedPasskey.equals(request.getEmail())) {
//            // Passkey verified
//            Users user = new Users();
//            user.setEmail(request.getEmail());
//            signUpRepository.save(user);
//            response.setMessage("Email verified. Proceed to input username.");
//        } else {
//            response.setMessage("Invalid passkey.");
//        }
//        return response;
//    }
@Override
public SignUpResponse verifyPasskey(SignUpRequestPasskey request) {
    SignUpResponse response = new SignUpResponse();
    // Retrieve the saved passkey using email as the key
    String savedPasskey = TemporaryStorage.get(request.getEmail());

    // Compare the saved passkey with the provided passkey
    if (savedPasskey != null && savedPasskey.equals(request.getPasskey())) {
        // Passkey verified
        Users user = new Users();
        user.setEmail(request.getEmail());
        signUpRepository.save(user);
        response.setMessage("Email verified. Proceed to input username.");
    } else {
        response.setMessage("Invalid passkey.");
    }
    return response;
}



    @Override
    public SignUpResponse saveUsername(SignUpRequestUsername request) {
        SignUpResponse response = new SignUpResponse();
        Users user = signUpRepository.findByEmail(request.getEmail());

        if (user != null) {
            user.setUserName(request.getUserName());
            signUpRepository.save(user);
            response.setMessage("Username saved successfully.");
        } else {
            response.setMessage("User not found. Please verify your phone number first.");
        }
        return response;
    }
}
