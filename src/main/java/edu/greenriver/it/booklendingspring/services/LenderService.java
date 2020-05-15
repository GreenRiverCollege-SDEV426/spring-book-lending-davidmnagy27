package edu.greenriver.it.booklendingspring.services;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LenderService
{
 private ILenderRepository lenderRepository;

 public LenderService(ILenderRepository lenderRepository)
 {
     this.lenderRepository = lenderRepository;
 }

 public Iterable<Lender> getLenders()
 {
  return  lenderRepository.findAll();



   //  return StreamSupport.stream(lenderRepository.findAll()
     //        .spliterator(),false)
       //      .collect(Collectors.toList());
 }

}
