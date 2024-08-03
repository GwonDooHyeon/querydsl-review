package study.querydsl.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;

    @DisplayName("테스트 엔티티")
    @Test
    void testEntity() throws Exception {
        // given
        Team a = new Team("a");
        Team b = new Team("b");
        em.persist(a);
        em.persist(b);

        Member member1 = new Member("member1", 10, a);
        Member member2 = new Member("member2", 10, a);

        Member member3 = new Member("member3", 10, b);
        Member member4 = new Member("member4", 10, b);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.getTeam() = " + member.getTeam());
        }
        // when

        // then
    }

}