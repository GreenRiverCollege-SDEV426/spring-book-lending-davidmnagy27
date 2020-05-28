//David Nagy
//5/17/2020
//LenderService
// The service layer controls business logic

package edu.greenriver.it.booklendingspring.services;


import edu.greenriver.it.booklendingspring.model.Authority;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.model.UserDetailsAdapter;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author davidnagy
 * @version 2.0
 */
@Service
public class LenderService implements UserDetailsService {
 private ILenderRepository lenderRepository;

 /**
  * @param lenderRepository works with the business logic
  */
 public LenderService(ILenderRepository lenderRepository) {
  this.lenderRepository = lenderRepository;
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

 @Override
 public String toString() {
  return "LenderService{" +
          "lenderRepository=" + lenderRepository +
          '}';
 }


 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //This matches are password using Spring Security

  Optional<Lender> lender = lenderRepository.getLenderByUsername(username);
  if (lender.isPresent()) {

   return new UserDetailsAdapter(lender.get());
  }else

  throw new UsernameNotFoundException("username of password is incorrect");

 }

}