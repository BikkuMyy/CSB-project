package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class DefaultController {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/events";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String registerForm() {
        return "register";
    }
    
    @RequestMapping(value = "/register?error", method=RequestMethod.GET)
    public String registerError() {
        return "register";
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String registerUser(@RequestParam String username, @RequestParam String pwd) {
        for(Account a : accountRepository.findAll()){
            if(a.getUsername().equals(username)){
                return "redirect:/register?error";
            }
        }
        Account account = new Account(username, passwordEncoder.encode(pwd));
        accountRepository.save(account);
        
        return "login";
    }
}
