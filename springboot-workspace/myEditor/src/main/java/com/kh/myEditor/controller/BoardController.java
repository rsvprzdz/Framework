package com.kh.myEditor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.myEditor.model.service.BoardService;
import com.kh.myEditor.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bService; 
	
	@GetMapping("/board")
	public String enrollBoard() {
		return "/board/enrollBoard";
	}
	
	// 리스폰스 바디를 붙였다: 비동기통신을 하겠다. 리턴ok를 한다: 리스폰스바디를 붙여야함
	/**
	 * 
	 * @param board
	 * @return
	 */
	@ResponseBody
	@PostMapping("/board")
	public String insertBoard(@RequestBody Board board) {
		log.info("data --> {}", board);
		int result = bService.insertBoard(board);
		
		return result > 0 ? "ok" : "fail";
	}
	
	/**
	 * 전달된 이미지파일들을 서버에 저장한 후 해당 파일들의 이름 목록을 반환
	 * @param imgList 이미지 파일목록
	 * @return 이미지 파일명 목록
	 */
	@ResponseBody
	@PostMapping(value="/upload", produces="application/json;charset=UTF-8")
	public String uploadImage(List<MultipartFile> imgList) {
		
		log.info("{}", imgList);
		
		List<String> changeNameList = new ArrayList<>();
		
		for(MultipartFile file : imgList) {
			String changeName = saveFile(file);
			log.info("change name : {}", changeName);
			changeNameList.add(changeName);
		}
		
		return new Gson().toJson(changeNameList);	// ArrayList를 Gson을 통해 JSONArray로 변경해서 던져야 js가 알아처먹음
	}
	
	// ------------------------------------------------
	private String saveFile(MultipartFile upfile) {
		// 파일명을 변경하여 저장
		// 	변경 파일명 => yyyyMMddHHmmss + xxxxx(랜덤값) + .확장자
		
		// * 현재 날짜 시간 정보
		String currTime = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
		//	* 5자리 랜덤값 ( 10000 ~ 99999 )
		int random = (int)(Math.random()*(99999-10000+1))+10000;
		
		
		//기존이름가져오기
		String orgName = upfile.getOriginalFilename();		// "test.png" .의 위치: indexof // "test.2024.png" 확장자.의위치: lastindexof
		
		//* 확장자 (.txt, .java, .png, ... ) 가져오기
		String ext = orgName.substring( orgName.lastIndexOf(".") );
		
		// 저장할 네임
		String chgName = currTime + random + ext;
		
		// 업로드할 파일을 저장할 위치의 물리적인 경로 조회
		String uploadDir= "./uploads/";
		Path savePath =Paths.get(uploadDir + chgName);		// "./uploads/20241106101823421.jpg"

		try {
			Files.createDirectories(savePath.getParent());	// 상위 디렉토리가 없을 경우 생성
			Files.write(savePath, upfile.getBytes());		// 파일을 서버에 저장
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uploadDir + chgName;
	}
}
