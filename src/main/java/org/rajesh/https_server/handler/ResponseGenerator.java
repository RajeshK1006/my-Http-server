package org.rajesh.https_server.handler;

public class ResponseGenerator {
    public String generateResponse(String method, String path){
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
                    httpResponse = "HTTP/1.1 404 NOT FOUND\r\n" + "Content-Type: text/plain\r\n\r\n"+ "Rajesh the page your are trying to find is not with us unfortunately!! sorry for the inconvenience. try out with any other correct urls as mentioned in the soource code";
            }

        }
        else{
            httpResponse = "HTTP/1.1 405 Method Not Allowed\r\n" + "Content-Type: text/plain\r\n\r\nMethod not allowed!";
        }

        return httpResponse;
    }
}
