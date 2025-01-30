package com.codify.ioio.Security;

import com.codify.ioio.Model.TblUsers;
import com.codify.ioio.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetails(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);

        Optional<TblUsers> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            logger.warn("No user found with username: {}", username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        TblUsers userObj = user.get();

        logger.info("User found, checking password encoding...");






        if (!userObj.getPassword().startsWith("$2a$")) {
            logger.info("Password is plain text, encoding...");
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            userRepo.save(userObj);
        } else {
            logger.info("Password already encoded.");
        }

        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .build();
    }}
