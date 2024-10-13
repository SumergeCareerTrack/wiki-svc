package com.sumerge.careertrack.wiki_svc.exceptions;

public class DoesNotExistException extends WikiException {

    public static final String ARTICLE_ID = "Article with ID \"%s\" does not exist.";

    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(String message) {
        super(message);
    }

    public DoesNotExistException(String message, Object... args) {
        super(String.format(message, args));
    }
}
