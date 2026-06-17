package com.noahbelton29.minecraft.renderer;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void draw(Mesh mesh, ShaderProgram shader) {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }
}
