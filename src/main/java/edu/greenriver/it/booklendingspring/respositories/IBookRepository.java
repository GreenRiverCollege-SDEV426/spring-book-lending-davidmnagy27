//David Nagy
//5/17/2020
//IBookRepository.java
//Crud repository that connects with the service layer and model layer



package edu.greenriver.it.booklendingspring.respositories;

import edu.greenriver.it.booklendingspring.model.Book;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author davidnagy
 * @version 2.0
 */
public interface IBookRepository extends CrudRepository<Book, Long>
{


        /**
         * @param isbn title of book
         * @return the book object
         */
        Optional<Book> getLenderByisbn(String isbn);
        Optional<Book> getBookByIsbn(String isbn);



}



