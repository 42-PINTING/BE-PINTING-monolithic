package pinting.board.repository;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pinting.board.domain.Post;
import pinting.board.domain.Tag;

import java.util.List;
import java.util.Optional;

import static pinting.board.domain.QPost.post;
import static pinting.board.domain.QTag.tag;

@Repository
@Transactional
@RequiredArgsConstructor
public class JPABoardRepository implements BoardRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    @Override
    public void save(Post post) {
        em.persist(post);
    }

    @Override
    public void deleteById(Long id) {
    Post existing = em.find(Post.class, id);
    if (existing != null) {
        this.em.remove(this.em.contains(existing) ? existing : this.em.merge(existing));
    }
    }

    @Override
    public Optional<Post> findOneById(Long id) {
        return Optional.ofNullable(em.find(Post.class, id));
    }

    @Override
    public List<Post> findByAuthorId(Long authorId) {
        return queryFactory
                .selectFrom(post)
                .where(post.authorId.eq(authorId))
                .fetch();
    }

    @Override
    public List<Post> findAll() {
        return queryFactory
                .selectFrom(post)
                .fetch();
    }

    @Override
    public List<Post> findPostsByKeyword(String keyword) {
        return queryFactory
                .selectFrom(post)
                .where(post.title.contains(keyword))
                .fetch();
    }

    @Override
    public List<Post> findPostsByTags(List<String> tagNames) {
        return queryFactory
                .selectFrom(post)
                .join(post.tags, tag)
                .where(tag.name.in(tagNames))
                .fetch();
    }

    @Override
    public List<Post> getRandomPosts(int count) {
        return queryFactory
                .selectFrom(post)
                .where(post.hiddenTime.isNull())
                .orderBy(NumberExpression.random().asc())
                .limit(count)
                .fetch();
    }
}
