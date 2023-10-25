package org.launchcode.codingevents.controller;


import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


//import static com.sun.beans.introspect.PropertyInfo.Name.required;


@Controller
@RequestMapping("events")
public class EventController {

//    private static List<Event> events = new ArrayList<>();  removed this since we are not holding data anymore in this controller
    //class but will be held in the event data class

    @GetMapping
    public String displayAllEvents(Model model){
      model.addAttribute("title", "All Events");
      model.addAttribute("events", EventData.getAll());
      return "events/index";
    }
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create") //now because of the @ModelAttribute, when this method is called, Spring will create a new Event object
    public String processForm(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        return "redirect:/events"; //this takes it back to the display events page
    }

    @GetMapping("delete") //this display the form. We add the Model object since we are passing data into
    public String renderDeleteEventForm(Model model){
     model.addAttribute("title", "Delete Event");
     model.addAttribute("events", EventData.getAll()); //this is us passing in the collection of events to delete
     return "events/delete";
    }

    @PostMapping("delete")
    //using int[] which is an array of integers due to the html handling all the id's
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null){
            for (int id : eventIds){
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }

}
