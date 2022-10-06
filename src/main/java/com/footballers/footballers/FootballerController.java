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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    @PatchMapping("/footballers/{id}")

    public Footballer updateFootballersScore(@RequestBody Footballer userSentData, @PathVariable Integer id) {
        Optional<Footballer> match = footballerRepository.findById(id);

        if (match.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        Footballer footballer = match.get();
        footballer.isReplacement = userSentData.isReplacement == null ? footballer.isReplacement
                : footballer.isReplacement;
        footballer.name = userSentData.name == null ? footballer.name : footballer.name;

        footballer.nationality = userSentData.nationality == null ? footballer.nationality : footballer.nationality;
        footballer.scoreOutOfTen = userSentData.scoreOutOfTen == null ? footballer.scoreOutOfTen
                : userSentData.scoreOutOfTen;
                
        return footballerRepository.save(footballer);
    }
}
// @PatchMapping("/zoo-animals/{id}")
// public ZooAnimal updateZooAnimal(@RequestBody ZooAnimal userSentData,
// @PathVariable Integer id) {
// Optional<ZooAnimal> match = zooAnimalRepo.findById(id);

// if (match.isEmpty()) {
// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find
// resource");
// }

// ZooAnimal zooAnimal = match.get();
// zooAnimal.isHungry = userSentData.isHungry == null ? zooAnimal.isHungry :
// userSentData.isHungry;
// zooAnimal.name = userSentData.name == null ? zooAnimal.name :
// userSentData.name;
// zooAnimal.species = userSentData.species == null ? zooAnimal.species :
// userSentData.species;
// zooAnimal.origin = userSentData.origin == null ? zooAnimal.origin :
// userSentData.origin;

// return zooAnimalRepo.save(zooAnimal);
// }



class Reply {
    public String message;
    public String error;
}

interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}

interface TeamRepository extends JpaRepository<Team, Integer> {
}