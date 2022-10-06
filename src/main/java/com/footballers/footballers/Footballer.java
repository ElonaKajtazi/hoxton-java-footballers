package com.footballers.footballers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// - Player (id, name, nationality, scoreOutOfTen, isReplacement)
@Entity
public class Footballer {
    @Id
    @GeneratedValue
    public Integer id;
    public String name;
    public String nationality;
    public Integer scoreOutOfTen;
    public Boolean isReplacement;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "teamId", nullable = false)
    // public Team team;

    public Footballer() {
    }
}
