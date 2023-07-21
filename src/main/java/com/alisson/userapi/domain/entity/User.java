package com.alisson.userapi.domain.entity;

import com.alisson.userapi.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private String userPassword;
    private UserRole role;

    public User() {

    }
    public User(Long id, String userName, String userEmail, String userLogin, String userPassword, UserRole role) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserEmail() {

        return userEmail;
    }

    public void setUserEmail(String userEmail) {

        this.userEmail = userEmail;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public void setUserLogin(String userLogin) {

        this.userLogin = userLogin;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null)
            return false;
        if (getUserEmail() != null ? !getUserEmail().equals(user.getUserEmail()) : user.getUserEmail() != null)
            return false;
        if (getUserLogin() != null ? !getUserLogin().equals(user.getUserLogin()) : user.getUserLogin() != null)
            return false;
        if (getUserPassword() != null ? !getUserPassword().equals(user.getUserPassword()) : user.getUserPassword() != null)
            return false;
        return getRole() != null ? getRole().equals(user.getRole()) : user.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getUserEmail() != null ? getUserEmail().hashCode() : 0);
        result = 31 * result + (getUserLogin() != null ? getUserLogin().hashCode() : 0);
        result = 31 * result + (getUserPassword() != null ? getUserPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // Define as permissões que o usuário ADMIN tera -> permissões de admin e de user
        if(this.role == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userLogin;
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
