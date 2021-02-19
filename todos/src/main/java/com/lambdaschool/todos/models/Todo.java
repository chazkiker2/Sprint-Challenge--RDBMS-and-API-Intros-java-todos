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
	 * Cannot be null.
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
	@Column(columnDefinition = "boolean default false")
	private boolean completed;

	/**
	 * Foreign key — The userid of the user assigned to this todoItem is what is stored in the database.
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


	/**
	 * Getter for todoid
	 *
	 * @return todoid The todoid (long) of the todo item
	 */
	public long getTodoid() {
		return todoid;
	}

	/**
	 * Setter for todoid
	 *
	 * @param todoid The new todoid (long) for the todo item
	 */
	public void setTodoid(long todoid) {
		this.todoid = todoid;
	}

	/**
	 * Getter for description
	 *
	 * @return the description (String) of the todo item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description
	 *
	 * @param description the new description (String) for the todo item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for completed
	 *
	 * @return true (boolean) if the todo is completed, false (booelan) if the todo is incomplete
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Setter for completed
	 *
	 * @param completed true to set the status to complete, false to set incomplete
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * Getter for user
	 * @return The user (User) associated with this todo item
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter for user
	 * @param user The new user (User) to associate with this todo item
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
