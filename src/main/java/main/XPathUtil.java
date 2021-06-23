package main;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathUtil {
	
	public static String convertXMLFileToString(File file)throws IOException{
		
		String xmlString = null;
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			xmlString = writer.getBuffer().toString();
		}catch (Exception e) {
			throw new IOException(e);
		}		
		return xmlString;		
	}
	
	//XML 형식의 문자를 읽어서 파싱하여 출력한다. XPATH
	public static void parseXMLStringByXpath(String xml)throws IOException{

		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xml));
			Document document = documentBuilder.parse(inputSource); 
		
			//xml 출력
			printNodeByXpath(document);
		}catch (Exception e) {
			throw new IOException(e);
		}		
	}
	
	//일단 하드코딩으로 한 것...
	private static void printNodeByXpath(Document document)throws Exception{
		document.getDocumentElement().normalize();
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		//root
		Node node = (Node)xPath.evaluate("/response/body/items/item", document.getDocumentElement(), XPathConstants.NODE);		
		System.out.println("name : " + node.getNodeName());
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeName().equalsIgnoreCase("detailurl")) {
                System.out.println(children.item(i).getTextContent());
            }
        }
	}

    public static void main(String[] args) throws Exception {
        File file = new File("data/data.xml");
        String xmlStr = convertXMLFileToString(file);
        parseXMLStringByXpath(xmlStr);

    }
}