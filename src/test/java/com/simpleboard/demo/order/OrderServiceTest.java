package com.simpleboard.demo.order;

import com.simpleboard.demo.member.Grade;
import com.simpleboard.demo.member.Member;
import com.simpleboard.demo.member.MemberService;
import com.simpleboard.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    @Test
    void 주문하기() {
        // given
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        int usbPrice = 24900;
        Member member = new Member(1L, "실험체1", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "USB", usbPrice);
        int resultPrice = order.calculatePrice();
        Assertions.assertThat(resultPrice).isEqualTo(usbPrice - 1000);

    }
}
