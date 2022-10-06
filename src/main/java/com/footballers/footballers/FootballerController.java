package com.footballers.footballers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootballerController {
    @Autowired
    private FootballerRepository footballerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/footballers")
    public List<Footballer> getAllFootballers() {
        return footballerRepository.findAll();
    }

    @PostMapping("teams/{teamId}/footballers")
    public Footballer createFootballer(@RequestBody Footballer footballerData, @PathVariable Integer teamId) {
        footballerData.team = teamRepository.findById(teamId).get();
        return footballerRepository.save(footballerData);
    }
}

interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}