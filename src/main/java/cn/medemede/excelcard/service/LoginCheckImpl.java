package cn.medemede.excelcard.service;

import cn.medemede.excelcard.dao.UserMapper;
import cn.medemede.excelcard.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginCheckImpl implements LoginCheck {

    @Resource
    private UserMapper userMapper;

    public String check(User user) {
        String forword = "failed";
        User user1=userMapper.selectByPrimaryKey(user.getId());
        if(user1!=null){
            if(user1.getPwd().equals(user.getPwd())){
                if(user1.getPower()==0){
                    forword="teacher";
                }else if(user1.getPower()==1){
                    forword="admin";
                }
            }
        }

        return forword;
    }
}
