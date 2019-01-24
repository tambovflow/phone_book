package com.rafael.sample.db;

import com.rafael.sample.person.Person;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeDbHelper {
    private static File personsFakeDb = new File("src/main/resources/fake_db/persons_db.txt");
    private static BufferedReader personsReader;
    private static PrintWriter personsWriter;
    private static Map<String, Person> personsMap = createHashMap();

    private static Map<String, Person> createHashMap() {
        Map<String, Person> personsMap = new HashMap<>();
        String nextLine=null;
        try {
            if(personsFakeDb.createNewFile()){
                System.out.println("Welcome! Your database has created!");
            }
            personsReader = new BufferedReader(new FileReader(personsFakeDb));
            while ((nextLine = personsReader.readLine())!=null){
                String[] inputString = nextLine.split(": ");
                List<String> phoneNumbers = Arrays.asList(inputString[1]);
                Person person = new Person(inputString[0],phoneNumbers);
                personsMap.put(inputString[0].toLowerCase(), person);
            }
            personsReader.close();
        } catch (IOException  e){
            e.printStackTrace();
        }
        return personsMap;
    }

    public static void addPersonToMap(Person person){
        if(personsMap.containsKey(person.getName().toLowerCase())){
            List<String> phoneNumbers = Stream.concat(
                    person.getPhoneNumbers().stream(),
                    personsMap.get(person.getName().toLowerCase()).getPhoneNumbers().stream())
                    .collect(Collectors.toList());
            person.setPhoneNumbers(phoneNumbers);

        }
        personsMap.put(person.getName().toLowerCase(), person);
    }

    public static boolean writeFakeDb(){
        try {
            personsWriter = new PrintWriter(personsFakeDb);
            for(String s : personsMap.keySet()){
                personsWriter.append(personsMap.get(s).toString());
            }
            personsWriter.flush();
            personsWriter.close();
            return true;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Person findPersonByName(String name){
        return getPersonsMap().get(name.toLowerCase());
    }

    public static Map<String, Person> getPersonsMap(){
        return personsMap;
    }

}
