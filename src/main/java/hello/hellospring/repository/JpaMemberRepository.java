package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 모든게 동작한다.
    //build.gradle에서 Jpa설정 해줌으로써 spring boot에서 EntityManager를 자동으로 생성해준다.

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //JPA가 insert query 만들어서 DB에 넣어줌.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  //조회할 타입과 식별자로 인지한다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {   //PK가 아닌 값으로 조회할 때는 jpql이라는 query언어를 써야한다.
       List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
