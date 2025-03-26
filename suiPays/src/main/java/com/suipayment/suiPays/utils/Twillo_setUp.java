//package com.suipayment.suiPays.utils;
//
//import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Twillo_setUp {
//
////    @Autowired
////    private SignUpResponse signUpResponse;
//
//    // Replace with your Twilio credentials
//    public static final String ACCOUNT_SID = "VAed49a0a46f6f4b20ce38b117fe6a6c3e";
//    public static final String AUTH_TOKEN = "eda939e55a877f3f1175ee803abea99d";
//
//    public static void main(String[] args) {
//        // Initialize Twilio
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        // Send an SMS
//        Message message = Message.creator(
//                        new PhoneNumber(""), // Receiver's phone number
//                        new PhoneNumber(""), // Your Twilio phone number
//                        "Hello from Twilio in Java!") // Message body
//                .create();
//
//        System.out.println("Message sent with SID: " + message.getSid());
//    }
//}
