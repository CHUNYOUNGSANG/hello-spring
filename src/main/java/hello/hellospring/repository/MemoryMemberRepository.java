package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   //회원을 저장할 하나의 데이터베이스라고 생각하면 됨.
    //실무에서 동시성 문제로 ConcurrentHashMap 써야할 수 있으나 지금은 예제라서 HashMap 사용한다
    private static long sequence = 0L;  //일련 번호
    //실무에서 동시성 문제로 AtomicLong 등을 사용해야 하나 여기서도 예제상 그냥 Long 사용한다.

    @Override   //null체크를 Optional로 감싸서 처리한다.
    public Member save(Member member) {
        member.setId(++sequence);   //멤버를 저장할 때 일련번호 값을 1 증가 시켜주기,
        store.put(member.getId(), member);
        return member;
    }

    @Override   //findAny()는 가장 먼저 찾은 요소를 Optional로 변환한다.
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //만약 null을 반환한 경우를 대비해서 Optional을 사용해서 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //같은 name을 가지고 있는 객체를 찾으면 반환, 없으면 null반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}


