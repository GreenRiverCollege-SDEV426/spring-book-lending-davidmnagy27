package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService service;


    public BookController(BookService service)
    {
        this.service= service;
    }

    @GetMapping("/all")
    public String allBooks(Model model)
    {
        model.addAttribute("books",service.getbook());
        return "all_books";
    }

    @GetMapping("/isbn/{isbn}")
    public String bookByIsbn (
            @PathVariable String isbn, Model model)
    {
        model.addAttribute("isbn",service.getBook(isbn));
        return "view_books";
    }
}





