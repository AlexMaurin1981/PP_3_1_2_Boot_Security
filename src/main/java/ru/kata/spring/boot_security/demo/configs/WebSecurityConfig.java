package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final SuccessUserHandler successUserHandler;
    @Autowired
    public WebSecurityConfig(UserService userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }




    protected  void  configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
      protected void configure(HttpSecurity http) throws Exception {
          http
                  .authorizeRequests()
                  .antMatchers("/", "/index", "/addNewUser").permitAll()
                  .anyRequest().authenticated()
                  .and()
                  .formLogin().successHandler(successUserHandler)
                  .permitAll()
                  .and()
                  .logout()
                  .permitAll();
      }

    @Bean
      public PasswordEncoder passwordEncoder() {
          return NoOpPasswordEncoder.getInstance();
      }

 //   @Bean
  //    public DaoAuthenticationProvider daoAuthenticationProvider(){
 //       DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
  //      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
 //       daoAuthenticationProvider.setUserDetailsService(userService);
  //  return daoAuthenticationProvider;
  //    }
}




