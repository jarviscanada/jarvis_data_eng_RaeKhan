package ca.jrvs.apps.twitter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JSONParser {
    public static String toJSON(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException{
        ObjectMapper m = new ObjectMapper();
        if (!includeNullValues) {
            m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if (prettyJson) {
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return m.writeValueAsString(object);
    }

    /**
     * Parse JSON string to an object
     * @param json JSON str
     * @param clazz object class
     * @param <T> Type
     * @return Object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException{
        ObjectMapper m = new ObjectMapper();
        return (T) m.readValue(json, clazz);
    }

// throws IOException is for failure in input and output operations
    public static void main(String[] args) throws IOException{
        Company company = toObjectFromJson(companyStr, Company.class);
        System.out.println(toJson(company, true, false));
    }
}
