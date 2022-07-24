package com.cs.assignment;

import com.cs.assignment.domain.Event;
import com.cs.assignment.domain.FileEvent;
import com.cs.assignment.service.EventService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class EventlogparserApplication {
	private static final Logger logger = LoggerFactory.getLogger(EventlogparserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EventlogparserApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EventService eventService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<FileEvent>> typeReference = new TypeReference<List<FileEvent>>() {};


			try{
				String logpath = args[0];
				logger.info("Reading LogFile: "+logpath);
				InputStream inputStream = TypeReference.class.getResourceAsStream(logpath);

				List<FileEvent> fileEvents = mapper.readValue(inputStream, typeReference);
				List<Event> events = FileEvent.getEventDuration(fileEvents);
				eventService.save(events);
				logger.info("Flagged Events Saved!");
			} catch (Exception e) {
				logger.error("Unable to save events: "+e.getMessage());
			}
		};
	}
}
