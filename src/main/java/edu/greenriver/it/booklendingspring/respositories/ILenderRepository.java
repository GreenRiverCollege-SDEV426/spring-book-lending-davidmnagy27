package edu.greenriver.it.booklendingspring.respositories;

import edu.greenriver.it.booklendingspring.model.Lender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ILenderRepository extends CrudRepository<Lender, Long>
{


    Optional<Lender> getLenderByUsername(String username);
}
