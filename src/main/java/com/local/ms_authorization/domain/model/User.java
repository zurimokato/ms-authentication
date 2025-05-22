package com.local.ms_authorization.domain.model;

import com.local.ms_authorization.domain.enums.StatusEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private String id;
    private String email;
    private String password;
    private String role;
    private String accessToken;
    private String refreshToken;
    private StatusEnum status;
    private String firstname;
    private String lastname;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority(role) );
    }

    @Override
    public String getUsername() {
        return email;
    }
}
