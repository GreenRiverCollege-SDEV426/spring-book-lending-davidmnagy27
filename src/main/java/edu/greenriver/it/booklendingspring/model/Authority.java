//David Nagy
//6/1/2020
// Authority.java
// implements granted authority

package edu.greenriver.it.booklendingspring.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;

/**
 * @author davidnagy
 * @version 5.0
 */
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

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", lender=" + lender +
                '}';
    }
}
