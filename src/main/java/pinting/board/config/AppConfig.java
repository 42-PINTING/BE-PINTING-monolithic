package pinting.board.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "pinting.board"
)
public class AppConfig {
    private final EntityManager em;

    public AppConfig(final EntityManager em) {this.em = em;}

    @Bean
    public JPAQueryFactory jpaQueryFactory() {return new JPAQueryFactory(em);}
}
