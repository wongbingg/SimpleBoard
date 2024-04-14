package com.simpleboard.demo;

import com.simpleboard.demo.member.Grade;
import com.simpleboard.demo.member.Member;
import com.simpleboard.demo.member.MemberService;
import com.simpleboard.demo.member.MemberServiceImpl;
import com.simpleboard.demo.order.Order;
import com.simpleboard.demo.order.OrderService;
import com.simpleboard.demo.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        long memberId = 1L;
        Member member = new Member(memberId, "실험체1", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "USB", 24900);
        System.out.println("order = " + order);
    }
}
