package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController
{
    private LenderService service;

    public AuthenticationController(LenderService service)
    {
        this.service =service;
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("lender", new Lender());
        return "register";
    }



}
