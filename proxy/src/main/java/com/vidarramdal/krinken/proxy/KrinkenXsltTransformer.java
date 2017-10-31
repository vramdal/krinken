package com.vidarramdal.krinken.proxy;

import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.Processor;

// https://www.programcreek.com/java-api-examples/index.php?api=net.sf.saxon.s9api.Processor

public class KrinkenXsltTransformer {

    public KrinkenXsltTransformer() {
        Configuration config = new Configuration();
        new Processor(config);
    }


}
