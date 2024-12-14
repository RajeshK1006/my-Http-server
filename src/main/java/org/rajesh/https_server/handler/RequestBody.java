package org.rajesh.https_server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class RequestBody {
    public String parseRequestBody(String method, Map<String, String> headers, BufferedReader reader) throws IOException {
        String requestBody = "";
        if(method.equals("POST") && headers.containsKey("Content-Length")) {
            try {

                int contentLength = Integer.parseInt(headers.get("Content-Length"));

                char[] body = new char[contentLength];

                int charsread = reader.read(body, 0, contentLength);

                requestBody = new String(body, 0, charsread);
            }

            catch (NumberFormatException e){
                throw new IOException("Invalid Content-Length value: "+ headers.get("Content-length"));
            }
        }

        return requestBody;
    }
}
