package com.java26.eolt.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private Long id;
    private String message;

    public EntityNotFoundException(Long id) {
        super("Entity with id " + id + " not found");
        this.id = id;
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}