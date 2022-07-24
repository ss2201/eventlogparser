package com.cs.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@Data
@Entity
public class Event implements Serializable {
    @Id
    private String id;
    private String type;
    private String host;
    private boolean alert;
    private long duration;

    public Event() {
    }
}
