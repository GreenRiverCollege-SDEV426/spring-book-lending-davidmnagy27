//David Nagy
//5/17/2020
//Book.java
// controlls the book data in the database


package edu.greenriver.it.booklendingspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.*;

/**
 * @author davidnagy
 * @version 2.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private int pages;

    @Lob
    private String synopsis;
    @Lob
    private Byte[] coverImage;


    /**
     * @return book/image
     */
    public String getImage()
    {
        String location = "/images/covers/" + title + ".jpg";

        if (new ClassPathResource("/static" + location).isFile()) {
            return location;
        }
        return "/templates/books/" + isbn + "/image";
    }


}