package org.launchcode.codingevents.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayEvents(Model model){
      List <String> events = new ArrayList<>();
      events.add("Born Day");
      events.add("It's a Celebration");
      events.add("Launchcode");
      events.add("Almost Christmas");
      model.addAttribute("events", events);

      return "events/index";
    }
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
}
