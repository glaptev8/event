package ru.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "app_user")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(
    name = "username",
    nullable = false,
    unique = true
  )
  private String username;

  @Column(
    name = "phone",
    nullable = false
  )

  private String phone;

  @Column(name = "active")
  private boolean active;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "creater")
  private Set<UserImages> avatars;

  @Column(
    name = "password",
    nullable = false
  )
  private String password;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "creater")
  private Set<Event> createEvent = new LinkedHashSet<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_event",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id"))
  private Set<Event> allEvent = new LinkedHashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet());
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive();
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive();
  }

  @Override
  public boolean isEnabled() {
    return isActive();
  }

}
