package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); //회원를 저장할 하나의 데이터베이스라고 생각하면 됨.
    private static long sequence = 0L; //일련 번호

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버를 저장할 때 일련번호 값을 1 증가 시켜주기. id setting
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //만약 null을 반환할 경우를 대비해서 Optional을 사용해서 감싸준다.
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //같은 name을 가지고 있는 객체를 찾으면 반환. 없으면 null반환
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }

}
