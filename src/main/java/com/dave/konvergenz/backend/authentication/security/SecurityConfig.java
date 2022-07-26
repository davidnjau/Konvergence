package com.dave.konvergenz.backend.authentication.security;

import com.dave.konvergenz.LoginResponse;
import com.dave.konvergenz.backend.authentication.entity.Role;
import com.dave.konvergenz.backend.authentication.entity.StaffDetails;
import com.dave.konvergenz.backend.authentication.filter.JwtTokenAuthenticationFilter;
import com.dave.konvergenz.backend.authentication.filter.JwtTokenFilter;
import com.dave.konvergenz.backend.authentication.filter.JwtTokenStore;
import com.dave.konvergenz.backend.authentication.service_class.impl.StaffDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenStore jwtTokenStore;
    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsServiceImpl;


    public SecurityConfig(JwtTokenStore jwtTokenStore, JwtTokenFilter jwtTokenFilter ) {
        this.jwtTokenStore = jwtTokenStore;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.addFilterBefore( jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class );
        http.addFilterBefore( jwtTokenFilter, JwtTokenAuthenticationFilter.class );
        http.csrf().disable().authorizeRequests()
                .antMatchers(GET, "/static/**").permitAll()
                .antMatchers(GET, "/assets/**").permitAll()
                .antMatchers(GET, "/register*").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/reports/import-excel").permitAll()
                .antMatchers("/api/v1/idle-texter/**").permitAll()
                .antMatchers("/api/v1/call-back/**").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin().loginPage("/login")
                .permitAll();

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(GET, "/static/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login")
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint( this::commence )
//                .and()
//                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
    }

    private void commence(HttpServletRequest request, HttpServletResponse response,
                          AuthenticationException authException) throws IOException, ServletException {

        response.setContentType( "application/json" );
        response.setStatus(403);
        response.getWriter().write( "{\"details\": \"User is not Authenticated\"}" );
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() throws Exception {
        JwtTokenAuthenticationFilter filter = new JwtTokenAuthenticationFilter();
        filter.setAuthenticationManager( authenticationManagerBean() );
        filter.setAuthenticationSuccessHandler(this::onAuthenticationSuccess  );
        filter.setAuthenticationFailureHandler( this::onAuthenticationFailure );
        filter.setFilterProcessesUrl("/api/v1/auth/login");
        return filter;
    }

    private void onAuthenticationSuccess(HttpServletRequest request,
                                         HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        try {
            String token = jwtTokenStore.generateToken( authentication );
            String emailAddress = authentication.getName();
            StaffDetails userDetails = staffDetailsServiceImpl.getStaffDetailsByEmailAddress(emailAddress);
            String userId = userDetails.getUserId();
            String firstName = userDetails.getFirstName();
            String lastName = userDetails.getLastName();
            String phoneNumber = userDetails.getPhoneNumber();

            LoginResponse loginResponse = new LoginResponse(
                    token, userId, firstName, emailAddress, lastName, phoneNumber,

                    userDetails.getRolesCollection().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList())

            );


            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);




        } catch ( Exception e ) {
            e.printStackTrace();

        }
    }

    private void onAuthenticationFailure(HttpServletRequest request,
                                         HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType( "application/json" );
        response.setStatus(400);
        response.getWriter().write( "{\"details\": \"" + exception.getMessage() +"\" }" );
    }
}
