
package com.healthSystemAPI.dao;


import com.healthSystemAPI.classes.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PersonDAO {
    private final List<Person> persons = new ArrayList<>();

    // Create a new person
    public void addAllPerson(Person person) {
        persons.add(person);
    }

    // reading exsiting person by ID 
    public Person getPerson(int id) throws NotFoundException {
        for (Person person : persons) {
            if (person.getPersonId()== id) {
                return person;
            }
        }
        throw new NotFoundException("Person with Id " + id + " not found");
    }

    // Update exsiting person by ID
    public void updatePerson(int id, Person updatedPerson) throws NotFoundException {
        Person person = getPerson(id);
        // Update person attributes
        person.setName(updatedPerson.getName());
        person.setContactNo(updatedPerson.getContactNo());
        person.setAddress(updatedPerson.getAddress());
    }

    // Delete exsiting person by ID  
    public void deletePerson(int id) throws NotFoundException {
        Person person = getPerson(id);
        persons.remove(person);
    }
}
