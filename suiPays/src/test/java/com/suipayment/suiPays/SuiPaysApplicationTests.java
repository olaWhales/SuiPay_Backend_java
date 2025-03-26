//package com.suipayment.suiPays;
//
//import com.suipayment.suiPays.data.model.Users;
//import com.suipayment.suiPays.data.repository.SignUpRepository;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
//import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
//import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;
//import com.suipayment.suiPays.services.signup.SignUp;
//import com.suipayment.suiPays.services.signup.SignUpMethod;
//import com.suipayment.suiPays.utils.TemporaryStorage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class SuiPaysApplicationTests {
//	@Autowired
//	private SignUp signUp;
//	@Autowired
//	private SignUpResponse response;
//	@Autowired
//	private SignUpRepository signUpRepository;
//
//	private final SignUpRequestEmail signUpRequestNumber = new SignUpRequestEmail();
////	@BeforeEach(){
////		String phoneNumber = "08102790000";
////		String userName = "JohnDoe";
////	}
//
//
//	@Test
//	void testThatUserCanRegisterWithPhoneNumber() {
//		// Arrange
//		signUpRequestNumber.setEmail("08102790000");
//		// Act
//		SignUpResponse response = signUp.response(signUpRequestNumber);
//		// Assert
//		assertNotNull(response);
//		assertEquals("Success", response.getMessage());
//	}
//
//	@Test
//	void testThatWhenUserRegistersTheyGetAPasskeyToProceed() {
//		// Arrange
//		signUpRequestNumber.setEmail("08102790000");
//		// Act
//		SignUpResponse response = signUp.response(signUpRequestNumber);
//		// Simulate retrieving the passkey
//		String passkey = TemporaryStorage.get(signUpRequestNumber.getEmail()); // Temporary storage
//		assertNotNull(response);
//		assertEquals("Success", response.getMessage());
//		assertNotNull(passkey, "Passkey should not be null.");
//	}
//
//	@Test
//	void testThatUserCanVerifyPasskey() {
//		String phoneNumber = "08102790000";
//		signUpRequestNumber.setEmail(phoneNumber);
//		String expectedPasskey = "123456";
//		TemporaryStorage.save(phoneNumber, expectedPasskey); // Simulate storing passkey
//		SignUpRequestPasskey signUpRequestPasskey = new SignUpRequestPasskey();
//		signUpRequestPasskey.setEmail(phoneNumber);
//		signUpRequestPasskey.setPasskey(expectedPasskey);
//		// Act
//		SignUpResponse response = signUp.verifyPasskey(signUpRequestPasskey);
//		// Assert
//		assertNotNull(response);
//		assertEquals("Phone number verified. Proceed to input username.", response.getMessage());
//	}
//
//	@Test
//	void testThatUserCanProceedToInputUsername() {
//		// Arrange
//		String phoneNumber = "08102790000";
//		String userName = "JohnDoe";
//		SignUpRequestUsername signUpRequestUsername = new SignUpRequestUsername();
//		signUpRequestUsername.setEmail(phoneNumber);
//		signUpRequestUsername.setUserName(userName);
//		Users user = new Users();
//		user.setEmail(phoneNumber); // Simulate a user already verified
//		signUpRepository.save(user);
//		// Act
//		SignUpResponse response = signUp.saveUsername(signUpRequestUsername);
//		// Assert
//		assertNotNull(response);
//		assertEquals("Username saved successfully.", response.getMessage());
//	}
//}



package com.suipayment.suiPays;

import com.suipayment.suiPays.data.model.Users;
import com.suipayment.suiPays.data.repository.SignUpRepository;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestEmail;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestPasskey;
import com.suipayment.suiPays.dto.request.signUp.SignUpRequestUsername;
import com.suipayment.suiPays.dto.response.signUpResponse.SignUpResponse;
import com.suipayment.suiPays.services.signup.SignUp;
import com.suipayment.suiPays.utils.TemporaryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SuiPaysApplicationTests {
	@Autowired
	private SignUp signUp;

	@Autowired
	private SignUpRepository signUpRepository;

	private SignUpRequestEmail signUpRequestEmail;
	private String phoneNumber;
	private String userName;

	@BeforeEach
	void setUp() {
		// Initialize shared test data
		phoneNumber = "08102790000";
		userName = "JohnDoe";

		signUpRequestEmail = new SignUpRequestEmail();
		signUpRequestEmail.setEmail(phoneNumber);
	}

	@Test
	void testThatUserCanRegisterWithPhoneNumber() {
		// Act
		SignUpResponse response = signUp.response(signUpRequestEmail);

		// Assert
		assertNotNull(response);
		assertEquals("Success", response.getMessage());
	}

	@Test
	void testThatWhenUserRegistersTheyGetAPasskeyToProceed() {
		// Act
		SignUpResponse response = signUp.response(signUpRequestEmail);
		// Simulate retrieving the passkey
		String passkey = TemporaryStorage.get(phoneNumber);
		// Assert
		assertNotNull(response);
		assertEquals("Success", response.getMessage());
		assertNotNull(passkey, "Passkey should not be null.");
	}

//	@Test
//	void testThatUserCanVerifyPasskey() {
//		// Arrange
//		String expectedPasskey = "123456";
//		TemporaryStorage.save(phoneNumber, expectedPasskey); // Simulate storing passkey
//		SignUpRequestPasskey signUpRequestPasskey = new SignUpRequestPasskey();
//		signUpRequestPasskey.setEmail(phoneNumber);
//		signUpRequestPasskey.setPasskey(expectedPasskey);
//		// Act
//		SignUpResponse response = signUp.verifyPasskey(signUpRequestPasskey);
//
//		// Assert
//		assertNotNull(response);
//		assertEquals("Phone number verified. Proceed to input username.", response.getMessage());
//	}

	@Test
	void testThatUserCanProceedToInputUsername() {
		// Arrange
		SignUpRequestUsername signUpRequestUsername = new SignUpRequestUsername();
		signUpRequestUsername.setEmail(phoneNumber);
		signUpRequestUsername.setUserName(userName);

		Users user = new Users();
		user.setEmail(phoneNumber); // Simulate a verified user
		signUpRepository.save(user);

		// Act
		SignUpResponse response = signUp.saveUsername(signUpRequestUsername);

		// Assert
		assertNotNull(response);
		assertEquals("Username saved successfully.", response.getMessage());
	}
}
