package com.mycompany.tdd.examples.mockito;

public class Person {

    long id;

    String firstName, lastName;

    public Person(long id, String fname, String lname) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
    }
    public Person(String fname, String lname) {
        this(0, fname, lname);
    }

    public String name() {
        return firstName + " " + lastName;
    }
}
