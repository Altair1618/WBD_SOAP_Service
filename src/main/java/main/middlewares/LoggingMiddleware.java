package main.middlewares;

import com.sun.net.httpserver.HttpExchange;
import javax.xml.ws.handler.MessageContext;

import main.models.Logging;
import main.repositories.LoggingRepository;

public class LoggingMiddleware extends Middleware {
    private static LoggingRepository repository = new LoggingRepository();
    private String description;
    private String IP;
    private String endpoint;

    public LoggingMiddleware(MessageContext context, String description, String endpoint) {
        HttpExchange exchange = (HttpExchange) context.get("com.sun.xml.internal.ws.http.exchange");
        
        this.IP = exchange.getRemoteAddress().getAddress().toString();
        this.description = description;
        this.endpoint = endpoint;
    }

    @Override
    public void handle() {
        Logging logging = new Logging(this.description, this.IP, this.endpoint);
        repository.addLogging(logging);
    }
}
