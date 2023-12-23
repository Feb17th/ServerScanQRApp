package com.example.reactnativeapi.util;

public class AuthenticateUtil {
    public static Integer checkRole(String role){
        if (role.equals("Admin")) return 1;
        else if(role.equals("Customer")) return 2;
        else return null;
    }
}
