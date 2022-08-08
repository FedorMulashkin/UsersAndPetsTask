package com.fedormulashkin.usersandpetstask.component;

import com.fedormulashkin.usersandpetstask.entity.User;
import com.fedormulashkin.usersandpetstask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

/**
 * Класс, позволяющий аутентифицироваться отдельным пользователям.
 * Метод attemptsCounter подсчитывает количество попыток входа в аккаунт и
 * задает время ожидания, в случае исчерпания всех попыток.
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    private static int attempts = 10;
    private static Instant start;
    private static Instant finish;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (Exception e) {
            attemptsCounter();
            throw new BadCredentialsException("Invalid username or password");
        }
        if ((user != null && user.getUsername().equals(username)) && attemptsCounter()) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            resetAttempts();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    private void resetAttempts() {
        attempts = 10;
        start = null;
    }

    private boolean attemptsCounter() {
        attempts--;
        if (start == null || Duration.between(start, finish).toMinutes() > 60) {
            start = Instant.now();
            finish = Instant.now();
            attempts = 10;
        }
        if (attempts <= 0 && Duration.between(start, finish).toMinutes() < 60) {
            System.out.println();
            finish = Instant.now();
            System.out.println();
            throw new BadCredentialsException("You doesn't have more attempts\n" + "Please wait " + (60 - Duration.between(start, finish).toMinutes()) + " minutes");
        } else {
            System.out.println("You have " + attempts + " attempts");
            return true;
        }
    }

    public boolean supports(Class<?> arg) {
        return true;
    }
}