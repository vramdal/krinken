<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jsoup="http://vvv.vidarramdal.com/ns/jsoup">

    <xsl:output method="xml" encoding="utf-8"/>

    <xsl:template match="/">
        <document>
            <xsl:value-of select="jsoup:select()"/>
        </document>
    </xsl:template>

</xsl:stylesheet>