package com.company.events;

public class Event {

    public enum Type {
        MOUSE_MOVED,
        MOUSE_PRESSED,
        MOUSE_RELEASED
    }

    private Type type;
    boolean handled;

    protected Event(Type type) {
        this.type = type;
    }

    Type getType() {
        return type;
    }

}
