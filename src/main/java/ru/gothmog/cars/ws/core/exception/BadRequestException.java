package ru.gothmog.cars.ws.core.exception;

import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    @Transient
    private final Object fieldValue;

    public BadRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s bad request with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
