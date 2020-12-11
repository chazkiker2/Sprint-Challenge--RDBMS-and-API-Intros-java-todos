package com.lambdaschool.todos.repository;


import com.lambdaschool.todos.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface TodoRepo
		extends CrudRepository<Todo, Long> {
	@Query(value = "UPDATE todos " +
	               "SET completed=true, last_modified_by=:username, last_modified_date=CURRENT_TIME " +
	               " todoid=:todoid ",
	       nativeQuery = true)
	void markTodoCompleted(
			@Param(value = "todoid")
					long todoid,
			@Param(value = "username")
					String username
	);

}
