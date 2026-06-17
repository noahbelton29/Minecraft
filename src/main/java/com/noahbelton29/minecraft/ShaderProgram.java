package com.noahbelton29.minecraft;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programId;

    public ShaderProgram(String vshPath, String fshPath) {
        String vshSource = load(vshPath);
        String fshSource = load(fshPath);

        int vsh = compile(vshSource, GL_VERTEX_SHADER);
        int fsh = compile(fshSource, GL_FRAGMENT_SHADER);

        programId = glCreateProgram();
        glAttachShader(programId, vsh);
        glAttachShader(programId, fsh);
        glLinkProgram(programId);

        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Shader link failed:\n" + glGetProgramInfoLog(programId));
        }

        glDeleteShader(vsh);
        glDeleteShader(fsh);
    }

    private int compile(String source, int type) {
        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        if (glGetShaderi(id, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Failed to compile shader:\n" + glGetShaderInfoLog(id));
        }

        return id;
    }

    private String load(String path) {
        try {
            var url = getClass().getClassLoader().getResource(path);
            if (url == null) throw new RuntimeException("Shader not found: " + path);
            return Files.readString(Paths.get(url.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        glDeleteProgram(programId);
    }
}
