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
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());

		Long id = memberService.createMember(member);

		return "successful creation for id: " + id;
	}

	@PutMapping("/members/{id}")
	public Member updateMember(@PathVariable Long id, MemberDto memberDto) {
		Member member = memberService.readOneMemberById(id);
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());

		memberService.updateMember(member);

		return member;
	}

	@DeleteMapping("/members/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.deleteMemberById(id);
	}
}
