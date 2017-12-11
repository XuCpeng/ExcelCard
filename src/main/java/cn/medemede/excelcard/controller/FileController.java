package cn.medemede.excelcard.controller;


import cn.medemede.excelcard.dao.SheetColumnMapper;
import cn.medemede.excelcard.model.SheetColumn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class FileController {

    @Resource
    private SheetColumnMapper sheetColumnMapper;

    @RequestMapping("/updatelocked/{sheetName}")
    public ModelAndView updateLocked(@PathVariable String sheetName,
                                     ModelAndView mv){
        SheetColumn sheetColumn;
        sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xlsx");
        if(sheetColumn==null){
            sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xls");
        }
        if(sheetColumn!=null) {

            sheetColumn.setLocked(0);
            sheetColumnMapper.updateByPrimaryKey(sheetColumn);
        }
        List<SheetColumn> sheetColumnList = sheetColumnMapper.selectAll();
        mv.addObject("sheetColumnList", sheetColumnList);
        mv.setViewName("admin");
        return mv;
    }

    @RequestMapping("/updateunlocked/{sheetName}")
    public ModelAndView updateUnLocked(@PathVariable String sheetName,
                                     ModelAndView mv){
        SheetColumn sheetColumn;
        sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xlsx");
        if(sheetColumn==null){
            sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xls");
        }
        if(sheetColumn!=null) {

            sheetColumn.setLocked(1);
            sheetColumnMapper.updateByPrimaryKey(sheetColumn);
        }
        List<SheetColumn> sheetColumnList = sheetColumnMapper.selectAll();
        mv.addObject("sheetColumnList", sheetColumnList);
        mv.setViewName("admin");
        return mv;
    }



}
