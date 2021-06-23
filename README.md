# XML SAX 파서 테스트

많은 데이터가 포함된 XML을 파싱하는 데 DOM 파서보다 SAX 파서가 성능상의 이점이 더 있다고 보고 SAX 파서를 테스트 하기 위한 프로젝트

## 테스트 환경
lombok 1.18.20  
jdk 8  
gradle 6.5.1

## 테스트 데이터
- 창업넷 데이터 item 데이터 500개
  - 데이터 위치 : ${프로젝트 폴더}/data/data.xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<response>
	<header>
		<resultCode>00</resultCode>
		<resultMsg>NORMAL SERVICE.</resultMsg>
	</header>
	<body>
		<items>
			<item>
				<biztitle>한국-터키 창업웨비나</biztitle>
				<detailurl>http://www.k-startup.go.kr/common/announcement/announcementDetail.do?mid=30004&amp;bid=701&amp;searchPostSn=136541&amp;searchAncmId=&amp;searchDtlAncmSn=0&amp;searchPrefixCode=BOARD_701_001&amp;searchBusinessSn=0</detailurl>
				<enddate>20210628</enddate>
				<insertdate>20210623</insertdate>
				<organizationname>(재)글로벌청년창업가재단</organizationname>
				<postsn>136541</postsn>
				<posttarget>전체</posttarget>
				<posttargetage>전체</posttargetage>
				<posttargetcomage>전체</posttargetcomage>
				<startdate>20210623</startdate>
				<supporttype>행사·네트워크</supporttype>
				<title>한국-터키 기업가정신과 스타트업 생태계 
웨비나 참가 모집</title>
				<viewcount>37</viewcount>
			</item>
			<!-- //.. 생략 -->
		</items>
		<numOfRows>30</numOfRows>
		<pageNo>1</pageNo>
		<totalCount>11205</totalCount>
	</body>
</response>
```
## 구현
SAX 파서는 이벤트 방식으로 동작한다 즉 스트림으로 읽으면서 특정 태그 이름과 매치될때 데이터를 처리하면 된다.  
구현은 ItemSaxHandler.java를 참고한다.  

## 성능
변칙성이 없고 정형화된 XML 데이터일 경우 SAX 파서가 월등한 성능을 보여준다.  
총 500개의 item 태그의 데이터를 자바 객체로 변환하는 데 고작 0.7초 밖에 걸리지 않았다.  
실제로 API를 호출해서 데이터를 가져오는 시간도 고려해야 하지만 데이터를 10000개 처리할 경우 14초 정도 걸릴 것으로 예상된다.  

## 참고
- [XML Parser 정리](https://hongkyu.tistory.com/m/79)
- [SAX parser 활용](https://sangwoo0727.github.io/java/JAVA-29_SAXParser/)
