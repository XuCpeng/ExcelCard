package cn.medemede.excelcard.controller;

import cn.medemede.excelcard.dao.SheetColumnMapper;
import cn.medemede.excelcard.dao.SheetDataMapper;
import cn.medemede.excelcard.model.SheetColumn;
import cn.medemede.excelcard.model.SheetData;
import cn.medemede.excelcard.model.User;
import cn.medemede.excelcard.service.ExcelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {

    @Resource
    private ExcelServiceImpl excelService;

    @Resource
    private SheetColumnMapper sheetColumnMapper;

    @Resource
    private SheetDataMapper sheetDataMapper;


    @RequestMapping("/uploadteacher")
    /*
    教师的上传功能
     */
    public ModelAndView upLoadtTeacher(@RequestParam("excelfile") MultipartFile multipartFile,
                               ModelAndView mv,
                               HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        String fileName=multipartFile.getOriginalFilename();
        File file=new File("classpath:"+fileName);
        try {
            multipartFile.transferTo(file);
            excelService.excelToDb(fileName,file,user);
            String info="上传成功！";
            List<SheetColumn> sheetColumnList = sheetColumnMapper.selectByLocked();
            List<SheetColumn> selectSheetColumnList = new ArrayList<SheetColumn>();
            Date nowDate = new Date();
            for (SheetColumn sheetColumn : sheetColumnList) {
                if (!sheetColumn.getValidity().before(nowDate)) {
                    selectSheetColumnList.add(sheetColumn);
                }
            }
            mv.addObject("finishedSheetName",sheetDataMapper.findSheetNameByUserId(user.getId()));
            mv.addObject("sheetColumnList", selectSheetColumnList);
            mv.addObject("info",info);
            mv.setViewName("teacher");
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
        }

        return mv;
    }

    /*
    教师的下载模板文件功能
     */
    @RequestMapping("/downloadteacher/{sheetName}")
    public ModelAndView downloadTeacher(@PathVariable String sheetName,
                                        ModelAndView mv,
                                        HttpServletResponse response){
        String info;
        SheetColumn sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xlsx");
        if(sheetColumn==null){
            sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName+".xls");
        }
        if (sheetColumn!=null) {
            excelService.exporeExcel(sheetColumn, response);
            info = "下载成功!";
        }else {
            info="此表格不存在！";
        }
        mv.addObject("info",info);
        mv.setViewName("teacher");
        return mv;
    }

    /*
    教师下载已完成的文件
     */
    @RequestMapping("/downloadfinished/{sheetName}")
    public ModelAndView downloadFinished(@PathVariable String sheetName,
                                         ModelAndView mv,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        String info = "导出失败！";
        String goodName=sheetName+".xlsx";
        User user= (User) request.getSession().getAttribute("user");
        SheetColumn sheetColumn=sheetColumnMapper.selectByPrimaryKey(goodName);
        if(sheetColumn==null){
            goodName=sheetName+".xls";
            sheetColumn=sheetColumnMapper.selectByPrimaryKey(goodName);
        }
        SheetData sheetData = sheetDataMapper.findByUseridAndSheetName(user.getId(), goodName);
        if(sheetColumn!=null&&sheetData!=null) {
            ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
            data.add(sheetData.getDataValue());
            excelService.exportExcel2(sheetColumn, data, response);
            info = "导出成功！";
        }
        mv.addObject("info",info);
        mv.setViewName("teacher");
        return mv;
    }

    @RequestMapping("/uploadadmin")
    public ModelAndView uploadAdmin(@RequestParam("excelfile") MultipartFile multipartFile,
                                    @RequestParam String setdate,
                                    ModelAndView mv,
                                    HttpServletRequest request) throws ParseException {
        String fileName=multipartFile.getOriginalFilename();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=simpleDateFormat.parse(setdate);
        File file=new File("classpath:"+fileName);
        try {
            multipartFile.transferTo(file);
            excelService.excelToDb2(fileName,file,date);
            List<SheetColumn> sheetColumnList = sheetColumnMapper.selectAll();
            mv.addObject("sheetColumnList",sheetColumnList);
            mv.setViewName("admin");
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
        }
        return  mv;
    }
    /*
    管理员下载整合
     */
    @RequestMapping("/downloadadmin/{sheetName}")
    public ModelAndView downLoadAdmin(@PathVariable String sheetName,
                                 ModelAndView mv,
                                 HttpServletResponse response){

        SheetColumn sheetColumn=sheetColumnMapper.selectByPrimaryKey(sheetName);
        List<SheetData> sheetDataList=sheetDataMapper.selectBySheetName(sheetName);
        ArrayList<ArrayList<String>> sheetData=new ArrayList<ArrayList<String>>();
        for (SheetData aSheetDataList : sheetDataList) {
            sheetData.add(aSheetDataList.getDataValue());
        }
        excelService.exportExcel2(sheetColumn,sheetData,response);
        String info="导出成功！";
        mv.addObject("info",info);
        mv.setViewName("admin");
        return mv;
    }
}
