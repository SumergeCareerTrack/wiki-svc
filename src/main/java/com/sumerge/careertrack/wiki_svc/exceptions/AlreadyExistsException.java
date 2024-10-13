package com.sumerge.careertrack.wiki_svc.exceptions;

public class AlreadyExistsException extends WikiException {

    public static final String ARTICLE_ID = "Article with ID \"%s\" already exists.";

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Object... args) {
        super(String.format(message, args));
    }
}
