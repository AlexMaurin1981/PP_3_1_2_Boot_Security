package ru.kata.spring.boot_security.demo.enteties;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column
   private String username;
   private String password;
   @Column
   private String firstName;

   @Column
   private String lastName;

   @Column
   private String email;


   public Collection<Role> getRoles() {
      return roles;
   }


   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )

   private Collection<Role> roles;

   public User() {
   }



   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setRoles(Collection<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", roles=" + roles +
              '}';
   }
}



   
