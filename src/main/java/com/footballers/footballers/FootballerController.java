package com.footballers.footballers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/footballers/{id}")
    public ResponseEntity<Footballer> getSingleFootballer(@PathVariable Integer id) {
        Optional<Footballer> match = footballerRepository.findById(id);
        if (match.isPresent()) {
            return new ResponseEntity<>(match.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // @GetMapping("/zoo-animals/{id}")
    // public ResponseEntity<ZooAnimal> getSingleZooAnimal(@PathVariable Integer id)
    // {
    // Optional<ZooAnimal> match = zooAnimalRepo.findById(id);

    // if (match.isPresent()) {
    // return new ResponseEntity<>(match.get(), HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    // }
    // }
}

interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}