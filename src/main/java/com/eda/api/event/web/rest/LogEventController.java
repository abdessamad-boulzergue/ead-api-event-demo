package com.eda.api.event.web.rest;

import com.eda.api.event.domain.model.LogEvent;
import com.eda.api.event.domain.usecase.SearchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class LogEventController {

    @Autowired
    SearchEvent searchEvent;

    @GetMapping
    @PreAuthorize("hasAuthority('scope:read')")
    public List<LogEvent> getEvents(){
       return searchEvent.search(LocalDateTime.now().minusDays(1),LocalDateTime.now());
    }
    @GetMapping("/test")
    public List<LogEvent> test(){
       return searchEvent.search(LocalDateTime.now().minusDays(1),LocalDateTime.now());
    }
}
