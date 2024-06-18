package pinting.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pinting.member.domain.Member;
import pinting.member.controller.dto.MemberDto;
import pinting.member.service.MemberService;

@Controller
@ResponseBody
public class MainController {

	MemberService memberService;

	public MainController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/{id}")
	public Member readOneById(@PathVariable Long id) {
		return memberService.readOneMemberById(id);
	}

	@PostMapping("/members")
	public String createMember(MemberDto memberDto) {
		Long id = memberService.createMember(memberDto);
		return "successful creation for id: " + id;
	}

	@PatchMapping("/members/{id}")
	public String updateMember(@PathVariable Long id, MemberDto memberDto) throws Exception {
		memberService.updateMember(id, memberDto);
		return "successful update for id: " + id;
	}

	@DeleteMapping("/members/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.deleteMemberById(id);
	}
}
