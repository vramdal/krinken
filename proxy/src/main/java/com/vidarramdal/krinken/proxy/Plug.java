package com.vidarramdal.krinken.proxy;


import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@XmlRootElement
public class Plug {

    private  String description;
    private String programnavn;
    private String tittel;
    private String relativBildeUrl;
    private String relativLink;

    public Plug() {
    }

    public Plug(Element context) {
        //Selector.select(".listobject", element);
        this.relativLink = Selector.select("a.listobject-link", context).attr("href");
        this.relativBildeUrl = Selector.select("figure img", context).attr("src");
        this.programnavn = Selector.select(".listobject-title", context).text();
        this.tittel = Selector.select(".title", context).text();
        this.description = Selector.select("listobject-description", context).text();
    }

    public String getProgramnavn() {
        return programnavn;
    }

    public String getTittel() {
        return Objects.equals(tittel, "") ? null : tittel;
    }

    public String getDescription() {
        return description;
    }

    public URL getBildeUrl(URL baseURL) throws MalformedURLException {
        return new URL(baseURL, this.relativBildeUrl);
    }

    public URL getLink(URL baseURL) throws MalformedURLException {
        return new URL(baseURL, this.relativLink);
    }
}
