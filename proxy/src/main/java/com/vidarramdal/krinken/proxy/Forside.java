package com.vidarramdal.krinken.proxy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Forside {

    @XmlElements({
            @javax.xml.bind.annotation.XmlElement(name="channel", type=Channel.class)
    })
    @XmlElementWrapper
    private List<Channel> channels;
    @XmlElements({
            @javax.xml.bind.annotation.XmlElement(name="plug", type=Plug.class)
    })
    @XmlElementWrapper
    private List<Plug> plugs;

    public Forside() {
    }

    public Forside(List<Channel> channels, List<Plug> plugs) {
        this.channels = channels;
        this.plugs = plugs;
    }

    org.w3c.dom.Document getSource(org.w3c.dom.Document document) {
        try {
            final JAXBContext jaxbc = JAXBContext.newInstance(Forside.class);
            Marshaller marshaller = jaxbc.createMarshaller();
            marshaller.marshal(this, document);
            return document;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
