package com.example.bonjour20.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;

    public CustomUserDetails(com.example.bonjour20.entities.User user) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir le rôle en GrantedAuthority
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + user.getAuthorities())
        );
    }

    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Spring Security utilise "username" mais on utilise l'email
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Méthode utilitaire pour obtenir l'objet User complet
    public com.example.bonjour20.entities.User getUser() {
        return user;
    }
}
