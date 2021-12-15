package com.kh.spring.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.vo.Member;

@Service("mService")
public interface MemberService {
	
	Member memberLogin(Member m);

}
