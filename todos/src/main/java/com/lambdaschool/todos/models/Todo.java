package com.lambdaschool.todos.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;



/**
 * The entity allowing interaction with the todos table
 */
@Entity
@Table(name = "todos")
public class Todo
		extends Auditable {
	/**
	 * The primary key (long) of the todos table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todoid;

	/**
	 * The description (String) of the todo item.
	 * Cannot be null.
	 */
	@Column(nullable = false)
	private String description;

	/**
	 * The status of the todo (boolean).
	 * True if complete, false if incomplete.
	 * All todos initialize with completed=false.
	 */
	@Column
	private boolean completed;

	/**
	 * The userid of the user assigned to this todoItem is what is stored in the database.
	 * This is the entire user object!
	 * <p>
	 * Forms a Many to One relationship between todos and users.
	 * A user can have many todos.
	 */
	@ManyToOne
	@JoinColumn(name = "userid",
	            nullable = false)
	@JsonIgnoreProperties(value = "todos",
	                      allowSetters = true)
	private User user;

	/**
	 * Default constructor required by JPA
	 */
	public Todo() {}

	/**
	 * Given the parameters, create a new todo object.
	 * completed (boolean) will initialize to false
	 *
	 * @param user        the user (User) assigned to the todo
	 * @param description the description (String) for the new todo
	 */
	public Todo(
			User user,
			String description
	) {
		this.user        = user;
		this.description = description;
		this.completed   = false;
	}


	public long getTodoid() {
		return todoid;
	}

	public void setTodoid(long todoid) {
		this.todoid = todoid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
