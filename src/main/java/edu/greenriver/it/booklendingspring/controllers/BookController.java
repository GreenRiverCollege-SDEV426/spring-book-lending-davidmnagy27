//David Nagy
//5/16/2020
//BookController.java
//Controller for book routes



package edu.greenriver.it.booklendingspring.controllers;

import edu.greenriver.it.booklendingspring.model.Book;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.services.BookService;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author davidnagy
 * @version 2.0
 */
@Controller
@RequestMapping("/books")
public class BookController extends AuthenticationInformation
{
    private BookService service;
    private LenderService lenderService;

    /**
     * @param service Adding a book service
     * @param lenderService adding a lender service.
     */
    public BookController(BookService service, LenderService lenderService)
    {
        this.service = service;
        this.lenderService = lenderService;

    }

    //    public BookController(BookService service) {
//        this.service = service;
//    }

    /**
     * @param model All book model controlling
     * @return allbooks model service layer
     */
    @GetMapping("/all")
    public String allBooks(Model model) {
        model.addAttribute("books", service.getbook());
        return "all_books";
    }
    /**
     * @param isbn  Title of book
     * @param model title of model service
     * @return title of book
     */
    @GetMapping("/view/{isbn}")
    public String bookByIsbn(
            @PathVariable String isbn, Model model)
    {
        model.addAttribute("book", service.getBook(isbn));
        return "view_books";
    }


    /**
     * @param isbn Adding a book to let other users borrow a book
     * @return borrowing a book
     */
    @GetMapping("/borrow/{isbn}")

        public String borrowBook(@PathVariable String isbn)
    {
        Lender loggedInUser = lenderService.getLoggedInUser();
        Book book= service.getBook(isbn);

        lenderService.borrowBook(loggedInUser,book);

        return "redirect:/lenders/username/" +
                book.getOwner().getUsername();



    }

    /**
     * @param isbn return book feature
     * @return book
     */
    @GetMapping("/return/{isbn}")

    public String returnBooks(@PathVariable String isbn)
    {
        Lender loggedInUser = lenderService.getLoggedInUser();
        Book book = service.getBook(isbn);
        lenderService.returnBooks(loggedInUser,book);

        return "redirect:/lenders/username/" + loggedInUser.getUsername();

    }


    /**
     * @param isbn adding a book image to the view book
     * @param response to getting the image from file
     * @throws IOException finding the image
     */
    @GetMapping("/{isbn}/image")
    public void bookImage(@PathVariable String isbn, HttpServletResponse response) throws IOException
    {
        Book book = service.getBook(isbn);
        Byte[] bytes = book.getCoverImage();

        byte[] fileBytes = new byte[bytes.length];
        for(int i=0;i<bytes.length; i++)
        {
            fileBytes[i]= bytes[i];
        }


        response.setContentType("image/jpeg");
        InputStream io = new ByteArrayInputStream(fileBytes);
        IOUtils.copy(io,response.getOutputStream());


    }

    /**
     * @param model adding new book
     * @return addbook
     */
    @GetMapping("/addbook")
    public String registerbook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }


    /**
     * @param book adding image to file
     * @param file the file reads the image sends to database
     * @param model saves book in database
     * @return add book
     * @throws IOException checks isbn
     */
    @PostMapping("/addbook")
    public String addbook(@ModelAttribute Book book,
                          @RequestParam("cover-image") MultipartFile file,
                          Model model) throws IOException {

        boolean checkSaveBook = service.saveBook(book,lenderService.getLoggedInUser(), file);

        if (checkSaveBook)
        {
            // add book to the database
            return "redirect:/books/addbook";
        }
        else
        {
            model.addAttribute("errors", "ISBN already in use!");
            return "addbook";
        }
    }

    /**
     * @param model books that user owns.
     * @return my book feature
     */
    @GetMapping("/mybook")
    public String mybook(Model model)
    {
        model.addAttribute("books",
                lenderService.getBooksbylender(lenderService.getLoggedInUser()));
        return "/mybooks";
    }











    @Override
    public String toString()
    {
        return "BookController{" +
                "service=" + service +
                '}';
    }
}

