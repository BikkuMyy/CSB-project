package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Event;
import sec.project.domain.Signup;
import sec.project.repository.EventRepository;
import sec.project.repository.SignupRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private SignupRepository signupRepository;
            

    @PostConstruct
    public void init() {
        eventRepository.save(new Event("Halloween"));
        eventRepository.save(new Event("Movies"));
        eventRepository.save(new Event("Excursion"));
        eventRepository.save(new Event("Cruise"));
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }
    
    @RequestMapping(value = "/myevents", method = RequestMethod.GET)
    public String listMyEvents(Model model) {
        List<String> eventsWithSignup = new ArrayList();
        
        for(Signup s : signupRepository.findAll()){
            eventsWithSignup.add(s.getEvent().getName());
        }
        model.addAttribute("events", eventsWithSignup);
        return "myevents";
    }
    
    @RequestMapping(value="/events/{event}", method=RequestMethod.DELETE)
    public String remove(@PathVariable String event){
        Event e = eventRepository.findByName(event);
        for(Signup s : e.getSignups()){
           signupRepository.delete(s);
        }
        e.clearSignups();
        return "myevents";
    }

}
