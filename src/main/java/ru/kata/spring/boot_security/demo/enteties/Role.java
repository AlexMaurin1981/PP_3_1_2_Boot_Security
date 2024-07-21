package ru.kata.spring.boot_security.demo.enteties;



import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "role")
public class Role implements GrantedAuthority {
 @Id
 private Long id;
 private String nameRole;
@ManyToMany (mappedBy = "roles")

 private Set<User> users;

 public Set<User> getUsers() {
  return users;
 }

 public void setUsers(Set<User> users) {
  this.users = users;
 }

 public Role() {

 }

 public Role(Long id) {
  this.id = id;
 }

 public Role(Long id, String nameRole) {
  this.id = id;
  this.nameRole = nameRole;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getNameRole() {
  return nameRole;
 }

 public void setNameRole(String nameRole) {
  this.nameRole = nameRole;
 }

 public Set<User> getUserSet() {
  return users;
 }

 public void setUserSet(Set<User> userSet) {
  this.users = users;
 }


 @Override
 public String getAuthority() {
  return nameRole;
 }

 @Override
 public String toString() {
  return this.nameRole;
 }
}

