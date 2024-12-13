package org.rajesh.https_server;

import org.rajesh.https_server.core.HttpServer;

public class MainServer {
    public static void main(String[] args) {
        int port = 8080;

        HttpServer server  = new HttpServer(port);

        try{
            server.start();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
