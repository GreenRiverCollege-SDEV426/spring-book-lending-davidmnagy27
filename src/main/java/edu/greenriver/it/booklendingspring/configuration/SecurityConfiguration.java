//David Nagy
//6/1/2020
//SecurityConfiguration
// Supports the security features in spring.


package edu.greenriver.it.booklendingspring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author davidnagy
 * @version 5.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private UserDetailsService service;

    /**
     * @param service UserDetails connect to book service.
     */
    public SecurityConfiguration(UserDetailsService service)
    {
        this.service=service;
    }

    /**
     * @return the Password encoder hash
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BCryptPasswordEncoder encoder=passwordEncoder();
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    @Override
    public  void configure(WebSecurity web)
    {
        web.ignoring().antMatchers("/js/**")
                .and()
                .ignoring().antMatchers("/css/**")
                .and()
                .ignoring().antMatchers("/h2-console/**");

    }
    @Override
    protected  void configure (HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                    .antMatchers("/books/all").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/lenders/**").hasAuthority("ROLE_USER")
                    .antMatchers("/books/**").hasAuthority("ROLE_USER")
                    .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error=true")
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/access_denied")
                .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true");

    }

    @Override
    public String toString() {
        return "SecurityConfiguration{" +
                "service=" + service +
                '}';
    }
}
