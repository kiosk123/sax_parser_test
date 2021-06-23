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

    /**
     * 시작 태그를 만났을 때 이벤트 발생
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            itemVo = new ItemVo();
        } 
    }

    /**
     * 끝 태그를 만났을 때 이벤트 발생
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) { 
            items.add(itemVo);
            itemVo = null; /** itemVo는 여기서 null 처리 */
        } else if ("biztitle".equals(qName)) {
            itemVo.setBiztitle(value);
        } else if ("detailurl".equals(qName)) {
            itemVo.setDetailurl(value);
        } else if ("enddate".equals(qName)) {
            itemVo.setEnddate(value);
        } else if ("insertdate".equals(qName)) {
            itemVo.setInsertdate(value);
        } else if ("organizationname".equals(qName)) {
            itemVo.setOrganizationname(value);
        } else if ("postsn".equals(qName)) {
            itemVo.setPostsn(value);
        } else if ("posttarget".equals(qName)) {
            itemVo.setPosttarget(value);
        } else if ("posttargetage".equals(qName)) {
            itemVo.setPosttargetage(value);
        } else if ("startdate".equals(qName)) {
            itemVo.setStartdate(value);
        } else if ("supporttype".equals(qName)) {
            itemVo.setSupporttype(value);
        } else if ("title".equals(qName)) {
            itemVo.setTitle(value);
        } else if ("viewcount".equals(qName)) {
            itemVo.setViewcount(value);
        } else if ("areaname".equals(qName)) {
            itemVo.setAreaname(value);
        } else if ("posttargetcomage".equals(value)) {
            itemVo.setPosttargetcomage(value);
        } else if ("resultCode".equals(qName)) {
            resultCode = value;
        } else if ("totalCount".equals(qName)) {
            totalCount = Integer.parseInt(value);
        } else if ("numOfRows".equals(qName)) {
            numOfRows = Integer.parseInt(value);
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
    
}
