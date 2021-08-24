package me.hjhng125.jpademo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import me.hjhng125.jpademo.domain.Member;
import me.hjhng125.jpademo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입() {
        Member member = Member.builder()
            .name("jinhyung")
            .build();

        Long joinedId = memberService.join(member);
        assertThat(member).isEqualTo(memberRepository.findById(joinedId));
    }

    @Test
    void 중복_회원가입_예외() {
        Member member1 = Member.builder()
            .name("jinhyung")
            .build();
        memberService.join(member1);

        Member member2 = Member.builder()
            .name("jinhyung")
            .build();

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}