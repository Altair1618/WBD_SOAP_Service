package main.utils;

import lombok.Getter;
import main.models.Logging;
import main.models.Subscription;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "response")
@XmlSeeAlso({ListWrapper.class, Logging.class, Subscription.class})
public class Response {
    @XmlElement @Getter
    private final String status;
    @XmlElement @Getter
    private final String message;
    @XmlElement @Getter
    private final Object data;

    public Response() {
        this(false, "", null);
    }

    public Response(boolean isSuccess, String message, Object data) {
        this.status = isSuccess ? "success" : "error";
        this.message = message;
        this.data = data;
    }
}
