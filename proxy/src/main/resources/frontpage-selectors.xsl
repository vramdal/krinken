<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsoup="xalan://com.vidarramdal.krinken.proxy.JsoupXsltExtensions"
                extension-element-prefixes="jsoup"
>

    <xsl:output method="xml" encoding="utf-8"/>

    <xsl:template match="/">
        <document>


            <laks>
                <!--<xsl:value-of select="jsoup:cssSelect('#main')"/>-->
                <jsoup:cssSelect selector=".container"/>
            </laks>
            <flesk>
            </flesk>


        </document>
    </xsl:template>

    <xsl:template match="div"></xsl:template>

</xsl:stylesheet>