package com.noahbelton29.minecraft;

import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    static void main() {
        Window window = new Window(800, 600, "Minecraft");
        window.init();

        glClearColor(0.5f, 0.8f, 1.0f, 1.0f);

        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            window.swapAndPoll();
        }

        window.destroy();
    }
}
