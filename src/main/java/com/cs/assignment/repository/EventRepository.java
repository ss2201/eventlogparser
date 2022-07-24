package com.cs.assignment.repository;

import com.cs.assignment.domain.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    //List<Event> findByDuration(String id, String state);


}
