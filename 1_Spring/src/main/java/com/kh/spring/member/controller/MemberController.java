package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller	// 객체 생성, Controller 성질을 가지게 함
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	/*연결하기*/
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login() {
//		System.out.println("로그인 처리합니다.");
//	}
	
	/********** 파라미터 전송 받기 **********/
	// 1. HttpServletRequest를 통해 전송받기(JSP/Servlet방식)
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("id1 : " + id);
//		System.out.println("pwd1 : " + pwd);
//	}
	
	// 2. @RequestParam 방식
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login(@RequestParam(value="id", defaultValue="hello") String userId,
//					  @RequestParam(value="pwd", defaultValue="world") String userPwd,
//					  @RequestParam(value="test", required=false, defaultValue="spring") String test) {
//		System.out.println("id2 : " + userId);
//		System.out.println("pwd2 : " + userPwd);
//		System.out.println("test : " + test);
//	}
	
	// 3. @RequestParam 생략
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login(String id, String pwd) {
//		System.out.println("id3 : " + id);
//		System.out.println("pwd3 : " + pwd);
//	}
	
	// 4.@ModelAttribute 방식
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login(@ModelAttribute Member m) {
//		System.out.println("id4 : " + m.getId());
//		System.out.println("pwd4 : " + m.getPwd());
//	}
	
	// 5. @ModelAttribute 생략
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public void login(Member m, HttpSession session) {
//		// 이전에 사용했던 new MemberService() 객체를 만드는 방식은 주도권이 나한테 있으며 결합도가 높아짐
//		// 결합도가 높음을 확인할 수 있는 것 1. 클래스 명 변경에 직접적인 영향 받음 2. 매 요청마다 새로운 service객체가 생성됨(주소값이 계속 달라짐)
//		
//		Member loginMember = mService.memberLogin(m);
//		
//		if(loginMember != null) {
//			session.setAttribute("loginUser", loginMember);
//		} else {
//			
//		}
//		
//	}
	
	/********* 전달하고자 하는 데이터가 있을 경우 *********/
	// 1. Model 객체 사용
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public String login(Member m, HttpSession session, Model model) {
//		
//		Member loginMember = mService.memberLogin(m);
//		
//		if(loginMember != null) {
//			session.setAttribute("loginUser", loginMember);
////			return "../home";		 // url에 login.me
//			return "redirect:home.do";
//		} else {
//			model.addAttribute("msg", "로그인에 실패하였습니다.");
//			return "../common/errorPage";
//		}
//		
//	}
	
	//2. ModelAndView객체 사용
//	@RequestMapping(value="login.me", method=RequestMethod.POST)
//	public ModelAndView login(Member m, HttpSession session, ModelAndView mv) {
//		
//		Member loginMember = mService.memberLogin(m);
//		
//		if(loginMember != null) {
//			session.setAttribute("loginUser", loginMember);
//			mv.setViewName("redirect:home.do");
//		} else {
//			mv.addObject("msg", "로그인에 실패하였습니다.");
//			mv.setViewName("../common/errorPage");
//		}
//		return mv;
//	}
	
	// 로그아웃
//	@RequestMapping(value="logout.me")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		
//		return "redirect:home.do";
//	}
	
	// 3. session에 저장할 때 @SessionAttributes 사용
	// model객체에 attribute가 추가될 때, 자동으로 키 값을 찾아 세션에 등록
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String login(Member m, Model model) {
		
		Member loginMember = mService.memberLogin(m);
		
		if(loginMember != null) {
			model.addAttribute("loginUser", loginMember);
			
			return "redirect:home.do";
		} else {
			throw new MemberException("로그인에 실패하였습니다");
		}
	}
	
	// @SessionAttributes에 맞는 로그아웃
	@RequestMapping(value="logout.me")
	public String logout(SessionStatus session) {
		session.setComplete();
		
		return "redirect:home.do";
	}

}
