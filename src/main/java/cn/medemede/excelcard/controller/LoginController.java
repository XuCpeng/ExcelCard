package cn.medemede.excelcard.controller;

import cn.medemede.excelcard.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Saber
 */
@Controller
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login/{userId}")
    public ModelAndView test(@PathVariable String userId,
                     ModelAndView mv){

        mv.addObject("user",userMapper.selectByPrimaryKey(userId));
        mv.setViewName("success");
        return mv;

    }

}
