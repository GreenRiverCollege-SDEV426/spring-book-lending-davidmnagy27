//David Nagy
//5/17/2020
//ILenderRepository.java
//Crud repository that connects with the service layer and model layer





package edu.greenriver.it.booklendingspring.respositories;

import edu.greenriver.it.booklendingspring.model.Lender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author davidnagy
 * @version 2.0
 */
@Repository
public interface ILenderRepository extends CrudRepository<Lender, Long>
{


    /**
     * @param username from lender object
     * @return username
     */
    Optional<Lender> getLenderByUsername(String username);
}
