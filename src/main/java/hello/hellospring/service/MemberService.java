package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {    //service클래스에는 비즈니스에 가까운 기능을 구현하기 때문에 비즈니스와 관련된 용어를 사용함.

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {   //외부에서 객체를 생성해서 넣어주는 것을 Denpendency Injection(DI)라고함.

        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
       // 같은 이름이 있는 중복 회원X

       // long start = System.currentTimeMillis();

       // try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
      //  } finally {
      //      long finish = System.currentTimeMillis();
      //      long timeMs = finish - start;
      //      System.out.println("join = " + timeMs + "ms");

      //  }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   //값이 있는 경우(Optional이라서 가능)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 } );
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
     //   long start = System.currentTimeMillis();

     //   try {
            return memberRepository.findAll();
     //   } finally {
     //       long finish = System.currentTimeMillis();
     //       long timeMs = finish - start;
     //       System.out.println("findMembers = " + timeMs + "ms");
     //   }

    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }

}
