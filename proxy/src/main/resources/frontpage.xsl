<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="utf-8"/>

    <xsl:param name="channelList"/>

    <xsl:template match="/">
        <document>
            <showcaseTemplate>
                <background/>
                <banner>
                    <title>Kanaler</title>
                </banner>
                <carousel>
                    <section>
                        <!--<xsl:apply-templates select="$channelList/forside/channels" mode="lockup"/>-->
                        <xsl:copy-of select="$channelList"/>
                        <xsl:apply-templates select="$channelList/forside/plugs" mode="lockup"/>
                    </section>
                </carousel>
            </showcaseTemplate>
        </document>
    </xsl:template>

    <xsl:template match="channel" mode="lockup">
        <lockup>
            <title><xsl:value-of select="@title"/></title>
            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Norsk-rikskringkasting-Logo.svg/1200px-Norsk-rikskringkasting-Logo.svg.png" width="400" height="300"/>
        </lockup>
    </xsl:template>

    <xsl:template match="plug" mode="lockup">
        <xsl:copy-of select="."/>
        <lockup>
            <title> <xsl:value-of select="tittel"/></title>
            <img src="{concat('https://radio.nrk.no', relativBildeUrl)}"/>
        </lockup>
    </xsl:template>

</xsl:stylesheet>