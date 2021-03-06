//David Nagy
//5/17/2020
//LenderController.java
//Lender controller for routing


package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author davidnagy
 * @version 2.0
 */
@Controller
@RequestMapping("/lenders")
public class LenderController  extends AuthenticationInformation{
    private LenderService service;

    /**
     * @param service Lender controller connecting to service layer
     */
    public LenderController(LenderService service) {
        this.service = service;
    }

    /**
     * @param model lender controller connected with model controller
     * @return model object
     */
    @GetMapping("/all")
    public String allLenders(Model model) {
        model.addAttribute("lenders", service.getLenders());

        return "all_lenders";
    }


    /**
     * @param username name of lender
     * @param model lender object
     * @return object
     */
    @GetMapping("/username/{username}")
    public String lenderByUsername(
            @PathVariable String username, Model model) {
        Lender user = service.getLender(username);

        model.addAttribute("lender",service.getLender(username));
        model.addAttribute("booksToLend", service.getBooksToLoan(user));
        model.addAttribute("loanedBook", service.getLoanedBooks(user));
        model.addAttribute("borrowedBook", service.getBorrowedBooks(user));


        return "view_username";
    }

    @Override
    public String toString() {
        return "LenderController{" +
                "service=" + service +
                '}';
    }



}

