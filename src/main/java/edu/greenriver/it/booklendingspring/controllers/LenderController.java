package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lenders")
public class LenderController
{
    private LenderService service;

    public LenderController(LenderService service)
    {
        this.service=service;
    }
    @GetMapping("/all")
    public String allLenders()
    {
        return "lenders/all_lenders";
    }
}
