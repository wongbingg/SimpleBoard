package com.simpleboard.demo.order;

import com.simpleboard.demo.discount.DiscountPolicy;
import com.simpleboard.demo.discount.FixDiscountPolicy;
import com.simpleboard.demo.member.Member;
import com.simpleboard.demo.member.MemberRepository;
import com.simpleboard.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
