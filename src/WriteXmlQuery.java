import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class WriteXmlQuery {

    public HashMap<String,String> getQuery(String filePath){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document=builder.parse(filePath);
            document.getDocumentElement().normalize();

            NodeList rootNode=document.getElementsByTagName("sql");
            System.out.println("dddd:"+rootNode.item(0).getChildNodes().getLength());
            NodeList sqlNode=rootNode.item(0).getChildNodes();

            HashMap<String,String> sqlList=new HashMap<String,String>();

            for(int i=0;i<sqlNode.getLength();i++){
                if(sqlNode.item(i).getNodeName().equals("#text")) continue;
                System.out.println(sqlNode.item(i).getNodeName()+","+sqlNode.item(i).getTextContent());
                sqlList.put(sqlNode.item(i).getNodeName(),sqlNode.item(i).getTextContent());
            }
            return sqlList;

        }catch (IOException ie){
            System.out.println("파일 읽기 실패~");
            ie.printStackTrace();
            return null;
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
            return null;
        }catch (SAXException se){
            se.printStackTrace();
            return null;
        }
    }
}
