package com.wellscosta.service;

import java.util.Date;
import java.util.List;

import com.wellscosta.model.entities.Task;

public interface ITaskListService {
    
    List<Task> searchListByDate(Date date);

    void addTaskByDate(Date date, Task task);

    void removeTask(Long id);
}
