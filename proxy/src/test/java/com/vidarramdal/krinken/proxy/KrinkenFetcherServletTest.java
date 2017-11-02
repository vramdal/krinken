package com.vidarramdal.krinken.proxy;

import com.google.appengine.repackaged.com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

import static com.vidarramdal.krinken.proxy.KrinkenFetcherServlet.PATH_XSLT;

public class KrinkenFetcherServletTest {



    @Test
    public void testFetch() throws IOException, TransformerException {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(PATH_XSLT);
        assert fis != null;
        KrinkenFetcherServlet servlet = new KrinkenFetcherServlet();
        Document jsoupDoc = Jsoup.parse(new File("src/main/resources/NRK Radio - forside.htm"), "utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        KrinkenFetcherServlet.StringBufferOutputStream xmlOut = new KrinkenFetcherServlet.StringBufferOutputStream(stringBuffer);
        servlet.transform(xmlOut, jsoupDoc, new Forside(), fis);
        String appTemplateStr = Resources.toString(Resources.getResource("app-template.js"), Charset.forName("UTF-8"));
        String result = appTemplateStr.replace("<!-- INSERT SCRIPT HERE -->", xmlOut.getBuffer().toString());
        System.out.println(result);

    }


    @Test
    public void testJsoupXsl() throws IOException, TransformerException {
        InputStream xslInputStream = this.getClass().getClassLoader().getResourceAsStream("frontpage-selectors.xsl");
        assert xslInputStream != null;
        InputStream htmlStream = this.getClass().getClassLoader().getResourceAsStream("NRK Radio - forside.htm");
        assert htmlStream != null;
        Document jsoupDoc = Jsoup.parse(htmlStream, "utf-8", "/");
        KrinkenFetcherServlet servlet = new KrinkenFetcherServlet();
        ByteArrayOutputStream xmlOut = new ByteArrayOutputStream();

        servlet.transformJsoup(xmlOut, jsoupDoc, xslInputStream);

        String result = xmlOut.toString("utf-8");
        xmlOut.write(result.getBytes("UTF-8"));
        xmlOut.close();
        System.out.println(xmlOut.toString("UTF-8"));
    }


    private static class MockHttpServletResponse implements HttpServletResponse {
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {

                }

                @Override
                public void write(int b) throws IOException {
                    System.out.write(b);
                }
            };
        }

        @Override
        public void setContentType(String type) {
            // Ignore
        }

        @Override
        public void setCharacterEncoding(String charset) {
            // Ignore
        }
        @Override
        public void addCookie(Cookie cookie) {

        }

        @Override
        public boolean containsHeader(String s) {
            return false;
        }

        @Override
        public String encodeURL(String s) {
            return null;
        }

        @Override
        public String encodeRedirectURL(String s) {
            return null;
        }

        @Override
        public String encodeUrl(String s) {
            return null;
        }

        @Override
        public String encodeRedirectUrl(String s) {
            return null;
        }

        @Override
        public void sendError(int i, String s) throws IOException {

        }

        @Override
        public void sendError(int i) throws IOException {

        }

        @Override
        public void sendRedirect(String s) throws IOException {

        }

        @Override
        public void setDateHeader(String s, long l) {

        }

        @Override
        public void addDateHeader(String s, long l) {

        }

        @Override
        public void setHeader(String s, String s1) {

        }

        @Override
        public void addHeader(String s, String s1) {

        }

        @Override
        public void setIntHeader(String s, int i) {

        }

        @Override
        public void addIntHeader(String s, int i) {

        }

        @Override
        public void setStatus(int i) {

        }

        @Override
        public void setStatus(int i, String s) {

        }

        @Override
        public int getStatus() {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Collection<String> getHeaderNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return null;
        }

        @Override
        public void setContentLength(int i) {

        }

        @Override
        public void setContentLengthLong(long l) {

        }

        @Override
        public void setBufferSize(int i) {

        }

        @Override
        public int getBufferSize() {
            return 0;
        }

        @Override
        public void flushBuffer() throws IOException {

        }

        @Override
        public void resetBuffer() {

        }

        @Override
        public boolean isCommitted() {
            return false;
        }

        @Override
        public void reset() {

        }

        @Override
        public void setLocale(Locale locale) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }
    }
}