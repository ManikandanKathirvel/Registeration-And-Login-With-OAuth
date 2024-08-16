package com.mani.RegisterationAndLogin.Exception;

import com.mani.RegisterationAndLogin.Entity.User;

public class userNameNotFoundException extends Exception {
    public userNameNotFoundException(String msg) {
        super(msg);
    }
}
