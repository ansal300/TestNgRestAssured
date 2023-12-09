package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonUtils {


    private static ObjectMapper mapper=new ObjectMapper();

    public static Map<String,Object> getJsoDataAsMap(String jsonFileName) throws IOException {
        InputStream stream=ResourceLoader.getResource("src/test/resources"+jsonFileName);
        Map<String,Object>data=mapper.readValue(stream, new TypeReference<>() {
        });

        return data;
    }
}
