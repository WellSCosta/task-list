package com.wellscosta.service;

import java.util.Date;
import java.util.List;

import com.wellscosta.model.entities.Task;

public class TaskListService implements ITaskListService{
    
    @Override
    public List<Task> searchListByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTaskByDate(Date date, Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTask(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}