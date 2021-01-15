//package ru.rcaltd.lightm.entities;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "users", schema = "lightm")
//@EqualsAndHashCode(of = {"id"})
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq-gen")
//    @Column(name = "id", nullable = false, insertable = true, updatable = true)
//    private Long id;
////
////    @Column(name = "user_uuid")
////    private UUID userUuid;
//
//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "enabled")
//    private boolean enabled;
//
//    @Column(name = "surname")
//    private String surname;
//
//    @Column(name = "firstname")
//    private String firstname;
//
//    @Column(name = "secondname")
//    private String secondname;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private List<Role> roles;
//
//    public User() {
//    }
//
//    public User(Long id, String username, String password, boolean enabled, List<Role> roles) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.roles = roles;
//    }
//}