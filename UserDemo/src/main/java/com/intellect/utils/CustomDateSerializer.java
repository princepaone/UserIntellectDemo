package com.intellect.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * @author Pavan
 *
 */
public class CustomDateSerializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
        String date = jsonParser.getText();
        try {
            Date parsedDate = format.parse(date);
            return format.format(parsedDate).toUpperCase();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
