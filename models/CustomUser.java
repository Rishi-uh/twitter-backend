package twitterproj.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class CustomUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String Name;

    @Column
    private String Password;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdat;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Constructor with no arguments
    public CustomUser() {
    }

    // Constructor with all arguments
    public CustomUser(long id, String username, String email, String Name, String Password,
            LocalDateTime createdat, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.Name = Name;
        this.Password = Password;
        this.createdat = createdat;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return Password;
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

    // Setter methods
    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Getter methods
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return Name;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public Role getRole() {
        return role;
    }

    // toString method
    @Override
    public String toString() {
        return "CustomUser [id=" + id + ", username=" + username + ", email=" + email + ", Name=" + Name + ", Password="
                + Password + ", createdat=" + createdat + ", role=" + role + "]";
    }

    // Builder method
    public static CustomUserBuilder builder() {
        return new CustomUserBuilder();
    }

    // Builder inner class
    public static class CustomUserBuilder {
        private long id;
        private String username;
        private String email;
        private String Name;
        private String Password;
        private LocalDateTime createdat;
        private Role role;

        public CustomUserBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CustomUserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public CustomUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomUserBuilder Name(String Name) {
            this.Name = Name;
            return this;
        }

        public CustomUserBuilder Password(String Password) {
            this.Password = Password;
            return this;
        }

        public CustomUserBuilder createdat(LocalDateTime createdat) {
            this.createdat = createdat;
            return this;
        }

        public CustomUserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public CustomUser build() {
            return new CustomUser(id, username, email, Name, Password, createdat, role);
        }
    }
}
