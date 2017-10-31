package com.vidarramdal.krinken.proxy;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Parent;
import org.jdom.filter.Filter;

import java.util.Iterator;
import java.util.List;

public interface XmlElement extends org.jdom.Parent {

    org.jdom.Element getXML();

    @Override
    default int getContentSize() {
        return this.getXML().getContentSize();
    };

    @Override
    default int indexOf(Content child) {
        return this.getXML().indexOf(child);
    }

    @Override
    default List cloneContent() {
        return this.getXML().cloneContent();
    }

    @Override
    default Content getContent(int index) {
        return this.getXML().getContent(index);
    }

    @Override
    default List getContent() {
        return this.getXML().getContent();
    }

    @Override
    default List getContent(Filter filter) {
        return this.getXML().getContent(filter);
    }

    @Override
    default List removeContent() {
        return this.getXML().removeContent();
    }

    @Override
    default List removeContent(Filter filter) {
        return this.getXML().removeContent(filter);
    }

    @Override
    default boolean removeContent(Content child) {
        return this.getXML().removeContent(child);
    }

    @Override
    default Content removeContent(int index) {
        return this.getXML().removeContent(index);
    }

    @Override
    default Object clone() {
        return this.getXML().clone();
    }

    @Override
    default Iterator getDescendants() {
        return this.getXML().getDescendants();
    }

    @Override
    default Iterator getDescendants(Filter filter) {
        return this.getXML().getDescendants(filter);
    }

    @Override
    default Parent getParent() {
        return this.getXML().getParent();
    }

    @Override
    default Document getDocument() {
        return this.getXML().getDocument();
    }
}
