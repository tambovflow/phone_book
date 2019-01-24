package com.rafael.sample.validator;

public class Validator {

    public static boolean checkPhoneNumber(String phoneNumber){
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        phoneNumber = phoneNumber.trim();
        return phoneNumber.matches(regex);
    }
}
