package com.company.sandbox;

import com.company.events.Event;
import com.company.events.Dispatcher;
import com.company.events.types.MouseMotionEvent;
import com.company.events.types.MousePressedEvent;
import com.company.events.types.MouseReleasedEvent;
import com.company.layers.Layer;

import java.awt.*;
import java.util.Random;

public class Example extends Layer {

    private String name;
    private Color color;
    private Rectangle rect;

    private boolean dragging = false;

    private int px, py;

    private static final Random random = new Random();

    public Example(String name, Color color) {
        this.name = name;
        this.color = color;

        rect = new Rectangle(random.nextInt(200) + 125, random.nextInt(100) + 100, 120, 100);
    }

    public void onEvent(Event event) {
        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> (onPressed((MousePressedEvent) e)));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> (onReleased((MouseReleasedEvent) e)));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> (onMoved((MouseMotionEvent) e)));
    }

    public void onRender(Graphics g) {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.WHITE);
        g.drawString(name, rect.x + 5, rect.y + 15);
    }

    private boolean onPressed(MousePressedEvent event) {
        if (rect.contains(new Point(event.getX(), event.getY())))
            dragging = true;
        return dragging;
    }

    private boolean onReleased(MouseReleasedEvent event) {
        dragging = false;
        return false;
    }

    private boolean onMoved(MouseMotionEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (dragging) {
            rect.x += x - px;
            rect.y += y - py;
        }

        px = x;
        py = y;

        return dragging;
    }

}
