package com.noahbelton29.minecraft;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final int vaoId;
    private final int vboId;
    private final int vertexCount;

    public Mesh(float[] vertices) {
        vertexCount = vertices.length / 3;

        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length);
        buffer.put(vertices).flip();

        // create and bind the VAO
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(buffer);
    }

    public void draw() {
        glBindVertexArray(vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
    }

    public void destroy() {
        glDeleteBuffers(vboId);
        glDeleteVertexArrays(vaoId);
    }
}
