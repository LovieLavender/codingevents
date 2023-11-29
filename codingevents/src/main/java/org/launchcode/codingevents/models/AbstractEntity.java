package org.launchcode.codingevents.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

//this class is abstract because no object will ever be instantiated from this class,
// it only collects data and behaviors that can be shared across other classes by extending this class


//the below is a persistence annotation. this says the fields in this class will be pushed down into the tables for the objects that extended
//
// Map superclass just says that we want map these fields down into the extensions when we are putting the data into tables when using orm.
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
