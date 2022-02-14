package com.antsiferov.thirdtask.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUser implements UserDetails {

    private final Collection<SimpleGrantedAuthority> rolesCollection;
    private Integer id;
    private String name;
    private String email;
    private String lastLogin;
    private String firstLogin;
    private String status;
    private String password;
    private String role;

    public CustomUser(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstLogin = user.getFirstLogin();
        this.status = user.getStatus();
        this.lastLogin = user.getLastLogin();
        this.role = user.getRole();
        this.rolesCollection = Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesCollection;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
}
