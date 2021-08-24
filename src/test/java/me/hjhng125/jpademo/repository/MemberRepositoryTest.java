package me.hjhng125.jpademo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        memberRepository.save(Member.builder()
            .name("jinhyung")
            .build());

        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

        assertThat(result).extracting("name").contains("jinhyung");
    }

    @Test
    void findById() {
        Member hong = Member.builder()
            .name("hong")
            .build();
        memberRepository.save(hong);

        Member byId = memberRepository.findById(hong.getId());
        assertThat(byId.getName()).isEqualTo("hong");
    }

    @Test
    void findAll() {
        memberRepository.save(Member.builder()
            .name("hong")
            .build());

        List<Member> all = memberRepository.findAll();
        all.forEach(System.out::println);
        assertThat(all).isNotEmpty();
    }

    @Test
    void findByName() {
        memberRepository.save(Member.builder()
            .name("hong")
            .build());

        List<Member> byName = memberRepository.findByName("hong");
        byName.forEach(System.out::println);
        assertThat(byName).isNotEmpty();
    }
}