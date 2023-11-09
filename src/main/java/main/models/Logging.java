package main.models;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@XmlRootElement(name = "logging")
public class Logging {
    @XmlElement
    private Integer id;
    @XmlElement
    private String description;
    @XmlElement
    private String IP;
    @XmlElement
    private String endpoint;
    @XmlElement
    private Date requestedAt;

    public Logging() {}

    public Logging(int id, String description, String IP, String endpoint, Date requestedAt) {
        this.id = id;
        this.description = description;
        this.IP = IP;
        this.endpoint = endpoint;
        this.requestedAt = requestedAt;
    }
}
