package org.rajesh.https_server.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "SERVER.log";

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = timestamp + " - - " + message;


        System.out.println(logMessage);

        try(FileWriter writer = new FileWriter(LOG_FILE,true)){
            writer.write(logMessage + "\n");

        } catch (IOException e) {
            System.err.println("Error writing to log file: "+ e.getMessage());
        }

    }
}
