package com.cs.assignment.domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FileEvent {

    private String id;
    private String state;
    private String type;
    private String host;
    private Timestamp timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static List<Event> getEventDuration(List<FileEvent> fileEvents) {

        Map<String, List<FileEvent>> noOfEvents = fileEvents.stream()
                .filter(e -> e.getId()!=null)
                .collect(Collectors.groupingBy(FileEvent::getId));
        //Map<String, Long> durationOfEvents = new HashMap<String, Long>();
        List<Event> flaggedEvent = new ArrayList<Event>();
        for (Map.Entry<String, List<FileEvent>> entry : noOfEvents.entrySet()) {
            String k = entry.getKey();
            List<FileEvent> v = entry.getValue();
            //System.out.println("key: " + k + ", value: " + v);
            flaggedEvent.add(getDuration(v));
            //durationOfEvents.put(k, durationOfEvent);

        }
        return flaggedEvent;
    }
    public static Event getDuration(List<FileEvent> events) {
        long duration;
        boolean alert = false;
        if (events.get(0).getState().equalsIgnoreCase("Finished")) {
            duration = events.get(0).getTimestamp().getTime() - events.get(1).getTimestamp().getTime();
        } else {
            duration = events.get(1).getTimestamp().getTime() - events.get(0).getTimestamp().getTime();
        }
        if (duration>4){
            alert = true;
        }
        Event event = new Event(events.get(0).getId(), events.get(0).getType(), events.get(0).getHost(), alert, duration);
        return event;
    }

    public static Timestamp convertStringToTimestamp(String timestampInString) {
        if (timestampInString != null) {
            Date date = new Date(Long.parseLong(timestampInString));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);
            //System.out.println("timeStamp"+timeStamp);
            return Timestamp.valueOf(formatted);
        } else {
            return null;
        }
    }
}
