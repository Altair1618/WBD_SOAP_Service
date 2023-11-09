package main.utils;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ListWrapper<T> {
    @XmlElement
    private final List<T> item;

    public ListWrapper() {
        this.item = new ArrayList<>();
    }

    public ListWrapper(List<T> item) {
        this.item = item;
    }
}
