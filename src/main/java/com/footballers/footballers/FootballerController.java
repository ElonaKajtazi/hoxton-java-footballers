package com.footballers.footballers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping("/footballers/{id}")
    public ResponseEntity<Reply> deleteFootballer(@PathVariable Integer id) {
        Reply reply = new Reply();

        if (footballerRepository.existsById(id)) {
            footballerRepository.deleteById(id);
            reply.message = "It worked!";
            return new ResponseEntity<>(reply, HttpStatus.OK);
        } else {
            reply.error = "Not found";
            return new ResponseEntity<>(reply, HttpStatus.NOT_FOUND);
        }
    }


}

class Reply {
    public String message;
    public String error;
}

interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}