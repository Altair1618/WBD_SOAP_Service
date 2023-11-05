package main.services;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import main.middlewares.LoggingMiddleware;
import main.models.Logging;
import main.repositories.LoggingRepository;

@WebService
public class LoggingService {
    private static LoggingRepository repository = new LoggingRepository();

    @Resource
    private WebServiceContext context;

    @WebMethod
    public List<Logging> getAllLoggings() {
        MessageContext messageContext = context.getMessageContext();
        LoggingMiddleware middleware = new LoggingMiddleware(messageContext, "Get All Loggings", "LoggingService");
        middleware.handle();

        try {
            return repository.getAllLoggings();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
