package cn.medemede.excelcard.controller;

import cn.medemede.excelcard.dao.SheetColumnMapper;
import cn.medemede.excelcard.dao.SheetDataMapper;
import cn.medemede.excelcard.dao.UserMapper;
import cn.medemede.excelcard.model.SheetColumn;
import cn.medemede.excelcard.model.User;
import cn.medemede.excelcard.service.LoginCheckImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Saber
 */
@Controller
public class LoginController {

    @Resource
    private LoginCheckImpl loginCheck;

    @Resource
    private SheetColumnMapper sheetColumnMapper;

    @Resource
    private SheetDataMapper sheetDataMapper;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String userId,
                             @RequestParam String pwd,
                             @RequestParam String checkcode,
                             ModelAndView mv,
                             HttpServletRequest request){
        String forword="failed";
        if(checkcode.toLowerCase().equals(request.getSession().getAttribute("checkCode"))) {
            User user = new User();
            user.setId(userId);
            user.setPwd(pwd);
            forword = loginCheck.check(user);
            if(!forword.equals("failed")) {

                request.getSession().setAttribute("user",user);

                if (forword.equals("teacher")) {
                    List<SheetColumn> sheetColumnList = sheetColumnMapper.selectByLocked();
                    List<SheetColumn> selectSheetColumnList = new ArrayList<SheetColumn>();
                    Date nowDate = new Date();
                    for (SheetColumn sheetColumn : sheetColumnList) {
                        if (!sheetColumn.getValidity().before(nowDate)) {
                            selectSheetColumnList.add(sheetColumn);
                        }
                    }
                    mv.addObject("finishedSheetName",sheetDataMapper.findSheetNameByUserId(userId));
                    mv.addObject("sheetColumnList", selectSheetColumnList);
                } else if (forword.equals("admin")) {
                    List<SheetColumn> sheetColumnList = sheetColumnMapper.selectAll();
                    mv.addObject("sheetColumnList", sheetColumnList);
                }
            }
        }
        mv.setViewName(forword);
        return mv;
    }


    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        int width = 60;
        int height = 20;
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //创建内存图像并获得其图形上下文
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //产生随机验证码
        //定义验证码的字符串
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i=0; i<4; i++)
        {
            int rand = (int)(Math.random()*36);
            rands[i] = chars.charAt(rand);
        }

        //产生图像
        //画背景
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);

        //随机产生120个干扰点
        for (int i=0; i<120; i++)
        {
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            int red = (int)(Math.random()*255);
            int green = (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));

        //在不同的高度上输出验证码的不同字符
        g.drawString(""+rands[0], 1, 17);
        g.drawString(""+rands[1], 16, 15);
        g.drawString(""+rands[2], 31, 18);
        g.drawString(""+rands[3], 46, 16);
        g.dispose();

        //将图像输出到客户端
        ServletOutputStream sos = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image,"JPEG",baos);
        byte[] buffer = baos.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        //将验证码放到session中
        session.setAttribute("checkCode", new String(rands).toLowerCase());
    }



}
