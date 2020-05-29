//David Nagy
//5/17/2020
//LenderService
// The service layer controls business logic

package edu.greenriver.it.booklendingspring.services;


import edu.greenriver.it.booklendingspring.model.Authority;
import edu.greenriver.it.booklendingspring.model.Book;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.model.UserDetailsAdapter;
import edu.greenriver.it.booklendingspring.respositories.IBookRepository;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author davidnagy
 * @version 2.0
 */
@Service
@Primary
public class LenderService implements UserDetailsService {
 private ILenderRepository lenderRepository;
 private IBookRepository bookRepository;

 /**
  * @param lenderRepository works with the business logic
  */
// public LenderService(ILenderRepository lenderRepository) {
//  this.lenderRepository = lenderRepository;
// }


 public LenderService(ILenderRepository lenderRepository, IBookRepository bookRepository) {
  this.lenderRepository = lenderRepository;
  this.bookRepository = bookRepository;
 }

 /**
  * @return the lenders
  */
 public Iterable<Lender> getLenders() {
  return lenderRepository.findAll();
 }

 /**
  * @param username object of lenders
  * @return lenders
  */
 public Lender getLender(String username) {
  return lenderRepository
          .getLenderByUsername(username)
          .orElse(null);
 }


 /**
  * @param lender registering new user covifriming the password is the same.
  * @return saving lender to database
  */
 public Lender registerUser(Lender lender) {

  //if password match
  if (lender.getPassword().equals(lender.getPasswordConfirmed())) {
   //encode the password
   lender.setPassword(new BCryptPasswordEncoder()
           .encode(lender.getPassword()));


   //save the role of userfor a new account

   Authority authority = Authority
           .builder()
           .authority("ROLE_USER")
           .lender(lender)
           .build();
   lender.getAuthorities().add(authority);

   //save and return
   return lenderRepository.save(lender);
  } else {
   return null;
  }

 }

 public Lender getLoggedInUser ()
 {
  Authentication auth = SecurityContextHolder
          .getContext()
          .getAuthentication();


  String username = auth.getName();
  Lender loggedInUser = lenderRepository
          .getLenderByUsername(username)
          .orElse(null);

  return loggedInUser;
 }

 public List<Book>getBooksbylender(Lender lender)
 {
  return  bookRepository.getAllByOwner(lender);

 }


 @Override
 public String toString() {
  return "LenderService{" +
          "lenderRepository=" + lenderRepository +
          '}';
 }


 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //This matches are password using Spring Security

  Optional<Lender> lender = lenderRepository.getLenderByUsername(username);
  if (lender.isPresent()) {

   return new UserDetailsAdapter(lender.get());
  }else

  throw new UsernameNotFoundException("username of password is incorrect");

 }

}