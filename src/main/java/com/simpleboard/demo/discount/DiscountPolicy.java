package com.simpleboard.demo.discount;
import com.simpleboard.demo.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
