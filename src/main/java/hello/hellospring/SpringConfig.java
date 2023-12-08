package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*  private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

/*    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    위에와 같이 추가하거나 TimeTraceAop 클래스 위에 Component 어노테이션을 추가한다. @Component
*/
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}


// 1. setter 방식
// @Autowired
// public setMemberRepository(MemoryRepository memoryRepository) {
//     this.memoryRepository = memoryRepository
// }

// 2. 필드방식
// @Autowired
// public void MemberRepository memberRepository;

// 3. 생성자방식
// @Autowired
// public MemberService memberService(MemberRepository memberRepository) {
//     this.memberRepository = memberRepository
// }

//public class MemberService {
//   private final MemberRepository memberRepository = new MemoryMemberRepository();