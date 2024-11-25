package com.pharvinex.pharvinexGroup.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            event.setImg(imageBytes);
        }
        log.info("Saving event: " + event.getName());
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        log.info("Fetching all events...");
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(int id) {
        log.info("Fetching event with ID: " + id);
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event updateEvent(int id, Event event, MultipartFile imageFile) throws IOException {
        log.info("Updating event with ID: " + id);
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent != null) {
            existingEvent.setName(event.getName());
            existingEvent.setDescription(event.getDescription());

            if (imageFile != null && !imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                existingEvent.setImg(imageBytes);
            }
            return eventRepository.save(existingEvent);
        }
        return null;
    }

    @Override
    public void deleteEvent(int id) {
        log.info("Deleting event with ID: " + id);
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllViewEvents() {
        log.info("Fetching all events...");
        return eventRepository.findAll();
    }
}
