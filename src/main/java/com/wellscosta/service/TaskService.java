package com.wellscosta.service;

import com.wellscosta.model.entities.Task;

public class TaskService implements ITaskService<Task>{

    @Override
    public void saveTask(Task t) {
        throw new UnsupportedOperationException("Not supported yet.");
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
