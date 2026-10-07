package com.example.fruitmarket.controller;

import com.example.fruitmarket.common.Result;
import com.example.fruitmarket.entity.vo.DashboardVO;
import com.example.fruitmarket.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StatsController {

    private final StatsService statsService;

    /** 数据看板 */
    @GetMapping("/dashboard")
    public Result<DashboardVO> dashboard() {
        return Result.ok(statsService.getDashboard());
    }
}