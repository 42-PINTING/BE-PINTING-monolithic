package pinting.member.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pinting.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	Member save(Member member);
	void deleteById(Long id);
	void delete(Member entity);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	Optional<Member> findByEmail(String email);
	List<Member> findAll();
}
