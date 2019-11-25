package com.mycompany.tdd.examples.mockito;

import java.util.List;


public interface PersonDao {
    public List<Person> getAll();

    public Person getById(long id);
}