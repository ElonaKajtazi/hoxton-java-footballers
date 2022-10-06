package com.footballers.footballers;



import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Team {
    @Id
    @GeneratedValue
    public Integer id;
    public String name;

    @OneToMany(mappedBy = "team")
    public Set<Footballer> footballers;

    public Team() {
    }
}
