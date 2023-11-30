package org.launchcode.codingevents.controller;


import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;


//import static com.sun.beans.introspect.PropertyInfo.Name.required;


@Controller
@RequestMapping("events")
public class EventController {

//    private static List<Event> events = new ArrayList<>();  removed this since we are not holding data anymore in this controller
    //class but will be held in the event data class
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    //findAll, save, findById
    //we replaced all uses of our static Events data class that was storing objects in a memory collection and
    // replaced them with usages of the repository which is the interface that allows us to react with our Mysql database.
    @GetMapping
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model){

        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
              return "events/index";
    }

//    added another attribute below, an empty Event object to handle the no-arg constructor.
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create") //now because of the @ModelAttribute, when this method is called, Spring will create a new Event object
    public String processForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:/events"; //this takes it back to the display events page
    }

    @GetMapping("delete") //this display the form. We add the Model object since we are passing data into
    public String renderDeleteEventForm(Model model){
     model.addAttribute("title", "Delete Event");
     model.addAttribute("events", eventRepository.findAll()); //this is us passing in the collection of events to delete
     return "events/delete";
    }

    @PostMapping("delete")
    //using int[] which is an array of integers due to the html handling all the id's
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null){
            for (int id : eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

}
