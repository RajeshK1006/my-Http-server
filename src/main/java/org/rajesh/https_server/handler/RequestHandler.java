package org.rajesh.https_server.handler;

import org.rajesh.https_server.util.Logger;
import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import org.rajesh.https_server.handler.ResponseGenerator;


public class RequestHandler implements  Runnable{

    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    @Override
    public void run(){
        try(
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream())
            {
                BufferedReader reader = new BufferedReader((new InputStreamReader(input)));
                String requestLine = reader.readLine();
                Logger.log("Request: " + requestLine);

                ExtractTokens extractor = new ExtractTokens();
//                demo request line be like GET /(path)/HTTP 1.1 (version)

                String[] extracts = extractor.extractTokens(requestLine);

                String method = extracts[0];
                String path =  extracts[1];
                String httpVersion = extracts[2];

                ParseHeaders parser = new ParseHeaders();

                Map<String, String> header = parser.parseHeader(reader);

                RequestBody body = new RequestBody();

                String requestBody = body.parseRequestBody(method,header,reader);

                ResponseGenerator generator = new ResponseGenerator();

                String httpResponse = generator.generateResponse(method,path,requestBody);

                output.write(httpResponse.getBytes());

            }

        catch (IOException e){
            Logger.log("Error handling client: --> " + e.getMessage());
        }

        finally {
            try{
                clientSocket.close();
            }
            catch(IOException e){
                Logger.log("Error closing client socket: "+ e.getMessage());
            }
        }
    }
}
