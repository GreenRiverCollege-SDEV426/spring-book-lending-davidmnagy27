//David Nagy
//6/1/2020
//adminSetup.java
//Connecting to admim

package edu.greenriver.it.booklendingspring.configuration;

import edu.greenriver.it.booklendingspring.model.Authority;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author davidnagy
 * @version 5.0
 *
 */
@Component
public class AdminSetup implements CommandLineRunner
{
    private ILenderRepository lenderRepository;

    /**
     * @param lenderRepository sending the admin to the database.
     */
    public AdminSetup(ILenderRepository lenderRepository)
    {
        this.lenderRepository = lenderRepository;

    }

    @Override
    public void run(String... args)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Lender admin = Lender.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .authorities(new HashSet<>())
                .build();

                admin.getAuthorities().addAll(Arrays.asList(
                        Authority.builder()
                                .authority("ROLE_ADMIN")
                                .lender(admin)
                                .build(),
                        Authority.builder()
                        .authority("ROLE_USER")
                        .lender(admin)
                        .build()

                ));

                lenderRepository.save(admin);

    }

    @Override
    public String toString() {
        return "AdminSetup{" +
                "lenderRepository=" + lenderRepository +
                '}';
    }
}
