package com.pharvinex.pharvinexGroup.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create a new event
    @PostMapping("/admin/create-event")
    public ResponseEntity<Event> createEvent(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile) {
        try {
            log.info("Creating event with name: " + name);
            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            Event createdEvent = eventService.createEvent(event, imageFile);
            return ResponseEntity.ok(createdEvent);
        } catch (IOException e) {
            log.error("Error creating event: " + e.getMessage(), e);
            return ResponseEntity.status(500).build();  // Handle error
        }
    }

    // Get all events
    @GetMapping("/admin/list-event")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get a specific event by ID
    @GetMapping("/admin/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing event by ID
    @PutMapping("/admin/update-event/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile) {
        try {
            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            Event updatedEvent = eventService.updateEvent(id, event, imageFile);
            if (updatedEvent != null) {
                return ResponseEntity.ok(updatedEvent);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();  // Handle error
        }
    }

    // Delete an event by ID
    @DeleteMapping("admin/delete-event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // Get all events
    @GetMapping("/public/list-event")
    public List<Event> getAllViewEvents() {
        return eventService.getAllEvents();
    }
}