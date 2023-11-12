package main.services;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import main.middlewares.AuthMiddleware;
import main.middlewares.LoggingMiddleware;
import main.models.Subscription;
import main.repositories.SubscriptionRepository;
import main.utils.ListWrapper;
import main.utils.Response;

@WebService
public class SubscriptionService {
    private static final SubscriptionRepository repository = new SubscriptionRepository();

    @Resource
    private WebServiceContext context;

    @WebMethod
    public Response createSubscriptionRequest(@WebParam(name = "userId") Integer userId) {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Create Subscription Request", "SubscriptionService");
        if (logResponse.getStatus().equals("error")) return logResponse;

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;

        List<Subscription> data;
        try {
            data = repository.getSubscriptionByUserId(userId);
        } catch (Exception e) {
            String message = "Gagal Membuat Permintaan Subscription";
            return new Response(false, message, null);
        }

        if (data != null && !data.isEmpty()) {
            if (data.get(0).getStatus() == Subscription.Status.PENDING) {
                String message = "User Sudah Melakukan Permintaan Subscription";
                return new Response(false, message, null);
            }
        }

        try {
            repository.createSubscriptionRequest(userId);
            String message = "Berhasil Membuat Permintaan Subscription";
            return new Response(true, message, null);
        } catch (Exception e) {
            String message = "Gagal Membuat Permintaan Subscription";
            return new Response(false, message, null);
        }
    }

    @WebMethod
    public Response getAllSubscriptionRequests() {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Get All Subscription Requests", "SubscriptionService");
        if (logResponse.getStatus().equals("error")) return logResponse;

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;

        try {
            List<Subscription> data = repository.getAllSubscriptionRequests();
            String message = "Berhasil Mendapatkan Data Subscription Requests";
            return new Response(true, message, new ListWrapper<>(data));
        } catch (Exception e) {
            String message = "Gagal Mendapatkan Data Subscription Requests";
            return new Response(false, message, null);
        }
    }

    @WebMethod
    public Response getUserSubscriptionStatus(@WebParam(name = "userId") Integer userId) {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Get User Subscription Status", "SubscriptionService");
        if (logResponse.getStatus().equals("error")) return logResponse;

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;
        
        List<Subscription> data;

        try {
            data = repository.getSubscriptionByUserId(userId);
        } catch (Exception e) {
            String message = "Gagal Mendapatkan Status Subscription";
            return new Response(false, message, null);
        }

        if (data == null || data.isEmpty()) {
            String message = "User Belum Melakukan Permintaan Subscription";
            return new Response(true, message, null);
        }

        if (data.get(0).getStatus() == Subscription.Status.ACCEPTED) {
            if (data.get(0).getExpiredAt().getTime() < new java.util.Date().getTime()) {
                try {
                    repository.updateSubscriptionStatus(data.get(0).getId(), Subscription.Status.EXPIRED);
                } catch (Exception e) {
                    String message = "Gagal Mendapatkan Status Subscription";
                    return new Response(false, message, null);
                }

                data.get(0).setStatus(Subscription.Status.EXPIRED);
                String message = "Subscription User Expired";
                return new Response(true, message, data.get(0));
            }
        }

        String message;
        switch (data.get(0).getStatus()) {
            case PENDING:
                message = "Permintaan Subscription User Sedang Diproses";
                return new Response(true, message, data.get(0));
            case ACCEPTED:
                message = "Subscription User Aktif";
                return new Response(true, message, data.get(0));
            case REJECTED:
                message = "Permintaan Subscription User Ditolak";
                return new Response(true, message, data.get(0));
            case EXPIRED:
                message = "Subscription User Expired";
                return new Response(true, message, data.get(0));
            default:
                message = "User Belum Melakukan Permintaan Subscription";
                return new Response(true, message, null);
        }
    }

    @WebMethod
    public Response acceptSubscriptionRequest(@WebParam(name = "id") Integer id) {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Accept Subscription Request", "SubscriptionService");
        if (logResponse.getStatus().equals("error")) return logResponse;

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;

        List<Subscription> data;
        try {
            data = repository.getSubscriptionByUserId(id);
        } catch (Exception e) {
            String message = "Gagal Menerima Permintaan Subscription";
            return new Response(false, message, null);
        }

        if (data == null || data.isEmpty()) {
            String message = "User Belum Melakukan Permintaan Subscription";
            return new Response(false, message, null);
        }

        if (data.get(0).getStatus() != Subscription.Status.PENDING) {
            String message = "Status Subscription User Bukan PENDING";
            return new Response(false, message, null);
        }

        try {
            repository.updateSubscriptionStatus(id, Subscription.Status.ACCEPTED);
            String message = "Berhasil Menerima Permintaan Subscription";
            return new Response(true, message, null);
        } catch (Exception e) {
            String message = "Gagal Menerima Permintaan Subscription";
            return new Response(false, message, null);
        }
    }

    @WebMethod
    public Response rejectSubscriptionRequest(@WebParam(name = "id") Integer id) {
        MessageContext messageContext = context.getMessageContext();

        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();
        Response logResponse = loggingMiddleware.addLogging(messageContext, "Reject Subscription Request", "SubscriptionService");
        if (logResponse.getStatus().equals("error")) return logResponse;

        AuthMiddleware authMiddleware = new AuthMiddleware();
        Response authResponse = authMiddleware.authenticate(messageContext);
        if (authResponse.getStatus().equals("error")) return authResponse;

        List<Subscription> data;
        try {
            data = repository.getSubscriptionByUserId(id);
        } catch (Exception e) {
            String message = "Gagal Menolak Permintaan Subscription";
            return new Response(false, message, null);
        }

        if (data == null || data.isEmpty()) {
            String message = "User Belum Melakukan Permintaan Subscription";
            return new Response(false, message, null);
        }

        if (data.get(0).getStatus() != Subscription.Status.PENDING) {
            String message = "Status Subscription User Bukan PENDING";
            return new Response(false, message, null);
        }

        try {
            repository.updateSubscriptionStatus(id, Subscription.Status.REJECTED);
            String message = "Berhasil Menolak Permintaan Subscription";
            return new Response(true, message, null);
        } catch (Exception e) {
            String message = "Gagal Menolak Permintaan Subscription";
            return new Response(false, message, null);
        }
    }
}
