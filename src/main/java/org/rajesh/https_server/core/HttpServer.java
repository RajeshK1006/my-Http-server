package org.rajesh.https_server.core;

import org.rajesh.https_server.util.Logger;
import org.rajesh.https_server.handler.RequestHandler;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class HttpServer {
    private final int port;

    public HttpServer(int port){
        this.port = port;
    }


    public  void start() throws IOException{
        Logger.log("rajesh! Starting server on port " + port);

        try(ServerSocket serverSocket = new ServerSocket(port)){
            Logger.log("Server is running on port "+ port);

            while(true){
                Socket clientSocket = serverSocket.accept();
                Logger.log("rajesh Client is connected successfully "+ clientSocket.getInetAddress());

                new Thread(new RequestHandler(clientSocket)).start();
            }
        }
        catch (IOException e) {
            Logger.log("Error starting the server: " + e.getMessage());
            throw e;
        }
    }

}
