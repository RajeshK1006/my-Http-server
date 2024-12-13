package org.rajesh.https_server.handler;

import org.rajesh.https_server.util.Logger;
import java.io.*;
import java.net.Socket;

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

                String httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n\r\n" +
                        "Hello World rajesh!!!";

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
