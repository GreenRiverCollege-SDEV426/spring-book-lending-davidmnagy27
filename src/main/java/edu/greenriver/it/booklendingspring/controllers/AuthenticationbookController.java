package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.services.BookService;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationbookController {


    private BookService service;


    public AuthenticationbookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/addbook")
    public String registerbook(Model model) {
        model.addAttribute("book", new Lender());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addbook(@ModelAttribute Book book,
                          Model model) {

        if (service.checkisbn(book.getIsbn()))
        {
            return "redirect:";
        } else {
            model.addAttribute("errors", "ISBN already in use!");
            return "addbook";
        }



    }
}