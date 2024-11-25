package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	
	@ResponseBody
	@RequestMapping(value="temp.do", produces="application/json;charset=UTF-8")
	public String temperature(String location) throws IOException {
		
		//Date에서 년월일 뽑으면 이상하게 나옴 => LocalDateTime으로 변경
		LocalDateTime date = LocalDateTime.now();
		
		// LocalDateTime은 DateTimeFormatter로 포맷 지정, new로 생성하지 않고 .ofPattern()을 통해 바로 지정해줌. 불변객체라서 그렇데요 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formatDate = "";
		int hour = date.getHour();
		
		if(hour >=6 && hour  <18) {
			formatDate = dtf.format(date)+"0600";
		} else if(hour >=18 && hour < 24){
			formatDate = dtf.format(date)+"1800";
		} else if(hour >=0 && hour < 6) {
			formatDate = date.getYear()+""+date.getMonthValue()+""+(date.getDayOfMonth()-1)+"1800";
		}
		
		/*
		 * 경우의 수 체크
		11일 06:00 ~ 17:59일 경우
		202410110600
		
		11일 18:00 ~ 23:59일 경우
		202410111800
		
		12일 00:00 ~ 05:59일 경우
		202410(12-1)1800
		
		...아래 반복
		12일 06:00 ~ 17:59
		202410120600
		
		12일 18:00 ~ 23:59일 경우
		202410121800
		
		13일 00:00 ~ 05:59일 경우
		202410(13-1)1800
		...
		 */
		
		//검증
		//System.out.println(formatDate);
		//System.out.println(date.getYear());
		//System.out.println(date.getMonthValue());
		//System.out.println(date.getDayOfMonth());
		//formatDate = date.getYear()+""+date.getMonthValue()+""+(date.getDayOfMonth()-1)+"0600";
		
		 
		
	 	
	    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
	    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서뷔쓰키~"); /*Service Key*/
	    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	    urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
	    urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(location , "UTF-8")); /*11B10101 서울, 11B20201 인천 등 ( 별첨엑셀자료 참고)*/
	    urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(formatDate, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력- YYYYMMDD0600(1800) 최근 24시간 자료만 제공*/
	    URL url = new URL(urlBuilder.toString());
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    System.out.println("Response code: " + conn.getResponseCode());
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();
	    System.out.println(sb.toString());
	        
	    return sb.toString();
	}
}
