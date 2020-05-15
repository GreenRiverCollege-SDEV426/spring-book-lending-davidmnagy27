package edu.greenriver.it.booklendingspring.services;

import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.respositories.IBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    private IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
        public Iterable<Book> getbook()
        {
            return bookRepository.findAll();


    }
}
