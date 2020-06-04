//David Nagy
//5/17/2020
//BookService.java
// The service layer controls business logic

package edu.greenriver.it.booklendingspring.services;

import edu.greenriver.it.booklendingspring.model.Book;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.IBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author davidnagy
 * @version 2.0
 */
@Service
public class BookService
{
    private IBookRepository bookRepository;



    /**
     * @param bookRepository works with the database of business logic
     */
    public BookService(IBookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    /**
     * @return getbook from database
     */
        public Iterable<Book> getbook()
        {
            return bookRepository.findAll();


    }

    /**
     * @param isbn title of book
     * @return book
     */
    public Book getBook(String isbn)
    {
        return bookRepository
                .getLenderByisbn(isbn)
                .orElse(null);
    }

    /**
     * @param isbn Checking ISBN and and get lender by ISBN
     * @return book by isbn
     */
    public boolean checkisbn(String isbn)
    {
        bookRepository.getBookByIsbn(isbn);
        bookRepository.getLenderByisbn(isbn);

       return bookRepository.getBookByIsbn(isbn).isEmpty();
    }

    /**
     * @param book adding book into the database
     */
    // adds a book to the database
   public void addBook(Book book)
    {
        bookRepository.save(book);
    }


    /**
     * @param book saving book into database
     * @param file save book using multipartfile
     * @return saved book
     * @throws IOException returns true if booked saved
     */
    public boolean saveBook(Book book, Lender loggedInUser, MultipartFile file)  throws IOException
    {

        if(getBook(book.getIsbn()) == null)
        {
            book.setOwner(loggedInUser);
            loggedInUser.getBooks().add(book);

         saveImageToBook(book, file);
         bookRepository.save(book);
         return true;

        }
        return false;
    }

    private void saveImageToBook(Book book, MultipartFile file) throws IOException
    {
       byte[] fileBytes = file.getBytes();
       Byte[] bytes = new Byte[fileBytes.length];

       for (int i = 0; i < fileBytes.length; i++)
       {
           bytes[i] = new Byte(fileBytes[i]);

        }
       book.setCoverImage(bytes);

    }





    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}
