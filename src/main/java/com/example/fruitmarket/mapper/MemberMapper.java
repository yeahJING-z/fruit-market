package com.example.fruitmarket.mapper;

import com.example.fruitmarket.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member getById(@Param("memberId") Integer memberId);
    Member findByPhone(@Param("phone") String phone);
    int addPoints(@Param("memberId") Integer memberId,
                  @Param("points") Integer points);
}