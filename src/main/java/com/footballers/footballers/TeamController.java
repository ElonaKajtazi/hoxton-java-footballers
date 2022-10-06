package com.footballers.footballers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/teams")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @PostMapping("/teams")
    public Team creatTeam (@RequestBody Team teamData) {
        return teamRepository.save(teamData);
    }
}


interface TeamRepository extends JpaRepository <Team, Integer>{}
