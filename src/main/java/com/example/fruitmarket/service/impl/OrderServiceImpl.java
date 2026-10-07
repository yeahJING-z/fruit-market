package com.example.fruitmarket.service.impl;

import com.example.fruitmarket.common.BizException;
import com.example.fruitmarket.entity.*;
import com.example.fruitmarket.entity.dto.OrderCreateDTO;
import com.example.fruitmarket.mapper.*;
import com.example.fruitmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersMapper ordersMapper;
    private final OrderItemMapper orderItemMapper;
    private final InventoryMapper inventoryMapper;
    private final MemberMapper memberMapper;
    private final PointsRecordMapper pointsRecordMapper;
    private final StockLogMapper stockLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderCreateDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new BizException("购物车为空");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> details = new ArrayList<>();

        // ① 逐项锁定库存行 + 校验 + 计算金额
        for (OrderCreateDTO.Item it : dto.getItems()) {
            if (it.getProductId() == null || it.getQuantity() == null
                    || it.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BizException("商品数量非法");
            }

            Product p = inventoryMapper.selectProductForUpdate(it.getProductId());
            if (p == null) throw new BizException("商品不存在：ID " + it.getProductId());

            if (p.getCurrentStock().compareTo(it.getQuantity()) < 0) {
                throw new BizException(p.getName() + " 库存不足，仅剩 "
                        + p.getCurrentStock() + p.getUnit());
            }

            BigDecimal sub = p.getPrice().multiply(it.getQuantity())
                    .setScale(2, RoundingMode.HALF_UP);
            total = total.add(sub);

            OrderItem item = new OrderItem();
            item.setProductId(p.getProductId());
            item.setQuantity(it.getQuantity());
            item.setUnitPrice(p.getPrice());
            item.setSubtotal(sub);
            details.add(item);
        }

        // ② 校验会员
        Member member = null;
        if (dto.getMemberId() != null) {
            member = memberMapper.getById(dto.getMemberId());
            if (member == null) throw new BizException("会员不存在");
        }

        // ③ 写订单主表
        Order order = new Order();
        order.setOrderNo(genOrderNo());
        order.setMemberId(member != null ? member.getMemberId() : null);
        order.setStaffId(null);
        order.setTotalAmount(total);
        order.setPayMethod(dto.getPayMethod() == null ? "微信" : dto.getPayMethod());
        order.setStatus("PAID");
        order.setCreatedAt(LocalDateTime.now());
        ordersMapper.insert(order);

        // ④ 明细 + 扣库存 + 写流水
        for (OrderItem d : details) {
            d.setOrderId(order.getOrderId());
            orderItemMapper.insert(d);

            Product stockProduct = inventoryMapper.selectProductForUpdate(d.getProductId());
            BigDecimal afterStock = stockProduct.getCurrentStock().subtract(d.getQuantity());

            inventoryMapper.decreaseStock(d.getProductId(), d.getQuantity());

            StockLog log = new StockLog();
            log.setProductId(d.getProductId());
            log.setChangeType("SALE_OUT");
            log.setChangeQty(d.getQuantity().negate());
            log.setStockAfter(afterStock);
            log.setRefOrderId(order.getOrderId());
            log.setCreatedAt(LocalDateTime.now());
            stockLogMapper.insert(log);
        }

        // ⑤ 会员积分：1 元 = 1 分
        if (member != null) {
            int earn = total.intValue();
            if (earn > 0) {
                memberMapper.addPoints(member.getMemberId(), earn);

                PointsRecord pr = new PointsRecord();
                pr.setMemberId(member.getMemberId());
                pr.setChangePoints(earn);
                pr.setReason("消费赠送");
                pr.setOrderId(order.getOrderId());
                pr.setCreatedAt(LocalDateTime.now());
                pointsRecordMapper.insert(pr);
            }
        }

        return order;
    }

    @Override
    public List<Order> list() {
        return ordersMapper.list();
    }

    private String genOrderNo() {
        String ts = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "SO" + ts + (int)(Math.random() * 1000);
    }
}