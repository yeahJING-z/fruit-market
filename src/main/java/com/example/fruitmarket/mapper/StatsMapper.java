package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.vo.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface StatsMapper {

    Map<String, Object> todayStats();

    Map<String, Object> totalStats();

    Integer memberCount();

    List<WarnItemVO> warnList();

    List<TopProductVO> top5();

    List<CategorySalesVO> categorySales();

    List<MemberRankVO> memberRank();

    List<PayMethodVO> payMethodStats();
}