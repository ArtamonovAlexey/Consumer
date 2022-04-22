package ru.artamonov.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import ru.artamonov.consumer.entity.Worker;

import java.util.ArrayList;
import java.util.List;

public class JsonConvertor {

    public static List<String> getObjectsAsString(String jsonAsArray) {
        List<String> listOfObjectsAsString = new ArrayList<>();

        int counterOfCurlyBraces = 0;

        int beginPositionOfObject = 0;
        int endPositionOfObject = 0;

        String objectAsString;

        for (int i = 0; i < jsonAsArray.length(); i++) {
            if (jsonAsArray.charAt(i) == '{') {
                counterOfCurlyBraces++;

                if (counterOfCurlyBraces == 1) {
                    beginPositionOfObject = i;
                }
            }

            if (jsonAsArray.charAt(i) == '}') {
                counterOfCurlyBraces--;

                if (counterOfCurlyBraces == 0) {
                    endPositionOfObject = i + 1;
                }
            }

            if ((jsonAsArray.charAt(i) != ' ') && (jsonAsArray.charAt(i) != ',') && (jsonAsArray.charAt(i) != '[')
                    && (jsonAsArray.charAt(i) != ']') && (counterOfCurlyBraces == 0)) {
                objectAsString = jsonAsArray.substring(beginPositionOfObject, endPositionOfObject);
                listOfObjectsAsString.add(objectAsString);
            }
        }

        return listOfObjectsAsString;
    }

    public static Object convertTo(String json, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Object();
    }

}
