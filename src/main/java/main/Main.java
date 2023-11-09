package main;

import main.services.LoggingService;
import main.services.SubscriptionService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://0.0.0.0:9000/logging", new LoggingService());
            Endpoint.publish("http://0.0.0.0:9000/subscription", new SubscriptionService());
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println("Error occured in server");
            System.out.println(e.getMessage());
        }
    }
}
