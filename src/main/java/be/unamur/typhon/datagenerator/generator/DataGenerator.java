package be.unamur.typhon.datagenerator.generator;

import java.security.SecureRandom;
import java.util.UUID;

public class DataGenerator {

    private static SecureRandom random = new SecureRandom();
    private static final int RANDOM_STRING_LENGTH = 256;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

    protected static int generateRandomInt() {
        return random.nextInt();
    }

    protected static String generateRandomString() {
        StringBuilder sb = new StringBuilder(RANDOM_STRING_LENGTH);
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            sb.append(DATA_FOR_RANDOM_STRING.charAt(random.nextInt(DATA_FOR_RANDOM_STRING.length())));
        }
        return sb.toString();
    }

    protected static String generateUuid() {
        return UUID.randomUUID().toString();
    }

}