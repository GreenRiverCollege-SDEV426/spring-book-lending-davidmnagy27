package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.services.BookService;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/register")
    public String register(@ModelAttribute Lender lender,
                           Model model)
    {
        lender = service.registerUser(lender);
        if (lender != null) {
            return "redirect:";
        } else {
            model.addAttribute("errors", "Password does not match!");
            return "register";
        }


    }



}
