package com.proiect.parkinglotproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
@NamedQueries({
        @NamedQuery(name = "UserGroup.findByUsername",
                query = "SELECT ug FROM UserGroup ug WHERE ug.username = :username")
})

public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(name = "usergroup", nullable = false, length = 50)
    private String userGroup;

    public UserGroup() {}

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUserGroup() { return userGroup; }
    public void setUserGroup(String userGroup) { this.userGroup = userGroup; }

}