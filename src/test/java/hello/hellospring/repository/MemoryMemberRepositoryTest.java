package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();    //각 테스트 종료후에는 store를 clear해야함
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");   //이름 설정

        repository.save(member);    //멤버 저장

        Member result = repository.findById(member.getId()).get();  //저장되었는지를 아이디로 멤버를 찾기
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //spring1 이름으로 객체 찾기

        assertThat(result).isEqualTo(member1);  //위에서 생성한 객체와 동일한 객체인지 확인

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll(); //전체 목록 가져오기

        assertThat(result.size()).isEqualTo(2); //전체 목록의 개수가 총 2개인지 확인
    }
}
