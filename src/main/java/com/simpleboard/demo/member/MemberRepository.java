package com.simpleboard.demo.member;

public interface MemberRepository { // Option + Enter 로 해당 인터페이스로 할 수 있는 기능에 접근
    void save(Member member);
    Member findById(Long memberId);
}
