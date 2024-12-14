package org.rajesh.https_server.handler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;


public class PostRequestHandler {
    public String sendPostRequest(String urlString, String jsonInput ) throws IOException{
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setDoOutput(true);

        connection.setRequestProperty("Content-Type","application/json");

        try(OutputStream os = connection.getOutputStream()){
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input,0,input.length);
        }

        StringBuilder response = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8))){
            String responseLine;
            while ((responseLine = br.readLine()) != null){
                response.append(responseLine.trim());
            }
        }

        return response.toString();


    }
}
