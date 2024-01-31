package com.alisson.userapi.domain.user;

import com.alisson.userapi.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private String userPassword;
    private UserRole role;

    public User(UserDTO user) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {return userPassword;}

    @Override
    public String getUsername() {return userLogin;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
