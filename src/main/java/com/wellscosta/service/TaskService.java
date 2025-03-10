package com.wellscosta.service;

import java.util.List;

import com.wellscosta.dao.ITaskDao;
import com.wellscosta.model.entities.Task;

public class TaskService implements ITaskService<Task> {

    ITaskDao dao;

    public TaskService(ITaskDao dao) {
        this.dao = dao;
    }

    @Override
    public void saveTask(Task task) {
        List<Task> tasks = dao.searchByName(task.getName());

        //TODO: create exception class for verification
        if (tasks.stream().anyMatch(x -> x.getName().equalsIgnoreCase(task.getName()))) {
            throw new RuntimeException();
        }

        dao.save(task);
    }

    @Override
    public Task updateTask(Task t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTask(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCompleteTask(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCancelTask(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTaskForNextDay(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
