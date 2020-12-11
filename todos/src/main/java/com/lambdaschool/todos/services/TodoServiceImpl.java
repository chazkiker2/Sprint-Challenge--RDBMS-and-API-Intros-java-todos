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

	@Autowired
	private TodoRepo todoRepo;

	@Autowired
	private UserAuditing userAuditing;


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
