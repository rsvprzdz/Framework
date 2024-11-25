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

	private final String serviceKey = "mnEnhzjOugZrzEYm3%2FfoAToclEu4fyMFWyjrrJQYSCy%2BJXdqgNVjgTq6CqgDqiNHqonqqThP6g3%2B9ue9tEEZCA%3D%3D";

	@ResponseBody

	@RequestMapping(value = "air.do", produces = "application/json;charset=UTF-8")

	public String airPollution(String location) throws IOException {

		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";

		url += "?serviceKey=" + serviceKey

				+ "&sidoName=" + URLEncoder.encode(location, "UTF-8")

				+ "&returnType=json";

		URL requestUrl = new URL(url);

		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();

		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

		String responseText = "";

		String line;

		while ((line = br.readLine()) != null) {

			responseText += line;

		}

		br.close();

		urlConnection.disconnect();

		return responseText;

	}
}