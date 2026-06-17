package com.noahbelton29.minecraft;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Minecraft {
    private final Window window;

    public Minecraft() {
        window = new Window(800, 600, "Minecraft");
    }

    public void run() {
        window.init();

        glClearColor(0.5f, 0.8f, 1.0f, 1.0f);

        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            window.swapAndPoll();
        }

        window.destroy();
    }
}
