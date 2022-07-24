package com.cs.assignment.service;

import com.cs.assignment.domain.Event;
import com.cs.assignment.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Iterable<Event> list() {
        return eventRepository.findAll();
    }

    public Event save(Event event){
        return eventRepository.save(event);
    }
    public Iterable<Event> save(List<Event> events) {
        //getEventDuration(events);

        return eventRepository.saveAll(events);
    }
}
