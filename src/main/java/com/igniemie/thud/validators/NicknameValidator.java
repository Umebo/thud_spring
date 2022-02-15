package com.igniemie.thud.validators;

import com.igniemie.thud.exception.NicknameValidationException;

public class NicknameValidator {
    public static void validateNickname(String login) {
        if (login == null || login.length() <= 1) {
            throw new NicknameValidationException("Login incorrect");
        }
    }
}