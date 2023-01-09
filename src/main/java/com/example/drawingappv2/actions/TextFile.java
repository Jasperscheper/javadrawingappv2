package com.example.drawingappv2.actions;

public class TextFile {
    private String path;

    public TextFile(String filePath){
        this.path = filePath;
    }

    public String getPath() {
        return path;
    }

    public String open() {
        return "Opening file " +path;
    }
}
