package content.integration.marshall;

/**
 * Created by u6023035 on 24/01/2017.
 */
public class ArticleMarshaller {
    public static String csvToXML(String body) {
        String[] lines = body.split("\\n");
        String[] fieldNames = lines[0].split(",");
        StringBuffer xmlDoc = new StringBuffer().append("<journal>");
        for (int j=1; j<lines.length; j++ )
        {
            xmlDoc.append("<article>");
            String[] fields = lines[j].split(",");
            for (int i=0; i<fields.length; i++ )
            {
                xmlDoc.append("<" + fieldNames[i].trim() + ">" + fields[i].trim() + "</" + fieldNames[i].trim() + ">");
            }
            xmlDoc.append("</article>");
        }
        return xmlDoc.append("</journal>").toString();
    }
}
