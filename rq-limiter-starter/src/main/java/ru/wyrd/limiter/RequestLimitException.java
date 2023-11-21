package ru.wyrd.limiter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
public class RequestLimitException extends Exception {
    public RequestLimitException(String sessionId) {
        super(String.format("Request for session '%s' exceeded the limit", sessionId));
    }
}
