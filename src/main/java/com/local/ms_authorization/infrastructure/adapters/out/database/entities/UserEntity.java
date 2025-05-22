package com.local.ms_authorization.infrastructure.adapters.out.database.entities;

import com.local.ms_authorization.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_rol", nullable = false)
    private String userRol;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String firstname;
    private String lastname;

    @Transient // <-- ¡Esta es la anotación clave!
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    @Transient // <-- ¡Esta es la anotación clave!
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRol));
    }

    @Override
    public String getUsername() {
        return username;
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
