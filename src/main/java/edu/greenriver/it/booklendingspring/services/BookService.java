//David Nagy
//5/17/2020
//BookService.java
// The service layer controls business logic

package edu.greenriver.it.booklendingspring.services;

import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.IBookRepository;
import org.springframework.stereotype.Service;

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
    public BookService(IBookRepository bookRepository) {
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

    public boolean checkisbn(String isbn)
    {
        bookRepository.getBookByIsbn(isbn);
        bookRepository.getLenderByisbn(isbn);
       return bookRepository.getBookByIsbn(isbn).isEmpty();
    }

    // adds a book to the database
    public void addBook(Book book)
    {
        bookRepository.save(book);
    }




    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}
