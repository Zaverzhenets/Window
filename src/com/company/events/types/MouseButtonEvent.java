package com.company.events.types;

import com.company.events.Event;

public class MouseButtonEvent extends Event {

    private int keyCode, x, y;

    MouseButtonEvent(Type type, int keyCode, int x, int y) {
        super(type);
        this.keyCode = keyCode;
        this.x = x;
        this.y = y;
    }

    public int getButton() {
        return keyCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
