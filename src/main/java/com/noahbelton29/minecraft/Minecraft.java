package com.noahbelton29.minecraft;

import com.noahbelton29.minecraft.core.Window;
import com.noahbelton29.minecraft.renderer.Mesh;
import com.noahbelton29.minecraft.renderer.Renderer;
import com.noahbelton29.minecraft.renderer.ShaderProgram;
import com.noahbelton29.minecraft.util.Identifier;

import static org.lwjgl.opengl.GL11.*;

public class Minecraft {
    private final Window window;
    private final Renderer renderer;

    private static final float[] TRIANGLE_VERTICES = {
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    public Minecraft() {
        window = new Window(800, 600, "Minecraft");
        renderer = new Renderer();
    }

    public void run() {
        window.init();

        ShaderProgram shader = new ShaderProgram(
                Identifier.fromDefault("shaders/triangle.vsh"),
                Identifier.fromDefault("shaders/triangle.fsh")
        );
        Mesh mesh = new Mesh(TRIANGLE_VERTICES);

        glClearColor(0.5f, 0.8f, 1.0f, 1.0f);

        while (!window.shouldClose()) {
            renderer.clear();
            renderer.draw(mesh, shader);
            window.swapAndPoll();
        }

        mesh.destroy();
        shader.destroy();
        window.destroy();
    }
}
