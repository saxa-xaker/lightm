//package ru.rcaltd.lightm.entities;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Data
//@Entity
//@Table(name = "role", schema = "lightm")
//public class Role implements GrantedAuthority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq-gen")
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    public Role() {
//    }
//
//    public String getAuthority() {
//        return name;
//    }
//}
