package com.senla.pronych.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
 
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Enumerated(EnumType.STRING)
    private RoleName name;
 
    
    @ManyToMany(mappedBy = "roles")    
    private Set<User> users = new HashSet<>();
    
    public Role() {}
 
    public Role(RoleName name) {
        this.name = name;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public RoleName getName() {
        return name;
    }
 
    public void setName(RoleName name) {
        this.name = name;
    }
}