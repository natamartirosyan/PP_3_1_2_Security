package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "[a-zA-Z_0-9]+", message = "Name can contain only letters and/or numbers")
    @Size(min = 1, max = 50, message = "Name should be between 1 and 50 characters")
    private String name;

    @Column(name = "lastName")
    @NotBlank(message = "Lastname cannot be empty")
    @Pattern(regexp = "[a-zA-Z_0-9]+", message = "Lastname can contain only letters and/or numbers")
    @Size(min = 1, max = 50, message = "Name should be between 1 and 50 characters")
    private String lastName;

    @Column(name = "yearOfBirth")
    @NotNull(message = "Please enter correct year of birth")
    @Min(value = 1900, message = "Not less than 1900")
    @Max(value = 2005)
    private int yearOfBirth;

    @NotNull(message = "Please enter your email")
    @Column(name = "login", unique = true)
    private String login;

    @NotNull(message = "Please enter your password")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn (name="user_id"),
            inverseJoinColumns = @JoinColumn (name="role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String name, String lastName, int yearOfBirth, String login, String password, List<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", login='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && yearOfBirth == user.yearOfBirth && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(login, user.login) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, yearOfBirth, login, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
