package com.rafael.sample;

import com.rafael.sample.db.FakeDbHelper;
import com.rafael.sample.person.Person;
import com.rafael.sample.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean save;
    public static void main(String[] args) {
        String navMenu;

        while (true){
            System.out.println("A -> Add New Person");
            System.out.println("L -> Show persons list");
            System.out.println("F -> Find a person by name");
            if(save) System.out.println("S -> Save changes");
            System.out.println("Q -> Quit");
            navMenu = scanner.nextLine();
            switch (navMenu.toLowerCase()){
                case "a": addNewPerson();
                    save =true;
                    break;
                case "l": showPersonsList();
                    break;
                case "f": findPersonByName();
                    break;
                case "s": save();
                    break;
                case "q": quit();
                    break;
            }
        }
    }


    private static void addNewPerson() {
        String personName;
        String phoneNumber;
        boolean next = false;
        List<String> personPhoneNumbers = new ArrayList<>();

        System.out.println("Input person name: ");
        personName = scanner.nextLine();
        while (true){
            System.out.println("Input person phone number: ");
            phoneNumber = scanner.nextLine();
            if(Validator.checkPhoneNumber(phoneNumber)){
                personPhoneNumbers.add(phoneNumber);
                next = true;
                System.out.println("A -> Add person");
            }else if(next && phoneNumber.toLowerCase().equals("a")){
                FakeDbHelper.addPersonToMap(new Person(personName, personPhoneNumbers));
                break;
            }
            else {
                System.out.println("invalid number format");
            }

        }
    }

    private static void showPersonsList() {
        if(FakeDbHelper.getPersonsMap().isEmpty()){
            System.out.println("Empty");
            return;
        }
        for(String s : FakeDbHelper.getPersonsMap().keySet()){
            System.out.print(FakeDbHelper.getPersonsMap().get(s));
        }
    }

    private static void findPersonByName() {
        Person person;
        System.out.println("Enter Person name: ");
        String personName = scanner.nextLine();

        if((person = FakeDbHelper.findPersonByName(personName))==null){
            System.out.println("Nothing found");
        }else {
            System.out.println(person);
        }
    }

    private static void save() {
        if(!save){
            System.out.println("WRONG COMMAND. \nnothing to save");
            return;
        }
        if(FakeDbHelper.writeFakeDb()) {
            System.out.println("data successfully saved");
            save =false;
        } else {
            System.out.println("Error");
        }
    }

    private static void quit() {
        if(save) {
            String inputLine;
            System.out.println("Save changes before exit? Y: ");
            inputLine = scanner.nextLine().toLowerCase();
            if (inputLine.equals("y")) {
                save();
            }
        }
        System.out.println("Good Bye");
        System.exit(1);
    }

}
