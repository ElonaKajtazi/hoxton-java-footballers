package com.footballers.footballers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootballerController {
    @Autowired
    private FootballerRepository footballerRepository;

    @GetMapping("/footballers")
    public List<Footballer> getAllFootballers() {
        return footballerRepository.findAll();
    }

    @PostMapping("/footballers")
    public Footballer createFootballer(@RequestBody Footballer footballerData) {
        return footballerRepository.save(footballerData);
    }
}

interface FootballerRepository extends JpaRepository<Footballer, Integer> {
}