package com.pharvinex.pharvinexGroup.event;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    Event createEvent(Event event, MultipartFile imageFile) throws IOException;
    List<Event> getAllEvents();
    Event getEventById(int id);
    Event updateEvent(int id, Event event, MultipartFile imageFile) throws IOException;
    void deleteEvent(int id);
    List<Event> getAllViewEvents();
}