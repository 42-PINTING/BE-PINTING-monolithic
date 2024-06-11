package pinting.member.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pinting.member.domain.Member;
import pinting.member.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long createMember(Member member) {
		validateDuplicateMember(member);

		System.out.println("service member.name = " + member.getName());

		memberRepository.save(member);
		return member.getId();
	}

	public Member readOneMemberById(Long id) {
		return memberRepository.findById(id).orElseGet(Member::new);
	}

	public Member updateMember(Member member) {
		return memberRepository.save(member);
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
