package com.rafael.sample.person;

import java.util.List;

public class Person {
    private String name;
    private List<String> phoneNumbers;

    public Person() {
    }

    public Person(String name, List<String> phoneNumbers) {
        this.name = name.trim();
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return name  + ": " + phoneNumbers.toString().substring(1, phoneNumbers.toString().length()-1) + "\n";
    }
}
