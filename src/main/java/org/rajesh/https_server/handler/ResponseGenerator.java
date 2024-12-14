package org.rajesh.https_server.handler;
import org.rajesh.https_server.handler.PostRequestHandler;

import java.io.IOException;

public class ResponseGenerator {
    public String generateResponse(String method, String path,String requestBody){
        String httpResponse = "";
        if (method.equals("GET")){
            switch (path) {
                case "/":
                    httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n\r\n" + "Welcome to the server rajesh!!!!";
                    break;
                case "/home":
                    httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n\r\n"+ "Hello this is the home page rajesh!!! level 2 of this project";
                    break;
                case "/next":
                    httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/json\r\n\r\n" + "{\"message\":\"This is mock data\"}";
                    break;
                default:
                    httpResponse = "HTTP/1.1 404 NOT FOUND\r\n" + "Content-Type: text/plain\r\n\r\n"+ "Rajesh the page your are trying to find is not with us unfortunately!! sorry for the inconvenience. try out with any other correct urls as mentioned in the source code";

            }

        }
        else if (method.equals("POST")){
            if(path.equals("/submit")){

                String urlString = "http://localhost:8080" + path;
                PostRequestHandler postRequest = new PostRequestHandler();

                try{
                    String responseFromPostRequest = postRequest.sendPostRequest(urlString,requestBody);
                    httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/json\r\n\r\n" + "{\"message\":\"Data received successfully rajesh\", \"body\": \"" + responseFromPostRequest + "\"}";

                }
                catch (IOException e){
                    httpResponse = "HTTP/1.1 500 INTERNAL SERVER ERROR\r\n" + "Content-Type: text/plain\r\n\r\n" + "Error processing the POST request: " + e.getMessage();
                }

            }
            else{
                httpResponse = "HTTP/1.1 404 NOT FOUND\r\n" + "Content-Type: text/plain\r\n\r\n" + "The POST route is not found for the path: " + path;

            }

        }
        else{
            httpResponse = "HTTP/1.1 405 Method Not Allowed\r\n" + "Content-Type: text/plain\r\n\r\nMethod not allowed!";
        }

        return httpResponse;
    }
}
