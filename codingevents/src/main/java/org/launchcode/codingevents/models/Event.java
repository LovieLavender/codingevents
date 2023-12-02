package org.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
public class Event extends AbstractEntity{
//    @Id
//    @GeneratedValue
//    private int id;
    //the above stuff is now in our abstract entity class along with getter, hashcode and equals


//    private static int nextId = 1; no longer need this because the persistent database will do this
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;
    //the above field establishes the relationship between event and event details.

    //   private Event type; this is being replaced with the below,so we can set up a relationship
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;


    public Event(String name, EventCategory eventCategory) {
//        this(); no longer need this either
        this.name = name;
        this.eventCategory = eventCategory;

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


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString(){
        return name;
    }

}
