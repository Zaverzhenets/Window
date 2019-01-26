package com.company;

import java.awt.Color;

import com.company.core.Window;
import com.company.sandbox.Example;

public class Main {

    public static void main(String[] args) {
        Window window = new Window("Window", 740, 460);
        window.addLayer(new Example("Bottom-2, Layer-3", new Color(0x2233CC)));
        window.addLayer(new Example("Bottom-1, Layer-2", new Color(0xCC2233)));
        window.addLayer(new Example("Top, Layer-1", Color.GREEN));
    }

}
