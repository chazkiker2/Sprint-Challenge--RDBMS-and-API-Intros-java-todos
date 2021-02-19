package com.lambdaschool.todos.services;


import com.lambdaschool.todos.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;



@Transactional
@Service(value = "todosService")
public class TodoServiceImpl
		implements TodoService {

	/**
	 * Connects this service to the Todos table
	 */
	@Autowired
	private TodoRepo todoRepo;

	/**
	 * Connects this service to the auditing service in order to get current user's username
	 */
	@Autowired
	private UserAuditing userAuditing;


	/**
	 * Set the todoItem with the given todoid as complete
	 * @param todoid The todoid (long) of the todoItem to update
	 */
	@Transactional
	@Override
	public void markComplete(long todoid) {
		String username = userAuditing.getCurrentAuditor()
		                              .orElse("SYSTEM");
		todoRepo.findById(todoid)
		        .orElseThrow(() -> new EntityNotFoundException("Todo with id " + todoid + " Not Found"));
		todoRepo.markTodoCompleted(
				todoid,
				username
		);

		todoRepo.findById(todoid)
		        .orElseThrow(() -> new EntityNotFoundException("Todo with id " + todoid + " Not " + "Found"));
	}

}
