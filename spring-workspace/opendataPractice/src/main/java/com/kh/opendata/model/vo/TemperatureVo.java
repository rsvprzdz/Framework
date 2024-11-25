package com.kh.opendata.model.vo;

import lombok.Data;

@Data
public class TemperatureVo {

	private String taMin3;	// 3일 후 예상최저기온
	private String taMax3;	// 3일 후 예상최고기온
	
	private String taMin7;	// 7일 후 예상최저기온
	private String taMax7;	// 7일 후 예상최고기온
	
	private String taMin10;	// 10일 후 예상최저기온
	private String taMax10;	// 10일 후 예상최고기온
}
