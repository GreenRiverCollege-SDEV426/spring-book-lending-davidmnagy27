//David Nagy
//5/25/2020
//AuthenticationController
// Controlls the register form.


package edu.greenriver.it.booklendingspring.controllers;


import edu.greenriver.it.booklendingspring.configuration.SecurityConfiguration;
import edu.greenriver.it.booklendingspring.model.Lender;

import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author davidnagy
 * @version 3.0
 * controls the LenderService layer
 */
@Controller
public class AuthenticationController extends AuthenticationInformation
{
    private LenderService service;


    /**
     * @param service the service layer for the register form
     */
    public AuthenticationController(LenderService service)
    {
        this.service =service;
    }

    /**
     * @param model adding a new lender
     * @return lender
     */
    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("lender", new Lender());
        return "/general/register";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout, Model model)
    {
        if(error != null)
        {
            model.addAttribute("message","invalid credentials");
        }
        if(logout !=null)
        {
            model.addAttribute("message","user logged out");
        }
        return "/general/login";

    }

    @GetMapping("/access_denied")
    public String denied()
    {
        return "/general/access_denied";
    }



    /**
     * @param lender if the password doesnt match sends out an error
     * @param model attribute errors
     * @return register
     */
    @PostMapping("/register")
    public String register(@ModelAttribute Lender lender,
                           Model model)
    {
        lender = service.registerUser(lender);
        if (lender != null) {
            return "redirect:";
        } else {
            model.addAttribute("errors", "Password does not match!");
            return "/general/register";
        }



    }

    @Override
    public String toString() {
        return "AuthenticationController{" +
                "service=" + service +
                '}';
    }
}
