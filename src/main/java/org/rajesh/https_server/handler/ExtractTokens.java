package org.rajesh.https_server.handler;

public class ExtractTokens {
    public String[] extractTokens(String request){
        String[] parts = request.split(" ");

        String method = parts[0];
        String path = parts[1];
        String httpVersion = parts[2];

        return new String[] {method,path,httpVersion};
    }
}
