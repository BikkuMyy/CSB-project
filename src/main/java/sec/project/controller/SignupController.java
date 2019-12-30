package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Event;
import sec.project.domain.Signup;
import sec.project.repository.EventRepository;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/form/{eventname}", method = RequestMethod.GET)
    public String loadForm(@PathVariable String eventname, Model model) {
        model.addAttribute("eventname", eventname);
        return "form";
    }

    @RequestMapping(value = "/form/{eventname}", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam String info, @PathVariable String eventname) {
        Event event = eventRepository.findByName(eventname);
        Signup signup = new Signup(name, address, info, event);
        signupRepository.save(signup);
        event.addSignup(signup);
        
        return "done";
    }

}
