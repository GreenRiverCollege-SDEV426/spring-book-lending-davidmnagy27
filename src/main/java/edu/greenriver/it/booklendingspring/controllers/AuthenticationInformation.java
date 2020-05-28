package edu.greenriver.it.booklendingspring.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthenticationInformation
{
    @ModelAttribute("validUserLoggedIn")
    public boolean isLoggedIn()
    {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return auth !=null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken);
    }

    @ModelAttribute("loggedInUsername")
    public String loggedInUsername()
    {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

}
