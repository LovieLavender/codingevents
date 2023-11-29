package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
public class Event extends AbstractEntity{
//    @Id
//    @GeneratedValue
//    private int id;
    //the above stuff is now in our abstract entity class along with getter, hashcode and equals


//    private static int nextId = 1; no longer need this because the persistent database will do this

    private EventType type;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    public Event(String name, String description, String contactEmail, EventType type) {
//        this(); no longer need this either
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;

    }

//    public Event(){
//        this.id= nextId;
//        nextId++;
//    }  no longer need this either due to persistent database
    public Event(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return name;
    }

}
