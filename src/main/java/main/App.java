/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        File file = new File("data/data.xml");
		String xmlStr = "";
        try {
            xmlStr = xmlFileToString(file);
            xmlStr = xmlStr.replaceAll("<detailurl>", "<detailurl><![CDATA[");
            xmlStr = xmlStr.replaceAll("</detailurl>", "]]></detailurl>");
            xmlStr = xmlStr.replaceAll("&amp;", "&");

        } catch (Exception e) {
            log.debug("============= program end, because of error =============");
            return;
        }

		try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			ItemSaxHandler handler = new ItemSaxHandler();
			parser.parse(new InputSource(new StringReader(xmlStr)), handler);
			
			List<ItemVo> list = handler.getItems();

            int totalCount = handler.getTotalCount();
            String resultCode = handler.getResultCode();
            String resultMsg = handler.getResultMsg();

            log.debug("=============== total count : {} ======================", totalCount);
            log.debug("=============== result code : {} ======================", resultCode);
            log.debug("=============== result message : {} ===================", resultMsg);

            list.forEach(item -> log.debug(item.toString()));
			
            log.debug("list size : {}", list.size());
		}catch(Exception e) {
			e.printStackTrace();
		}	
    }

    private static String xmlFileToString(File file) throws IOException {
        
        String xmlStr = "";
        try (Reader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);) {

            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            xmlStr = sb.toString();
        } catch (IOException e) {
            log.debug("While converting xml file to string, occurred exception", e);
            throw e;
        }
        return xmlStr;
    }
}
