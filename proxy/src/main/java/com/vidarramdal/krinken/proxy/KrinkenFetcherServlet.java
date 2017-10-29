package com.vidarramdal.krinken.proxy;

import com.google.appengine.repackaged.com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class KrinkenFetcherServlet extends HttpServlet {

    public static final String PATH_XSLT = "radio-to-atv.xsl";

    public static class StringBufferOutputStream extends OutputStream {
        private StringBuffer buffer;

        public StringBufferOutputStream(StringBuffer out) {
            this.buffer = out;
        }

        public void write(int ch) throws IOException {
            this.buffer.append((char)ch);
        }

        public StringBuffer getBuffer() {
            return buffer;
        }
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                ServletOutputStream outputStream = resp.getOutputStream();
                InputStream xsltStream = this.getClass().getClassLoader().getResourceAsStream(PATH_XSLT);
                InputStream htmlStream = this.getClass().getClassLoader().getResourceAsStream("NRK Radio - forside.htm");
        ){
//            Document jsoupDoc = fetch();
            Document jsoupDoc = Jsoup.parse(htmlStream, "utf-8", "/");
            StringBuffer stringBuffer = new StringBuffer();
            KrinkenFetcherServlet.StringBufferOutputStream xmlOut = new KrinkenFetcherServlet.StringBufferOutputStream(stringBuffer);
            transform(xmlOut, jsoupDoc, xsltStream);
            String appTemplateStr = Resources.toString(Resources.getResource("app-template.js"), Charset.forName("UTF-8"));
            String result = appTemplateStr.replace("<!-- INSERT SCRIPT HERE -->", xmlOut.getBuffer().toString());
            outputStream.write(result.getBytes("UTF-8"));
//            System.out.println(result);

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


        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    Document fetch() throws IOException, TransformerException {
        return Jsoup.connect("https://radio.nrk.no").get();

/*
        Elements plugs = jsoupDoc.select("#main .radio-plugs .listobject");
        for (Element plug : plugs) {
            System.out.println("plug.outerHtml() = " + plug.outerHtml());
        }
*/
    }

    void transform(OutputStream outputStream, Document jsoupDoc, InputStream xsltStream) throws TransformerException {
        org.w3c.dom.Document w3cDoc = new W3CDom().fromJsoup(jsoupDoc);
        Source inputSource = new DOMSource(w3cDoc);
        Source xsltSource = new StreamSource(xsltStream);
        TransformerFactory fact = new net.sf.saxon.TransformerFactoryImpl();
        Transformer transformer = fact.newTransformer(xsltSource);
        transformer.transform(inputSource, new StreamResult(outputStream));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

/*
        try {
            String id = req.getParameter("id");
            String text = req.getParameter("text");

            if (id == null || text == null || id.equals("") || text.equals("")) {
                req.setAttribute("error", "invalid input");
                req.getRequestDispatcher("/urlfetchresult.jsp").forward(req, resp);
                return;
            }

            JSONObject jsonObj =
                    new JSONObject().put("userId", 1).put("id", id).put("title", text).put("body", text);

            // [START complex]
            URL url = new URL("http://jsonplaceholder.typicode.com/posts/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(URLEncoder.encode(jsonObj.toString(), "UTF-8"));
            writer.close();

            int respCode = conn.getResponseCode(); // New items get NOT_FOUND on PUT
            if (respCode == HttpURLConnection.HTTP_OK || respCode == HttpURLConnection.HTTP_NOT_FOUND) {
                req.setAttribute("error", "");
                StringBuffer response = new StringBuffer();
                String line;

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                req.setAttribute("response", response.toString());
            } else {
                req.setAttribute("error", conn.getResponseCode() + " " + conn.getResponseMessage());
            }
            // [END complex]
            req.getRequestDispatcher("/urlfetchresult.jsp").forward(req, resp);
        } catch (JSONException e) {
            throw new ServletException(e);
        }
*/
    }

}
