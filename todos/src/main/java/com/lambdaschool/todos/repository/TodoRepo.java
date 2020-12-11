package com.lambdaschool.todos.repository;


import com.lambdaschool.todos.models.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface TodoRepo
		extends CrudRepository<Todo, Long> {
//			value = "UPDATE todos " +
//	               "SET completed='true', last_modified_by=:username, last_modified_date=CURRENT_TIMESTAMP " +
//	               "WHERE todoid=:todoid ",
	@Transactional
	@Modifying
	@Query(value="UPDATE todos SET completed='true', last_modified_by=:username, last_modified_date=CURRENT_TIMESTAMP " +
	             "WHERE todoid=:todoid ",
	       nativeQuery = true)
	void markTodoCompleted(
			@Param(value = "todoid")
					long todoid,
			@Param(value = "username")
					String username
	);

}
