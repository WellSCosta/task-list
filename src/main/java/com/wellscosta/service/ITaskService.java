package com.wellscosta.service;

public interface ITaskService<T> {

    void saveTask(T t);
    T updateTask(T t);
    void removeTask(Long id);

    void setCompleteTask(Long id);
    void setCancelTask(Long id);
    void setTaskForNextDay(Long id);
}
