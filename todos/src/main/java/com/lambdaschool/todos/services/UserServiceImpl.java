package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
//import com.lambdaschool.todos.repository.TodoRepo;
import com.lambdaschool.todos.repository.UserRepo;
import com.lambdaschool.todos.views.UserNameCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;



/**
 * Implements the Userservice Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
		implements UserService {
	/**
	 * Connects this service to the User table.
	 */
	@Autowired
	private UserRepo userRepo;

//		@Autowired
//	  private TodoRepo todoRepo;

	/**
	 * Connects this service to the auditing service in order to get current user name
	 */
	@Autowired
	private UserAuditing userAuditing;

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		/*
		 * findAll returns an iterator set.
		 * iterate over the iterator set and add each element to an array list.
		 */
		userRepo.findAll()
		        .iterator()
		        .forEachRemaining(list::add);
		return list;
	}

	public User findUserById(long id)
			throws
			EntityNotFoundException {
		return userRepo.findById(id)
		               .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
	}

	@Transactional
	@Override
	public void delete(long id) {
		userRepo.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
		userRepo.deleteById(id);
	}

	@Transactional
	@Override
	public User save(User user) {
		User newUser = new User();

		newUser.setUsername(user.getUsername()
		                        .toLowerCase());
		newUser.setPassword(user.getPassword());
		newUser.setPrimaryemail(user.getPrimaryemail()
		                            .toLowerCase());

		if (user.getTodos()
		        .size() > 0) {

			newUser.getTodos()
			       .clear();
			for (Todo td : user.getTodos()) {
				Todo newTodo = new Todo(
						newUser,
						td.getDescription()
				);
				//				newTodo.setDescription(td.getDescription());
				//				newTodo.setUser(newUser);
//				todoRepo.save(newTodo);
				newUser.getTodos()
				       .add(newTodo);
				//		    todoRepo.save(td);
			}
		}

		return userRepo.save(newUser);
	}

	@Override
	public List<UserNameCountTodos> getCountUserTodos() {
		return null;
	}

}
