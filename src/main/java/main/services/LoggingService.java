package main.services;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import main.middlewares.AuthMiddleware;
import main.middlewares.LoggingMiddleware;
import main.models.Logging;
import main.repositories.LoggingRepository;
import main.utils.Response;

@WebService
public class LoggingService {
    private static final LoggingRepository repository = new LoggingRepository();

    @Resource
    private WebServiceContext context;

    @WebMethod
    public Response getAllLoggings() {
        MessageContext messageContext = context.getMessageContext();

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Get All Loggings", "LoggingService");

        if (logResponse.getStatus().equals("error")) return logResponse;

        try {
            List<Logging> data = repository.getAllLoggings();
            String message = "Berhasil Mendapatkan Data Loggings";
            return new Response(true, message, data);
        } catch (Exception e) {
            String message = "Gagal Mendapatkan Data Loggings";
            return new Response(false, message, null);
        }
    }
}
