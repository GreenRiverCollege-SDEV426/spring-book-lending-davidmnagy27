//David Nagy
//5/17/2020
//LenderService
// The service layer controls business logic

package edu.greenriver.it.booklendingspring.services;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;
import org.springframework.stereotype.Service;

/**
 * @author davidnagy
 * @version 2.0
 */
@Service
public class LenderService
{
 private ILenderRepository lenderRepository;

 /**
  * @param lenderRepository works with the business logic
  */
 public LenderService(ILenderRepository lenderRepository)
 {
     this.lenderRepository = lenderRepository;
 }
 /**
  * @return the lenders
  */
 public Iterable<Lender> getLenders()
 {
  return lenderRepository.findAll();
 }

 /**
  * @param username object of lenders
  * @return lenders
  */
  public Lender getLender(String username)
  {

   return lenderRepository
           .getLenderByUsername(username)
           .orElse(null);
  }

 @Override
 public String toString()
 {
  return "LenderService{" +
          "lenderRepository=" + lenderRepository +
          '}';
 }
}
