package me.hjhng125.jpademo.repository;

import me.hjhng125.jpademo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}
