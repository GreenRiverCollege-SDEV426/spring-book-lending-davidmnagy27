package edu.greenriver.it.booklendingspring.respositories;

import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.model.Lender;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBookRepository extends CrudRepository<Book, Long> {


        Optional<Book> getLenderByisbn(String isbn);
        }


