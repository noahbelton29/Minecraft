package com.noahbelton29.minecraft.renderer;

import com.noahbelton29.minecraft.util.FileUtil;
import com.noahbelton29.minecraft.util.Identifier;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programId;

    public ShaderProgram(Identifier vshPath, Identifier fshPath) {
        int vsh = compile(FileUtil.loadResource(vshPath), GL_VERTEX_SHADER);
        int fsh = compile(FileUtil.loadResource(fshPath), GL_FRAGMENT_SHADER);

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
