package com.footballers.footballers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// - Start a new Maven + Spring project and install the necessary dependencies ✅
// - Create Classes for:
//   - Player (id, name, nationality, scoreOutOfTen, isReplacement) ✅
//   - Team (id, name) ✅
// - Seed the data to have at least 2-3 teams. ✅
// - Write class methods:
//   - Get the team with all its players ✅
//   - Get a single player ✅
//   - Add a person to a team ✅
//   - Remove a person from a team ✅ (Not sure if deleting a footballer is considered removing him form the team, but let's consider it done for now!)
//   - Change a player's scoreOutOfTen (if they improved in practce)
//   - Get the total score of a team. Do not include the replacement in the score(should we store it in a variable? If so why? If no, why not?)
//   - Given 2 team id's find out which one would win (based on total score) and respond with a nice message to the user like "The Legless just got pwned hard by Estonia's Grandma Coalition"
// - Create controllers for the above methods to work.


@SpringBootApplication
public class FootballersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballersApplication.class, args);
		System.out.println("hello");
	}

}
