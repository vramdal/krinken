<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:output encoding="utf-8"/>

<!--
    <xsl:template match="/">
        <document>
        <xsl:apply-templates select="." mode="showcase"/>
        </document>
    </xsl:template>
-->

    <xsl:template match="/">
        <document>
            <divTemplate tv-align="center">
                <xsl:apply-templates select=".//ul[tokenize(@class,'\s')='nrk-grid']//*[tokenize(@class,'\s')='channel-link']" mode="lockup"/>
            </divTemplate>
        </document>
    </xsl:template>

    <xsl:template match="*[tokenize(@class,'\s')='channel-link']" mode="lockup">
        <lockup tv-align="center">
            <img src="https://gfx.nrk.no/a35CcJV_F5WfxFRLrCHh8AhUcT7TQApTkE4CBsLtx_Jg" width="300" height="100"/>
            <title><xsl:value-of select="@title"/></title>
        </lockup>
    </xsl:template>

    <xsl:template match="/" mode="showcase">

        <showcaseTemplate>
            <img src="https://radio.nrk.no/Pakke74/3.0.163.0a/content/images/plug-bg-gradient.png" />
            <banner>
                <title>Aktuelt, javisst</title>
                <row>
                    <button>
                        <text>Slideshow</text>
                    </button>
                    <button>
                        <text>Screensaver</text>
                    </button>
                </row>
            </banner>
            <carousel>
                <section>
                    <xsl:apply-templates select=".//div[@id='main']//ul[tokenize(@class,'\s')='radio-plugs']/li[tokenize(@class,'\s')='plug-r']" mode="lockup"/>
                </section>
            </carousel>
        </showcaseTemplate>
    </xsl:template>

    <xsl:template match="li" mode="lockup">
        <lockup>
            <img src="{current()//figure/meta[@itemprop='contentUrl']/@content}" width="439" height="246"/>
            <description><xsl:value-of select="current()//article[tokenize(@class,'\s')='infobox']/h2[tokenize(@class,'\s')='title']"/></description>
        </lockup>

    </xsl:template>

</xsl:stylesheet>