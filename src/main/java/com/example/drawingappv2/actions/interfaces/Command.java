package com.example.drawingappv2.actions.interfaces;

import java.io.IOException;

public interface Command {
    String execute() throws IOException;
    void undo();
    void redo();
}
