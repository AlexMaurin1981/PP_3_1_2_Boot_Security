package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import javax.security.sasl.AuthenticationException;

@EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final SuccessUserHandler successUserHandler;

        private  final UserDetailsServiceImpl userDetailsService;
        @Autowired
        public WebSecurityConfig(SuccessUserHandler successUserHandler,UserDetailsServiceImpl userDetailsService) {
            this.successUserHandler = successUserHandler;

            this.userDetailsService = userDetailsService;
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index","/login").permitAll()
                .antMatchers("admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          try {
              auth.authenticationProvider(daoAuthenticationProvider());
          } catch (Exception e) {
           throw new AuthenticationException("Failed to configure authentication provider",e);
          }
        }

        @Bean
       public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
    DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider daoAuthentication = new DaoAuthenticationProvider();
        daoAuthentication.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthentication.setUserDetailsService(userDetailsService);
        return daoAuthentication;
    }
}




