//David Nagy
//6/1/2020
//userDetailsAdapter.java
// Using the Adapter pattern to get user details

package edu.greenriver.it.booklendingspring.model;


import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Collection;

/**
 * @author davidnagy
 * @version 5.0
 */
@Component
@NoArgsConstructor
public class UserDetailsAdapter implements UserDetails
{
    private Lender lender;


    /**
     * @param lender lender user details
     */
    public UserDetailsAdapter(Lender lender)
    {
        this.lender=lender;

    }

    @Override
    public Collection<Authority> getAuthorities()
    {
        return lender.getAuthorities();
    }

    @Override
    public String getPassword()
    {
        return lender.getPassword();
    }

    @Override
    public String getUsername()
    {
        return lender.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString()
    {
        return "UserDetailsAdapter{" +
                "lender=" + lender +
                '}';
    }
}
