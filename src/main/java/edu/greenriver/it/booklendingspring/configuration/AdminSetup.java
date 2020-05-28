package edu.greenriver.it.booklendingspring.configuration;

import edu.greenriver.it.booklendingspring.model.Authority;
import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

public class AdminSetup implements CommandLineRunner
{
    private ILenderRepository lenderRepository;

    public AdminSetup(ILenderRepository lenderRepository)

    {
        this.lenderRepository=lenderRepository;

}


    @Override
    public void run(String... args) throws Exception
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Lender admin= Lender.builder()
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
}
