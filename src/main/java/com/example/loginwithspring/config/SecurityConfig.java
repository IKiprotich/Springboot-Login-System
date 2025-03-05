package com.example.loginwithspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@EnableWebSecurity
@Controller
public class SecurityConfig {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        UserDetails ian = User.builder()
                .username("Ian")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build();
        this.userDetailsManager = new InMemoryUserDetailsManager(ian);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/register", "/change-password").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    // Password change endpoints
    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("newPassword") String newPassword) {
        if (userDetailsManager.userExists("Ian")) {
            UserDetails updatedIan = User.withUsername("Ian")
                    .password(passwordEncoder.encode(newPassword))
                    .roles("ADMIN")
                    .build();
            userDetailsManager.updateUser(updatedIan);
            return "redirect:/login?passwordChanged";
        }
        return "redirect:/change-password?error";
    }

    // Home page endpoint
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("username", "Ian"); // You might want to dynamically get the logged-in user later
        model.addAttribute("message", "Welcome to your dashboard!");
        return "home";
    }

    // Registration endpoints
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // New register.html template
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        if (userDetailsManager.userExists(username)) {
            model.addAttribute("error", "Username already taken!");
            return "register";
        }
        UserDetails newUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER") // Default role for new users
                .build();
        userDetailsManager.createUser(newUser);
        return "redirect:/login?registered"; // Redirect to login after registration
    }
}