package com.sumerge.careertrack.wiki_svc.exceptions;

public class WikiException extends RuntimeException {
    public WikiException() {
        super();
    }

    public WikiException(String message) {
        super(message);
    }

    public WikiException(String message, Object... args) {
        super(String.format(message, args));
    }
}
