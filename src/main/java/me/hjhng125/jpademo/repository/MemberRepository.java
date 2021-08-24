package me.hjhng125.jpademo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.hjhng125.jpademo.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Member member) {
        entityManager.persist(member);
    }

    public Member findById(Long memberId) {
        return entityManager.find(Member.class, memberId);
    }

    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class)
            .getResultList();
    }

    public List<Member> findByName(String memberName) {
        return entityManager.createQuery("SELECT m FROM Member m WHERE m.name = :memberName", Member.class)
            .setParameter("memberName", memberName)
            .getResultList();
    }
}
