package com.lambdaschool.todos.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service(value = "todosService")
public class TodoServiceImpl
		implements TodoService {

	//	@Autowired
	//	private TodoRepo todoRepo;
	@Autowired
	private UserAuditing userAuditing;


	@Transactional
	@Override
	public void markComplete(long todoid) {}

}
