package com.codify.ioio.Controller;

import com.codify.ioio.Commons.ApiResponse;
import com.codify.ioio.Model.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    private final AuthenticationManager authenticationManager;

    public SecurityController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password())
            );
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            // Return a success response
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .responseCode("200")
                            .responseMessage("Logged successfully")
                            .build()
            );
        } catch (Exception e) {
            // In case of failure, return an error message
            return ResponseEntity.internalServerError().body(
                    ApiResponse.builder()
                            .responseCode("401")
                            .responseMessage(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/hi")
    public String hello() {
        return "hello";
    }
}
