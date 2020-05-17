//David Nagy
//5/16/2020
//BookController.java
//Controller for book routes



package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.services.BookService;
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
@RequestMapping("/books")
public class BookController {
    private BookService service;

    /**
     * @param service Controls the service layer
     */
    public BookController(BookService service)
    {
        this.service= service;
    }
    /**
     * @param model All book model controlling
     * @return allbooks model service layer
     */
    @GetMapping("/all")
    public String allBooks(Model model)
    {
        model.addAttribute("books",service.getbook());
        return "all_books";
    }
    /**
     * @param isbn Title of book
     * @param model title of model service
     * @return title of book
     */
    @GetMapping("/isbn/{isbn}")
    public String bookByIsbn (
            @PathVariable String isbn, Model model)
    {
        model.addAttribute("isbn",service.getBook(isbn));
        return "view_books";
    }

    @Override
    public String toString()
    {
        return "BookController{" +
                "service=" + service +
                '}';
    }
}





