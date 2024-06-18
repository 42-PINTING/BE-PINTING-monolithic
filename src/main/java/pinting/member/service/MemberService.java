package pinting.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pinting.member.controller.dto.MemberDto;
import pinting.member.domain.Member;
import pinting.member.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long createMember(MemberDto memberDto) {
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());
		member.setRole("ROLE_USER");

		validateDuplicateMember(member);

		memberRepository.save(member);
		return member.getId();
	}

	@Transactional(readOnly = true)
	public Member readOneMemberById(Long id) {
		return memberRepository.findById(id).orElseGet(Member::new);
	}

	public void updateMember(Long id, MemberDto memberDto) throws Exception {
		Member member = memberRepository.findById(id).orElseThrow(ArrayIndexOutOfBoundsException::new);
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
	}

	public void deleteMemberById(Long id) {
		memberRepository.deleteById(id);
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}
}
