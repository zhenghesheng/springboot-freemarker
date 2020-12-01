package com.buba.Controller;

import com.buba.Entity.User;
import com.buba.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserRepository userRepository;

    @GetMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping("/tologin")
    public String login(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        // 我们判断，如果登录名不是空，并且，密码是 123456 就登录成功（暂不涉及数据库）
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 登录成功，就跳转到下一个页面
            session.setAttribute("username",username);
            return "redirect:/test/test";
        } else {
            // 登录失败，刷新本登录页
            return "login";
        }
    }

    @RequestMapping("/test")
    public String Test(Map<String,Object> map) {
        map.put("name","张三");
        List<String> name = new ArrayList<>();
        name.add("李四");
        name.add("王五");
        name.add("赵六");
        map.put("userlist",name);
        return "index";
    }
}
