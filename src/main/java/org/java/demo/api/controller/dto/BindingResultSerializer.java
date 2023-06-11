package org.java.demo.api.controller.dto;

import java.io.IOException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BindingResultSerializer extends JsonSerializer<BindingResult> {

    @Override
    public void serialize(BindingResult value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray(); // Start writing an array

        for (ObjectError error : value.getAllErrors()) {
            gen.writeStartObject(); // Start writing an object

            gen.writeStringField("defaultMessage", error.getDefaultMessage());
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                gen.writeStringField("field", fieldError.getField());
            }

            gen.writeEndObject(); // End writing the object
        }

        gen.writeEndArray(); // End writing the array
    }
}
