package com.redmath.Bank.App.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String bcryptPassword(String password) {
        return ENCODER.encode(password);
    }
}
