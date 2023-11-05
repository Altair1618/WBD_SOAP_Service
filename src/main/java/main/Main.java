package main;

import main.services.LoggingService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://0.0.0.0:9000/ws/logging", new LoggingService());
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Error occured in server");
            System.out.println(e.getMessage());
        }
    }
}
