package org.rajesh.https_server.handler;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.BufferedReader;

public class ParseHeaders {
    public Map<String, String> parseHeader(BufferedReader reader) throws IOException{
        Map<String, String> headers = new HashMap<>();
        String line;

        while(!(line = reader.readLine()).isEmpty()){
            String[] headerParts = line.split(": ",2);
            if(headerParts.length ==2){
                headers.put(headerParts[0],headerParts[1]);
            }
        }

        return headers;
    }
}
