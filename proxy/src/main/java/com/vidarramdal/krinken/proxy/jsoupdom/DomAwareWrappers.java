/*
 * The MIT License
 * Copyright © 2009 - 2017 Jonathan Hedley (jonathan@hedley.net)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.vidarramdal.krinken.proxy.jsoupdom;

import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DomAwareWrappers {

/*
    public static class ElementWrapper extends org.jsoup.nodes.Element {

        public ElementWrapper(org.jsoup.nodes.Element element) {
            super(element.tag(), element.baseUri(), element.attributes());
        }

    }

    public static class DocumentWrapper extends org.jsoup.nodes.Document {

        public DocumentWrapper(Document doc) {
            super(doc.baseUri());
        }

        @Override
        public org.jsoup.nodes.Element head() {
            return DomAwareWrappers.wrapElement(super.head());
        }

        @Override
        public org.jsoup.nodes.Element body() {
            return DomAwareWrappers.wrapElement(super.body());
        }

        @Override
        public org.jsoup.nodes.Element createElement(String tagName) {
            return DomAwareWrappers.wrapElement(super.createElement(tagName));
        }

        @Override
        public Document clone() {
            return wrapDocument(super.clone());
        }


        @Override
        public List<Node> childNodes() {
            return super.childNodes().stream().map(node -> {
                if (node instanceof Element) {
                    return wrapElement((Element) node);
                } else {
                    return node;
                }
            }).collect(Collectors.toList());
        }

        @Override
        public List<Node> childNodesCopy() {
            return super.childNodesCopy().stream().map(DomAwareWrappers::wrapNode).collect(Collectors.toList());
        }

        @Override
        protected Node[] childNodesAsArray() {
            Node[] childNodes = super.childNodesAsArray();
            NodeWrapper nodeWrappers[] = new NodeWrapper[childNodes.length];
            for (int i = 0; i < childNodes.length; i++) {
                Node childNode = childNodes[i];
                nodeWrappers[i] = new NodeWrapper(childNode);
            }
            return nodeWrappers;
        }

        @Override
        public Document ownerDocument() {
            return wrapDocument(super.ownerDocument());
        }

        @Override
        public Node traverse(NodeVisitor nodeVisitor) {
            return super.traverse(nodeVisitor);
        }

        @Override
        protected Node doClone(Node parent) {
            return super.doClone(parent);
        }
    }

    public static abstract class NodeWrapper extends Node {



    }

    private static ElementWrapper wrapElement(Element el) {
        return new ElementWrapper(el);
    }


    private static DocumentWrapper wrapDocument(Document doc) {
        return new DocumentWrapper(doc);
    }

    private static NodeWrapper wrapNode(Node node) {
        return new NodeWrapper(node);
    }
*/

}
