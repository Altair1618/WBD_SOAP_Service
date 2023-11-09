package main.services;

import main.middlewares.AuthMiddleware;
import main.middlewares.LoggingMiddleware;
import main.models.Logging;
import main.repositories.LoggingRepository;
import main.utils.ListWrapper;
import main.utils.Response;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

@WebService
public class LoggingService {
    private static final LoggingRepository repository = new LoggingRepository();

    @Resource
    private WebServiceContext context;

    @WebMethod
    public Response getAllLoggings() {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Get All Loggings", "LoggingService");
        if (logResponse.getStatus().equals("error")) {
            System.out.println("Log Error: " + logResponse.getMessage());
            return logResponse;
        }

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) {
            System.out.println("Auth Error: " + authResponse.getMessage());
            return authResponse;
        }

        try {
            List<Logging> data = repository.getAllLoggings();
            String message = "Berhasil Mendapatkan Data Loggings";
            System.out.println(message);
            return new Response(true, message, new ListWrapper<>(data));
        } catch (Exception e) {
            String message = "Gagal Mendapatkan Data Loggings";
            System.out.println(message);
            return new Response(false, message, null);
        }
    }
}
