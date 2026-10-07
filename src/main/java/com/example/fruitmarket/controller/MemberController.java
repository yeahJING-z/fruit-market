package com.example.fruitmarket.controller;

import com.example.fruitmarket.common.BizException;
import com.example.fruitmarket.common.Result;
import com.example.fruitmarket.entity.Member;
import com.example.fruitmarket.entity.Order;
import com.example.fruitmarket.entity.PointsRecord;
import com.example.fruitmarket.mapper.MemberMapper;
import com.example.fruitmarket.mapper.OrdersMapper;
import com.example.fruitmarket.mapper.PointsRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberMapper memberMapper;
    private final OrdersMapper ordersMapper;
    private final PointsRecordMapper pointsRecordMapper;

    /** 会员信息 */
    @GetMapping("/{id}")
    public Result<Member> info(@PathVariable Integer id) {
        Member m = memberMapper.getById(id);
        if (m == null) throw new BizException("会员不存在");
        return Result.ok(m);
    }

    /** 我的订单 */
    @GetMapping("/{id}/orders")
    public Result<List<Order>> myOrders(@PathVariable Integer id) {
        return Result.ok(ordersMapper.listByMember(id));
    }

    /** 我的积分流水 */
    @GetMapping("/{id}/points")
    public Result<List<PointsRecord>> myPoints(@PathVariable Integer id) {
        return Result.ok(pointsRecordMapper.listByMember(id));
    }
}