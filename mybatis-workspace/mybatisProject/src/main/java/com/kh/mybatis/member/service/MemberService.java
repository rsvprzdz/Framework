package com.kh.mybatis.member.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {
	// 인터페이스 : 추상메소드(public abstract) + 상수(public static final)
	
	/* 로그인 요청 관련 기능 */
	Member loginMember(Member m);
	
	/* 회원가입 요청 관련 기능 */
	int insertMember(Member m);
	
	/* 회원 정보 수정 관련 기능 */
	Member updateMember(Member m);
	
	/* 탈퇴 요청 관련 기능 */
	int deleteMember(String id, String pwd);
	
	// => 인터페이스의 몸체없는 메소드를 정의해놨고, 이걸 구현한 클래스가 필요한상태
}
