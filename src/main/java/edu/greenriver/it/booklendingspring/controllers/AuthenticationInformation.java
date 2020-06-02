//David Nagy
//6/1/2020
// AuthenticationInformation.java
// Authentication info for when valid users log in.

package edu.greenriver.it.booklendingspring.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/** @author davidnagy
 * @version 5.0
 *
 */
@Controller
public class AuthenticationInformation
{
    /**
     * @return valid User log in.
     */
    @ModelAttribute("validUserLoggedIn")
    public boolean isLoggedIn()
    {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return auth !=null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken);
    }

    /**
     * @return valid login from username
     */
    @ModelAttribute("loggedInUsername")
    public String loggedInUsername()
    {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

}
