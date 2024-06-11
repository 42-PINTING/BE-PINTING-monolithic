package pinting.member.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pinting.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public void deleteById(Long id) {
		Assert.notNull(id, "The given id must not be null");
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public void delete(Member entity) {
		Assert.notNull(entity, "Entity must not be null");
		Member existing = this.em.find(Member.class, entity.getId());
		if (existing != null) {
				this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
		}
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member user = em.find(Member.class, id);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class)
				.setParameter("email", email)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

}
