package it.unisalento.tripplanner.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
