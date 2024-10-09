package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	// 서비스 키 
	private final String SERVICE_KEY = "서비스키";
	
	// air.do 요청을 받을 메소드
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json;charset=UTF-8")
	public String airPollution(String location) throws IOException {
		
		// 대기오염 정보를 조회 (api 사용)
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
				+ "?serviceKey=" + SERVICE_KEY	// URLEncoder.encode(SERVICE_KEY, "UTF-8") -> 원본데이터(디코딩)인 경우
				+ "&sidoName=" + URLEncoder.encode(location, "UTF-8")
				+ "&returnType=json";
		
		URL reqUrl = new URL(url);	// 요청 주소로 URL 객체 생성
		HttpURLConnection urlConn = (HttpURLConnection)reqUrl.openConnection();	// URL 객체로 Connection 객체 생성
		
		urlConn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		
		String resText= "";
		String line;
		while((line = br.readLine()) != null) {
			resText += line;
		}
		
		br.close();
		urlConn.disconnect();
		
		System.out.println(resText);
		
		return resText;
	}
}
