package com.example.fruitmarket.controller;

import com.example.fruitmarket.common.BizException;
import com.example.fruitmarket.common.Result;
import com.example.fruitmarket.entity.Member;
import com.example.fruitmarket.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final MemberMapper memberMapper;

    /**
     * 登录
     * role = "member"：手机号 + 密码（查 member 表）
     * role = "admin" ：账号 admin + 密码 123456（硬编码，简化处理）
     */
    @PostMapping("/login")
    public Result<Member> login(@RequestBody Map<String, String> body) {
        String role = body.getOrDefault("role", "member");
        String account = body.get("account");
        String password = body.get("password");

        if (account == null || password == null) {
            throw new BizException("账号和密码不能为空");
        }

        // 管理员：硬编码（后面可以再改成查 staff 表）
        if ("admin".equals(role)) {
            if ("admin".equals(account) && "123456".equals(password)) {
                Member admin = new Member();
                admin.setMemberId(0);
                admin.setName("管理员");
                admin.setPhone("admin");
                return Result.ok(admin);
            }
            throw new BizException("管理员账号或密码错误");
        }

        // 会员：查 member 表
        Member m = memberMapper.findByPhone(account);
        if (m == null) {
            throw new BizException("该手机号未注册");
        }
        if (!password.equals(m.getPasswordHash())) {
            throw new BizException("密码错误");
        }
        return Result.ok(m);
    }
}