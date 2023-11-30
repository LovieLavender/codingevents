package org.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity{

    //mappedBy below tells how to determine which events are in a given category/how should Hibernate know which events are in this specific category.
    //the mappedBy value has to be the name of the field in the class we are storing here that creates this relationship. The relationship is going to be owned by the event.
    //it's going to have a foreign key or reference to an event category.
    //te below field do not go in the constructor because it's being created in the field's declaration


    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    @Size(min =3, message = "Name must be at least 3 characters long")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

}
