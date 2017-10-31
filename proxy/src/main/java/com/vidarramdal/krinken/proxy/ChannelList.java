package com.vidarramdal.krinken.proxy;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@XmlRootElement
public class ChannelList<T> extends ArrayList<T> {

    public ChannelList(Collection<? extends T> c) {
        super(c);
    }
}
