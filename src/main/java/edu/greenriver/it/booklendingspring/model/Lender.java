//David Nagy
//5/17/2020
//Lender.java
// controlls the lender data in the database

package edu.greenriver.it.booklendingspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author davidnagy
 * @version 2.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Lender
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Transient

    private String passwordConfirmed;

    private String firstName;

    private String lastName;

    @Lob
    private String bio;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "lender")
//    private Collection<Authority> authorities= new HashSet<>();
//
}

