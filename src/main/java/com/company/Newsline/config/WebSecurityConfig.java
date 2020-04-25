package com.company.Newsline.config;

import com.company.Newsline.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] UNSECURED_RESOURCE_LIST = new String[]{
            "/resources/**", "/css/**", "/images/**", "/js/**",
            "/static/**"};

    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //Access only for unauthorized users
                .antMatchers("/registration").not().fullyAuthenticated()
                //Access only for users which have Admin role
                .antMatchers("/news/newsV2/**").hasRole("ADMIN")
                .antMatchers("/news/newsFullVersion/**").hasRole("ADMIN")
                .antMatchers("/news/newsSaving/**").hasRole("ADMIN")
                .antMatchers("/news/delete/**").hasRole("ADMIN")
                //Access for everyone
                .antMatchers("/", "/resources/**").permitAll()
                .antMatchers("/news/newsV2ForAll/**").permitAll()
                .antMatchers("/news/newsFullVersionForAll/**").permitAll()
                .antMatchers("/news/searchForAll/**").permitAll()
                //Another pages require authentication
                .anyRequest().authenticated()
                .and()
                //Setting for logging in
                .formLogin()
                .loginPage("/login")
                //Redirecting to the main page after successful logging in
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }


}
