package main.middlewares;

import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import main.utils.Response;

import javax.xml.ws.handler.MessageContext;

public class AuthMiddleware {
    private static final List<String> apiKeys = new ArrayList<>();

    public AuthMiddleware() {
        if (apiKeys.isEmpty()) {
            apiKeys.add(System.getenv("PHP_APP_API_KEY"));
            apiKeys.add(System.getenv("REST_SERVICE_API_KEY"));
        }
    }

    public Response authenticate(MessageContext context) {
        HttpExchange exchange = (HttpExchange) context.get("com.sun.xml.internal.ws.http.exchange");
        String apiKey = exchange.getRequestHeaders().getFirst("api-key");

        if (apiKey != null && apiKeys.contains(apiKey)) return new Response(true, "Berhasil Melakukan Autentikasi", null);
        else return new Response(false, "Gagal Melakukan Autentikasi", null);
    }
}