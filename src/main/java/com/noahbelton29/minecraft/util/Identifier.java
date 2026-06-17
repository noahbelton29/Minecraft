package com.noahbelton29.minecraft.util;

import org.jetbrains.annotations.NotNull;

public record Identifier(String namespace, String path) {
    public static final String DEFAULT_NAMESPACE = "minecraft";

    public Identifier(String path) {
        this(DEFAULT_NAMESPACE, path);
    }

    public static Identifier of(String id) {
        String[] parts = id.split(":", 2);
        if (parts.length == 2) return new Identifier(parts[0], parts[1]);
        return new Identifier(DEFAULT_NAMESPACE, parts[0]);
    }

    public static Identifier fromNamespaceAndPath(String namespace, String path) {
        return new Identifier(namespace, path);
    }

    public static Identifier fromDefault(String path) {
        return new Identifier(DEFAULT_NAMESPACE, path);
    }

    public String toResourcePath() {
        return "assets/" + namespace + "/" + path;
    }

    @NotNull
    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
