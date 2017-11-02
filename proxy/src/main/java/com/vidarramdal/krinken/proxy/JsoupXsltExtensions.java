package com.vidarramdal.krinken.proxy;

import com.vidarramdal.krinken.proxy.jsoupdom.JsoupToDomConverter;
import org.apache.xpath.NodeSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vidarramdal.krinken.proxy.KrinkenFetcherServlet.PATH_XSLT;

@SuppressWarnings("unused")
public class JsoupXsltExtensions {

    Element contextElement;

    public JsoupXsltExtensions() {
        System.out.println("Konstruktør");
        try {
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream(PATH_XSLT);
            assert fis != null;
            KrinkenFetcherServlet servlet = new KrinkenFetcherServlet();
            Document jsoupDoc = Jsoup.parse(new File("src/main/resources/NRK Radio - forside.htm"), "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public org.w3c.dom.traversal.NodeIterator cssSelect(org.apache.xalan.extensions.ExpressionContext context, String selector) throws TransformerException {
        Node contextNode = context.getContextNode();
        String localName = contextNode.getLocalName();
        System.out.println("localName = " + localName);
        String nodeName = contextNode.getNodeName();
        System.out.println("nodeName = " + nodeName);
        int currentNode = context.getXPathContext().getCurrentNode();
        System.out.println("currentNode = " + currentNode);

//        Item contextItem = xPathContext.getContextItem();
//        System.out.println("contextItem = " + contextItem);
        System.out.println("selector = " + selector);
//        return "selector: " + selector;
        return new NodeSet();
    }

    public org.w3c.dom.traversal.NodeIterator cssSelect(org.apache.xalan.extensions.XSLProcessorContext context,
                 org.apache.xalan.templates.ElemExtensionCall extensionElement) {
        Node contextNode = context.getContextNode();
        if (contextNode.getNodeType() != Node.ELEMENT_NODE && contextNode.getNodeType() != Node.DOCUMENT_NODE) {
            throw new IllegalArgumentException("Cannot call cssSelect from a non-element context");
        }
        assert contextNode instanceof org.w3c.dom.Element || contextNode instanceof org.w3c.dom.Document;
        org.w3c.dom.Document ownerDocument;
        org.w3c.dom.Element contextElement;
        if (contextNode instanceof org.w3c.dom.Document) {
            contextElement = null;
            ownerDocument = (org.w3c.dom.Document) contextNode;
        } else {
            contextElement = (org.w3c.dom.Element) contextNode;
            ownerDocument = contextElement.getOwnerDocument();
        }
        String selector = extensionElement.getAttribute("selector");
        Map<org.w3c.dom.Node, Element> domToJsoup = (Map<org.w3c.dom.Node, Element>) ownerDocument.getUserData("domToJsoup");
        Map<Element, org.w3c.dom.Node> jsoupToDom = (Map<Element, org.w3c.dom.Node>) ownerDocument.getUserData("jsoupToDom");
        org.jsoup.nodes.Element jsoupElement = contextElement == null ? domToJsoup.get(ownerDocument) : domToJsoup.get(contextElement);
        NodeSet nodeSet = new NodeSet();
        Selector.select(selector, jsoupElement).stream().map(key -> {
            Node jsoupNode = jsoupToDom.get(key);
            assert jsoupNode != null : "Ingen jsoup-node for " + key;
            return jsoupNode;
        }).forEach(nodeSet::addNode);
        return nodeSet;
    }

    public void close() {
        contextElement = null;
    }

}
