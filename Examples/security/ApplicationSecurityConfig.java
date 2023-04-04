package com.project.TalonMillwork.security;

import com.project.TalonMillwork.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class implements basic auth for the application
 *
 * Some code sourced from https://github.com/amigoscode/spring-boot-security-course
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //Only disable if absolutely needed (if using global method security)
                .authorizeRequests()
                .antMatchers("/","/api/user/forgotPassword").permitAll() //White list
                //.antMatchers("/api/**").hasRole(EMPLOYEE.name()) //protected APIs
                .anyRequest().authenticated()
                .and().httpBasic()
                    //.defaultSuccessUrl("/greeting", true)
                    /*.loginPage("/login").permitAll().defaultSuccessUrl("/someendpoint", true) //for redirect after sucsessful login*/
                //.and().rememberMe() //remember session
                .and().logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
   }
}
