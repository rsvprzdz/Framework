package com.kh.spring.board.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply {

	private int replyNo;
	private String replyContent;
	private int refBno;
	private String replyWriter;
	private String createDate;
	private String status;
}
