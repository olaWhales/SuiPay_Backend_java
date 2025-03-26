package com.suipayment.suiPays.utils;
import java.util.HashMap;
import java.util.Map;

public class TemporaryStorage {

    // In-memory storage for phone numbers and passkeys
    private static final Map<String, String> storage = new HashMap<>();

    // Save a passkey for a phone number
    public static void save(String phoneNumber, String passkey) {
        storage.put(phoneNumber, passkey);
    }

    // Retrieve a passkey for a phone number
    public static String get(String phoneNumber) {
        return storage.get(phoneNumber);
    }

    // Remove a phone number and its passkey (after successful verification)
    public static void remove(String phoneNumber) {
        storage.remove(phoneNumber);
    }
}
