import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class TestXML {


    //xml파일 읽어오기
    public HashMap<String,String> selectXML() {
        final String xmlFile="C:\\Users\\JIHO\\IdeaProjects\\crud_test\\saveInfo.xml";
        try {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();//객체 생성을 위한 factory 생성
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);//생성된 builder를 통해 파싱해서 가져옴
            document.getDocumentElement().normalize();//정규화

            HashMap<String, String> listMap = new HashMap<String, String>();

            NodeList tablaList = document.getElementsByTagName("info");
            NodeList childNodes=tablaList.item(0).getChildNodes();

            for(int i=0 ; i<childNodes.getLength() ; i++) {
                if(childNodes.item(i).getNodeName().equals("#text")) {
                    continue;
                }else if(childNodes.item(i).getNodeName() != null && childNodes.item(i).getNodeName() != ""){
//                    System.out.println(i+":"+childNodes.item(i).getNodeName()+","+childNodes.item(i).getTextContent());
                    listMap.put(childNodes.item(i).getNodeName(), childNodes.item(i).getTextContent());

                }
            }

            return listMap;
        } catch (IOException ie) {
            ie.printStackTrace();
            return null;
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
            return null;
        }catch (SAXException saxe){
            saxe.printStackTrace();
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 1. createXMLFile
//    public void createXml() {
//        try {
//            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//            Node root = document.createElement("testDB");
//            document.appendChild(root);
//
//            Element table=document.createElement("testTable");
//            table.appendChild(document.createTextNode("userInfoTable"));
//            root.appendChild(table);
//
//            Element user = document.createElement("userInfo");
//            table.appendChild(user);
//
//            Element userNum=document.createElement("userNum");
//            userNum.appendChild(document.createTextNode("10"));
//            user.appendChild(userNum);
//
//            Element userName = document.createElement("userName");
//            userName.appendChild(document.createTextNode("testName"));
//            user.appendChild(userName);
//
//            Element userId = document.createElement("userId");
//            userId.appendChild(document.createTextNode("testid"));
//            user.appendChild(userId);
//
//            Element userPwd=document.createElement("userPwd");
//            userPwd.appendChild(document.createTextNode("1234"));
//            user.appendChild(userPwd);
//
//            Element userAddr=document.createElement("userAddr");
//            userAddr.appendChild(document.createTextNode("busan"));
//            user.appendChild(userAddr);
//
//            Element userPhone=document.createElement("userPhone");
//            userPhone.appendChild(document.createTextNode("454545"));
//            user.appendChild(userPhone);
//
//
//            DOMSource xml = new DOMSource(document);
//            StreamResult savaFile = new StreamResult(new File("saveInfo.xml"));
//            TransformerFactory.newInstance().newTransformer().transform(xml, savaFile);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }