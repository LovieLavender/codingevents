package org.launchcode.codingevents.controller;


import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("create")
    public String processForm(@RequestParam String eventName, @RequestParam String eventDescription){
        EventData.add(new Event(eventName, eventDescription));
        return "redirect:/events"; //this takes it back to the display events page
    }

}
