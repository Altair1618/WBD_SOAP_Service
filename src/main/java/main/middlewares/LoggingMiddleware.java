package main.middlewares;

import com.sun.net.httpserver.HttpExchange;
import main.repositories.LoggingRepository;
import main.utils.Response;

import javax.xml.ws.handler.MessageContext;
import java.sql.SQLException;

public class LoggingMiddleware {
    private static final LoggingRepository repository = new LoggingRepository();

    public Response addLogging(MessageContext context, String description, String endpoint) {
        try {
            HttpExchange exchange = (HttpExchange) context.get("com.sun.xml.internal.ws.http.exchange");
            String IP = exchange.getRemoteAddress().getAddress().toString();
            repository.addLogging(description, IP, endpoint);

            return new Response(true, "Berhasil Menambahkan Data Logging", null);
        } catch (SQLException e) {
            return new Response(false, "Terjadi Kesalahan Pada Database", null);
        } catch (Exception e) {
            return new Response(false, "Gagal Menambahkan Data Logging", null);
        }
    }
}
