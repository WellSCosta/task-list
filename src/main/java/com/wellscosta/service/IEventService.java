package com.wellscosta.service;

import com.wellscosta.model.entities.Event;

public interface IEventService extends ITaskService<Event>{
    void setEventLateById(Long id);
}
