package cn.medemede.excelcard.controller;

import cn.medemede.excelcard.dao.UserMapper;
import cn.medemede.excelcard.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String userId,
                                 @RequestParam String pwd,
                                 @RequestParam Integer power,
                                 ModelAndView mv){

        User user=new User(userId,pwd,power);
        String info = "注册失败";
        if(userMapper.selectByPrimaryKey(userId)!=null){
            info = "注册失败,用户名已存在！";
            mv.setViewName("register");
        }else {
            int f = userMapper.insertSelective(user);
            if (f != -1) {
                info="注册成功，请登陆！";
                mv.setViewName("index");
            }else {
                mv.setViewName("register");
            }
        }
        mv.addObject("info", info);
        return mv;
    }
}
