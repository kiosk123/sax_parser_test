package main;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class ItemSaxHandler extends DefaultHandler {

    @Getter
    private List<ItemVo> items = new LinkedList<ItemVo>();
    private ItemVo itemVo; // itemVO
    private String value; //값 저장
    private int totalCount = 0;
    private int numOfRows = 0;
    private String resultCode = "";
    private String resultMsg = "";

    /**
     * 시작 태그를 만났을 때 이벤트 발생
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equalsIgnoreCase(qName)) {
            itemVo = new ItemVo();
        } 
    }

    /**
     * 끝 태그를 만났을 때 이벤트 발생
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equalsIgnoreCase(qName)) { 
            items.add(itemVo);
            itemVo = null; /** itemVo는 여기서 null 처리 */
        } else if ("biztitle".equalsIgnoreCase(qName)) {
            itemVo.setBiztitle(value);
        } else if ("detailurl".equalsIgnoreCase(qName)) {
            itemVo.setDetailurl(value);
        } else if ("enddate".equalsIgnoreCase(qName)) {
            itemVo.setEnddate(value);
        } else if ("insertdate".equalsIgnoreCase(qName)) {
            itemVo.setInsertdate(value);
        } else if ("organizationname".equalsIgnoreCase(qName)) {
            itemVo.setOrganizationname(value);
        } else if ("postsn".equalsIgnoreCase(qName)) {
            itemVo.setPostsn(value);
        } else if ("posttarget".equalsIgnoreCase(qName)) {
            itemVo.setPosttarget(value);
        } else if ("posttargetage".equalsIgnoreCase(qName)) {
            itemVo.setPosttargetage(value);
        } else if ("startdate".equalsIgnoreCase(qName)) {
            itemVo.setStartdate(value);
        } else if ("supporttype".equalsIgnoreCase(qName)) {
            itemVo.setSupporttype(value);
        } else if ("title".equalsIgnoreCase(qName)) {
            itemVo.setTitle(value);
        } else if ("viewcount".equalsIgnoreCase(qName)) {
            itemVo.setViewcount(value);
        } else if ("areaname".equalsIgnoreCase(qName)) {
            itemVo.setAreaname(value);
        } else if ("posttargetcomage".equalsIgnoreCase(qName)) {
            itemVo.setPosttargetcomage(value);
        } else if ("resultCode".equalsIgnoreCase(qName)) {
            resultCode = value;
        } else if ("totalCount".equalsIgnoreCase(qName)) {
            totalCount = Integer.parseInt(value);
        } else if ("numOfRows".equalsIgnoreCase(qName)) {
            numOfRows = Integer.parseInt(value);
        } else if ("resultMsg".equalsIgnoreCase(qName)) {
            resultMsg = value;
        }
        value = null;
    }

    /**
     *  태그와 태그 사이 내용 처리
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = new String(ch,start,length);
    }

    public String getResultCode() {
        return resultCode;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public String getResultMsg() {
        return resultMsg;
    }
    
}
