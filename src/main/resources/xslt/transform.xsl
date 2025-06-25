<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


    <xsl:output method="xml" indent="yes"/>


    <xsl:template match="/order">
        <customerInfo>
            <name>
                <xsl:value-of select="customer/name"/>
            </name>
            <city>
                <xsl:value-of select="customer/city"/>
            </city>
        </customerInfo>
    </xsl:template>


    <xsl:template match="text()|@*|node()"/>
</xsl:stylesheet>
