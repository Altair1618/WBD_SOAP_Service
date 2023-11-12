package main.utils;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ListWrapper<T> {
    @XmlElement
    private final List<T> list;

    public ListWrapper() {
        this.list = new ArrayList<>();
    }

    public ListWrapper(List<T> list) {
        this.list = list;
    }
}
