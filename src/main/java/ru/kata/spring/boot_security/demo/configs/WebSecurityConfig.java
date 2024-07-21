package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.kata.spring.boot_security.demo.services.UserService;
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final SuccessUserHandler successUserHandler;
        private final UserService userService;

        @Autowired
        public WebSecurityConfig(SuccessUserHandler successUserHandler, @Lazy UserService userService) {
            this.successUserHandler = successUserHandler;
            this.userService = userService;
        }

    //  protected  void  configure (AuthenticationManagerBuilder auth) throws Exception {
   //     auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
  //  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index","/login").permitAll()
                .antMatchers("admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");




    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()  {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider daoAuthentication = new DaoAuthenticationProvider();
        daoAuthentication.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthentication.setUserDetailsService(userService);
        return daoAuthentication;
    }
}




