package pinting.board.repository;

import pinting.board.domain.Post;
import pinting.board.domain.Tag;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    void save(Post post);
    void deleteById(Long id);
    Optional<Post> findOneById(Long id);
    List<Post> findByAuthorId(Long authorId);
    List<Post> findAll();
    List<Post>getRandomPosts(int n);
    List<Post> findPostsByKeyword(String keyword);
    List<Post> findPostsByTags(List<String> tags);
}
