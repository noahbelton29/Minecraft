package com.noahbelton29.minecraft.util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String loadResource(Identifier id) {
        return loadResource(id.toResourcePath());
    }

    public static String loadResource(String path) {
        try {
            var url = FileUtil.class.getClassLoader().getResource(path);
            if (url == null) throw new RuntimeException("Resource not found: " + path);
            return Files.readString(Paths.get(url.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}