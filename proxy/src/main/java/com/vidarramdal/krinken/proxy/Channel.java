package com.vidarramdal.krinken.proxy;

import org.apache.xerces.dom.DocumentFragmentImpl;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

    @XmlAttribute
    private String title;

    private Element svgLogo;

    public Channel(Element element) {
        this.title = Selector.select("a.channel-link", element).attr("title");
        this.svgLogo = Selector.select("svg", element).first();
/*
        this.element = new org.jdom.Element("Channel")
                .addContent(new org.jdom.Element("title").addContent(this.title))
                .addContent(new org.jdom.Element("svgLogo").addContent(svgLogo.toString()));
*/
    }

    public Channel() {
    }

    public String getTitle() {
        return title;
    }

    public Element getSvgLogo() {
        return svgLogo;
    }

    public Node getSource(Document document) {
        try {
            final JAXBContext jaxbc = JAXBContext.newInstance(Channel.class);
            Marshaller marshaller = jaxbc.createMarshaller();
            marshaller.marshal(this, document);
            return document;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

}
