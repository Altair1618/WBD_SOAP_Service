package main.utils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {
    private final String status;
    private final String message;
    private final Object data;

    public Response(boolean isSuccess, String message, Object data) {
        this.status = isSuccess ? "success" : "error";
        this.message = message;
        this.data = data;
    }
}
