package com.kh.khEmail.test;

import java.io.File;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

public class MailTest {

	/*
	 * 이메일 관련 프로토콜
	 * 
	 * * SMTP (Simple Mail Transfer Protocol)
	 *   : 이메일 전송 시 사용되는 프로토콜
	 * 
	 * * POP (Post Office Protocol)
	 *   : 이메일 서버에 도착한 메일을 클라이언트로 가지고 올 때 사용되는 프로토콜
	 *   
	 * * IMAP (Internet Message Access Protocol)
	 *   : 이메일 서버에 직접 접속하여 메일을 확인할 때 사용되는 프로토콜
	 *   => Gmail서버(smtp) 사용 시 활성화 필요!
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("-----------------------Mail Test Start ---------------------------");
		// JavaMailSenderImpl 객체 사용 => org.springframework.mail (boot start mail 의존성 추가)
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		// 메일 서버 관련 설정
		sender.setHost("smtp.gmail.com");			// - SMTP 서버 주소
		sender.setPort(587);						// - 포트
		sender.setUsername("1gjdhks1@gmail.com");	// - 인증받은 사용자 이메일
		sender.setPassword("pnxg tmqh ikqc qznr");	// - 앱 비밀번호
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);			// SMTP 서버 연결 시 사용자 인증 설정
		prop.put("mail.smtp.starttls.enable", true);// SMTP 연결 시 TLS 암호화 설정
		sender.setJavaMailProperties(prop);			// 부가적인 설정들
		// -----------------------
		
		// 메일 전송 준비
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true, "UTF-8");
									// MimeMessage객체, Multipart(첨부파일) 포함유무, 인코딩
		
		helper.setSubject("[KH] 제친구 건웅이를 소개합니다");		// 메일 제목
		helper.setText("애는 착해요 안물어요");	// 메일 내용
		helper.setFrom("1gjdhks1@gmail.com", "[KH] 메일 전송");	// 발신자(이메일, 별칭)
		helper.setTo("1gjdhks1@naver.com");// 수신자
		
		// 파일 첨부 (첨부파일)
		File f = new File(MailTest.class.getResource("/static/img/gunwoong.jpg").getPath());
		helper.addAttachment("gunwoong.jpg", f);
		// ---------------------
		
		// 메일 전송
		sender.send(mm);
		System.out.println("-----------------------Mail Test End ---------------------------");
	}

}
