package com.example.fruitmarket.entity.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DashboardVO {
    private Map<String, Object> today;
    private Map<String, Object> total;
    private Integer memberCount;
    private List<WarnItemVO> warnList;
    private List<TopProductVO> top5;
    private List<CategorySalesVO> categorySales;
    private List<MemberRankVO> memberRank;
    private List<PayMethodVO> payMethod;
}