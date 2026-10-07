package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.PointsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointsRecordMapper {
    int insert(PointsRecord record);
    List<PointsRecord> listByMember(@Param("memberId") Integer memberId);   // ★ 新增
}