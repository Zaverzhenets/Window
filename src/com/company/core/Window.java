package com.company.core;

import com.company.events.Event;
import com.company.events.types.MouseMotionEvent;
import com.company.events.types.MousePressedEvent;
import com.company.events.types.MouseReleasedEvent;
import com.company.layers.Layer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {

    private Screen screen;
    private List<Layer> layers = new ArrayList<>();

    public Window(String name, int width, int height) {
        screen = new Screen(width, height);

        init(name);

        screen.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }

        });

        screen.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), false);
                onEvent(event);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), true);
                onEvent(event);
            }
        });

        screen.init();
        render();
    }

    private void init(String name) {
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(screen);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void render() {
        screen.beginRendering();
        screen.clear();
        onRender(screen.getGraphicsObject());
        screen.endRendering();
        try {
            Thread.sleep(3);
        } catch (InterruptedException ignored) {

        }

        EventQueue.invokeLater(this::render);
        // SwingUtilities.invokeLater(this::render);
    }

    private void onRender(Graphics g) {
        for (Layer layer : layers) layer.onRender(g);
    }

    private void onEvent(Event event) {
        for (int i = layers.size() - 1; i >= 0; i--)
            layers.get(i).onEvent(event);
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

}
