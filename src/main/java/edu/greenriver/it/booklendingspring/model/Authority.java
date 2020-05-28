package edu.greenriver.it.booklendingspring.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String authority;

    @ManyToOne
  //  @JoinColumn(name="user")
    private Lender lender;


    @Override
    public String getAuthority() {
        return authority;
    }
}
