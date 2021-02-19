package com.lambdaschool.todos;


import com.github.javafaker.Faker;
import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Random;



/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
		implements CommandLineRunner {
	/**
	 * Connects the user service to this process
	 */
	@Autowired
	UserService userService;

	/**
	 * Generates test, seed data for our application
	 * First a set of known data is seeded into our database.
	 * Second a random set of data using Java Faker is seeded into our database.
	 * Note this process does not remove data from the database. So if data exists in the database
	 * prior to running this process, that data remains in the database.
	 *
	 * OPTION to generate 100 more random Users with JavaFaker towards the bottom
	 *
	 * @param args The parameter is required by the parent interface but is not used in this process.
	 */
	@Transactional
	@Override
	public void run(String[] args)
			throws
			Exception {
		User u1 = new User(
				"admin",
				"password",
				"admin@lambdaschool.local"
		);
		u1.getTodos()
		  .add(new Todo(
				  u1,
				  "Give Joe access rights"
		  ));
		u1.getTodos()
		  .add(new Todo(
				  u1,
				  "Change the color of the home page"
		  ));

		userService.save(u1);

		User u2 = new User(
				"cinnamon",
				"1234567",
				"cinnamon@lambdaschool.local"
		);
		u2.getTodos()
		  .add(new Todo(
				  u2,
				  "Take a nap"
		  ));
		u2.getTodos()
		  .add(new Todo(
				  u2,
				  "Rearrange my hutch"
		  ));
		u2.getTodos()
		  .add(new Todo(
				  u2,
				  "Groom my fur"
		  ));
		userService.save(u2);

		// user
		User u3 = new User(
				"barnbarn",
				"ILuvM4th!",
				"barnbarn@lambdaschool.local"
		);
		u3.getTodos()
		  .add(new Todo(
				  u3,
				  "Rearrange my hutch"
		  ));
		userService.save(u3);

		User u4 = new User(
				"puttat",
				"password",
				"puttat@school.lambda"
		);
		userService.save(u4);

		User u5 = new User(
				"misskitty",
				"password",
				"misskitty@school.lambda"
		);
		userService.save(u5);

		/**
		 * Set the below "false" to true to create 100 users with JavaFaker
		 *
		 * All users will follow this structure:
		 * String username — a randomly generated username (format: `first.last`)
		 * String password — a password set to "password" — hard coded, not randomly generated
		 * String primaryemail — a randomly generated email address (format: first.last@gmail.com)
		 *
		 * Random() will generate a random number `n` [0, 4) — (i.e., 0, 1, 2, or 3)
		 * JavaFaker will randomly generate `n` [0, 4) todos (Todo) and add said todo to the
		 * fake user's todoList
		 *
		 *
		 *
		 */
		if (false) {
			// utilities for generating random seed data
			Random rand      = new Random();
			Faker  nameFaker = new Faker(new Locale("en-US"));


			for (int i = 0; i < 100; i++) {
				User fakeUser = new User(
						nameFaker.name()
						         .username(),
						"password",
						nameFaker.internet()
						         .emailAddress()
				);
				int numTodos = rand.nextInt(4);
				for (int j = 0; j < numTodos; j++) {
					fakeUser.getTodos()
					        .add(new Todo(
							        fakeUser,
							        nameFaker.chuckNorris()
							                 .fact()
					        ));
				}
				userService.save(fakeUser);

			}
		}
	}

}