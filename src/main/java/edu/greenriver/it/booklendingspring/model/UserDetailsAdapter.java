package edu.greenriver.it.booklendingspring.model;

import edu.greenriver.it.booklendingspring.model.Lender;
import edu.greenriver.it.booklendingspring.respositories.ILenderRepository;
import edu.greenriver.it.booklendingspring.services.LenderService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

public class UserDetailsAdapter implements UserDetails
{
    private Lender lender;


    public UserDetailsAdapter(Lender lender)
    {
        this.lender=lender;
        lender.getUsername();
        lender.getPassword();


    }

    @Override
    public Collection<Authority> getAuthorities() {
        return lender.getAuthorities();
    }



    @Override
    public String getPassword() {
        return lender.getPassword();
    }

    @Override
    public String getUsername() {
        return lender.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
