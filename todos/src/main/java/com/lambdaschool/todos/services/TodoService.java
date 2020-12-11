package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;



public interface TodoService
{


    /**
     * Set the todoItem with the given todoid as complete
     * @param todoid The todoid (long) of the todoItem to update
     */
    void markComplete(long todoid);
}
