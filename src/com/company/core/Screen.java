package com.company.core;

import java.awt.*;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
class Screen extends Canvas {

    private BufferStrategy bs;
    private Graphics g;

    Screen(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    void init() {
        if (bs == null)
            createBufferStrategy(3);
    }

    void beginRendering() {
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    void clear() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    Graphics getGraphicsObject() {
        return g;
    }

    void endRendering() {
        g.dispose();
        bs.show();
    }

}
