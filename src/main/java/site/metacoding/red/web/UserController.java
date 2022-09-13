package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.utill.Script;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UsersService usersService;
	private final HttpSession session;
	
	@GetMapping("joinForm")//인증이 필요한페이지는 앞에 도메인을 적지않는다
	public String joinForm() {
		return "users/joinForm";
	}
	
	@GetMapping("loginForm")//쿠키배워보기
	public String loginForm() {
		return "users/loginForm";
	}
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersService.회원가입(joinDto);
		return "redirect:/loginForm";
	}
	
	@PostMapping("/login")
	public @ResponseBody String login(LoginDto loginDto) { //@responsebody를 붙이면 데이터를 리턴함
		Users principal = usersService.로그인(loginDto);
		
		if(principal == null) {
			return Script.back("아이디 혹은 비밀번호가 틀렸습니다");
		}
		
		session.setAttribute("principal", principal);
		return Script.href("/");
	}
	
	@GetMapping("/users/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = usersService.회원정보보기(id);
		model.addAttribute("users",usersPS);
		return "users/updateForm";
	}
	
	@PutMapping("/users/{id}")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		usersService.회원수정(id, updateDto);
		return "redirect:/users/"+id;
	}
	
	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable Integer id) {
		usersService.회원탈퇴(id);
		return Script.href("/loginForm", "회원탈퇴가 완료되었습니다");
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginForm";
	}
}
