package com.vidarramdal.krinken.proxy;

import com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.vidarramdal.krinken.proxy.KrinkenFetcherServlet.PATH_XSLT;

public class KrinkenFetcherServletTest {

    @Test
    public void testFetch() throws IOException, TransformerException {
/*
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(PATH_XSLT);
        assert fis != null;
        KrinkenFetcherServlet servlet = new KrinkenFetcherServlet();
        Document jsoupDoc = Jsoup.parse(new File("src/test/resources/NRK Radio - forside.htm"), "utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        KrinkenFetcherServlet.StringBufferOutputStream xmlOut = new KrinkenFetcherServlet.StringBufferOutputStream(stringBuffer);
        servlet.transform(xmlOut, jsoupDoc, fis);
        String appTemplateStr = Resources.toString(Resources.getResource("app-template.js"), Charset.forName("UTF-8"));
        String result = appTemplateStr.replace("<!-- INSERT SCRIPT HERE -->", xmlOut.getBuffer().toString());
        System.out.println(result);
*/

    }


}