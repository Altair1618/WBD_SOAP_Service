package main.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

import java.util.Date;

@Getter
@XmlRootElement(name = "subscription")
public class Subscription {
    public enum Status {
        PENDING, ACCEPTED, REJECTED, EXPIRED
    }

    @XmlElement
    private Integer id;
    @XmlElement
    private Integer userId;
    @XmlElement
    private Status status;
    @XmlElement
    private Date expiredAt;
    @XmlElement
    private Date createdAt;

    public Subscription() {}

    public Subscription(Integer id, Integer userId, Status status, Date expiredAt, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
