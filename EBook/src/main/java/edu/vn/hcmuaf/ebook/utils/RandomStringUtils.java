package edu.vn.hcmuaf.ebook.utils;

public class RandomStringUtils {
    public static String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (characters.length() * Math.random());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }

    public static String randomNumber(int length) {
        String characters = "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (characters.length() * Math.random());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
}
