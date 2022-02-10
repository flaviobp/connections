package com.pingr.Connections.core;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Account {
    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friend_relation",
            joinColumns = @JoinColumn(name = "fk_id_user", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_id_friend", referencedColumnName = "id", nullable = false)
    )
    private Set<Account> friends;

    public Account() {
    }

    public Account(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }

    public Set<Account> getFriends() {
        return friends;
    }

    public void setFriends(Set<Account> friends) {
        this.friends = friends;
    }
}
