package net.croz.exception;

import java.io.IOException;

public class JokeNotFoundException extends IOException {
    
    public JokeNotFoundException(String msg) {
        super("Joke not found: " + msg);
    }
}
