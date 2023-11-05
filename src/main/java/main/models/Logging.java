package main.models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Logging {
    private @NonNull Integer id;
    private @NonNull String description;
    private @NonNull String IP;
    private @NonNull String endpoint;
    private @NonNull Date requested_at;

    public Logging(String description, String IP, String endpoint) {
        this.id = -1;
        this.description = description;
        this.IP = IP;
        this.endpoint = endpoint;
        this.requested_at = new Date();
    }

    public Logging(int id, String description, String IP, String endpoint, Date requested_at) {
        this.id = id;
        this.description = description;
        this.IP = IP;
        this.endpoint = endpoint;
        this.requested_at = requested_at;
    }
}
