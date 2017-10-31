package com.vidarramdal.krinken.proxy;

import com.google.appengine.repackaged.com.google.common.io.Resources;
import org.dom4j.dom.DOMDocument;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KrinkenFetcherServlet extends HttpServlet {

    public static final String PATH_XSLT = "radio-to-atv.xsl";
    public static final String PATH_FRONTPAGE_XSLT = "frontpage.xsl";

    public static class StringBufferOutputStream extends ByteArrayOutputStream {
        private StringBuffer buffer;

        public StringBufferOutputStream(StringBuffer out) {
            this.buffer = out;
        }

        public void write(int ch) {
            this.buffer.append((char)ch);
        }

        public StringBuffer getBuffer() {
            return buffer;
        }
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                InputStream xsltStream = this.getClass().getClassLoader().getResourceAsStream(PATH_XSLT);
                InputStream frontpageXsltStream = this.getClass().getClassLoader().getResourceAsStream(PATH_FRONTPAGE_XSLT);
                InputStream htmlStream = this.getClass().getClassLoader().getResourceAsStream("NRK Radio - forside.htm");
        ){
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/javascript");
            ServletOutputStream outputStream = resp.getOutputStream();
//            Document jsoupDoc = fetch();
            Document jsoupDoc = Jsoup.parse(htmlStream, "utf-8", "/");
            String selector = "#main .channel-grid .nrk-grid .radio-channel";
            Function<Element, Channel> factory = Channel::new;
            List<Channel> channelList = getObjects(jsoupDoc, selector, factory);
            for (Channel channel : channelList) {
                System.out.println("channel.getTitle() = " + channel.getTitle());
            }
            URL baseUrl = new URL("https://radio.nrk.no");
            List<Plug> bigPlugList = getObjects(jsoupDoc, "#main .radio-plugs .listobject", Plug::new);
            List<Plug> recommendedPlugList = getObjects(jsoupDoc, "#main #recommended-list .listobject-list .listobject", Plug::new);
            List<Plug> plugList = new ArrayList<>();
            plugList.addAll(bigPlugList);
            plugList.addAll(recommendedPlugList);
            for (Plug plug : plugList) {
                System.out.println(plug.getProgramnavn() + ": " + plug.getTittel());
            }
            StringBuffer stringBuffer = new StringBuffer();
            ByteArrayOutputStream xmlOut = new ByteArrayOutputStream();
            transform(xmlOut, jsoupDoc, xsltStream, new Forside(channelList, plugList), frontpageXsltStream);
            String appTemplateStr = Resources.toString(Resources.getResource("app-template.js"), Charset.forName("UTF-8"));
            String result = appTemplateStr.replace("<!-- INSERT SCRIPT HERE -->", xmlOut.toString("utf-8"));
            outputStream.write(result.getBytes("UTF-8"));
            outputStream.close();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private <T> List<T> getObjects(Document jsoupDoc, String selector, Function<Element, T> factory) {
        return jsoupDoc.select(selector).stream().map(factory).collect(Collectors.toList());
    }

    Document fetch() throws IOException, TransformerException {
        return Jsoup.connect("https://radio.nrk.no").get();

/*
        Elements plugs = jsoupDoc.select("#main .radio-plugs .listobject");
        for (Entity plug : plugs) {
            System.out.println("plug.outerHtml() = " + plug.outerHtml());
        }
*/
    }

    void transform(OutputStream outputStream, Document jsoupDoc, InputStream xsltStream, Forside forside, InputStream frontpageXsltStream) throws TransformerException {
        org.w3c.dom.Document w3cDoc = new W3CDom().fromJsoup(jsoupDoc);
        Source inputSource = new DOMSource(w3cDoc);
        Source xsltSource = new StreamSource(frontpageXsltStream);
        TransformerFactory fact = new net.sf.saxon.TransformerFactoryImpl();
        Transformer transformer = fact.newTransformer(xsltSource);
        transformer.setParameter("channelList", forside.getSource(new DOMDocument()));
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
