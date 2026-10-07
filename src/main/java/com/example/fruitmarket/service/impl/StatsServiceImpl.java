package com.example.fruitmarket.service.impl;

import com.example.fruitmarket.entity.vo.DashboardVO;
import com.example.fruitmarket.mapper.StatsMapper;
import com.example.fruitmarket.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsMapper statsMapper;

    @Override
    public DashboardVO getDashboard() {
        DashboardVO vo = new DashboardVO();
        vo.setToday(statsMapper.todayStats());
        vo.setTotal(statsMapper.totalStats());
        vo.setMemberCount(statsMapper.memberCount());
        vo.setWarnList(statsMapper.warnList());
        vo.setTop5(statsMapper.top5());
        vo.setCategorySales(statsMapper.categorySales());
        vo.setMemberRank(statsMapper.memberRank());
        vo.setPayMethod(statsMapper.payMethodStats());
        return vo;
    }
}