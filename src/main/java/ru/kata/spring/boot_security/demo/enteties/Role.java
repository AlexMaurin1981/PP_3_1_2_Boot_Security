package ru.kata.spring.boot_security.demo.enteties;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table (name = "role")
public class Role implements GrantedAuthority {
 @Id
 @GeneratedValue (strategy = GenerationType.IDENTITY)
 private Long id;
 @Column
 private String nameRole;

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





 @Override
 public String getAuthority() {
  return getNameRole();
 }
}

