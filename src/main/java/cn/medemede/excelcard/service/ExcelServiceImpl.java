package cn.medemede.excelcard.service;

import cn.medemede.excelcard.dao.SheetColumnMapper;
import cn.medemede.excelcard.dao.SheetDataMapper;
import cn.medemede.excelcard.model.SheetColumn;
import cn.medemede.excelcard.model.SheetData;
import cn.medemede.excelcard.model.User;
import cn.medemede.excelcard.util.ExportExcel2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private SheetDataMapper sheetDataMapper;

    @Resource
    private SheetColumnMapper sheetColumnMapper;

    /**
     * 将Excel中的内容导入数据库，教师
     */
    public void excelToDb(String sheetName, File sheetFile, User user) throws IOException {
        SheetData sheetData=new SheetData();
        sheetData.setUserId(user.getId());
        sheetData.setSheetName(sheetName);

        //声明工作簿
        Workbook workbook;
        //声明工作表
        Sheet sheet;
        try {
            //初始化工作簿，Excel 2007及以上，xlsx
            workbook = new XSSFWorkbook(sheetFile);
            //初始化工作表,默认第一个工作表
            sheet = workbook.getSheetAt(0);
            sheetData.setDataValue(getSheetData(sheet));

        } catch (InvalidFormatException e) {
            //初始化工作簿，Excel 2007以下
            workbook = new HSSFWorkbook(new FileInputStream(sheetFile));
            //初始化工作表
            sheet = workbook.getSheetAt(0);
            sheetData.setDataValue(getSheetData(sheet));
        }
        workbook.close();
        SheetData sheetDataL=sheetDataMapper.findByUseridAndSheetName(user.getId(),sheetName);
        if(sheetDataL!=null){
            sheetData.setId(sheetDataL.getId());
            sheetDataMapper.updateByPrimaryKeySelective(sheetData);
        }else {
            sheetDataMapper.insertSelective(sheetData);
        }
    }

    private ArrayList<String> getSheetData(Sheet sheet){
        ArrayList<String> sheetDataList=new ArrayList<String>();
        Row row=sheet.getRow(2);
        for (int i = 0; i <row.getPhysicalNumberOfCells(); i++) {
            Cell cell=row.getCell(i);
            cell.setCellType(CellType.STRING);
            sheetDataList.add(cell.getStringCellValue());
        }
        return sheetDataList;
    }

    /*
    教师的文件下载
     */

    public boolean exporeExcel(SheetColumn sheetColumn,HttpServletResponse response){
        XSSFWorkbook workbook = new XSSFWorkbook(); //创建工作簿
        XSSFSheet sheet = workbook.createSheet();  //创建工作表
        workbook.setSheetName(0, sheetColumn.getSheetName());  //设置工作表名称
        List<String> columnName=sheetColumn.getColumnName();

        //标题字体样式
        XSSFFont font1 = workbook.createFont();
        font1.setFontHeightInPoints((short) 24);
        //通用字体样式
        XSSFFont font3 = workbook.createFont();
        //通用样式库
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        cellStyle.setWrapText(true);
        cellStyle.setFont(font3);
        cellStyle.setBorderBottom(BorderStyle.THIN); //设置边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //标题样式库
        XSSFCellStyle cellTitleStyle = workbook.createCellStyle();
        cellTitleStyle.setAlignment(HorizontalAlignment.CENTER);   //居中对齐
        cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);  //垂直居中
        cellTitleStyle.setFont(font1);


        XSSFRow titleRow = sheet.createRow(0); //创建标题行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnName.size()-1)); //合并单元格

        XSSFCell titleCell = titleRow.createCell(0); //创建单元格
        titleCell.setCellValue(sheetColumn.getSheetName().split("\\.")[0]);  //设置单元格内容
        titleCell.setCellStyle(cellTitleStyle);  //使用样式库

        //生成表头
        XSSFRow rowTitle=sheet.createRow(1);
        for (int i = 0; i < columnName.size(); i++) {
            XSSFCell cell=rowTitle.createCell(i);
            cell.setCellValue(columnName.get(i));
            cell.setCellStyle(cellStyle);
        }
        return ExportExcel2.exportExcel(workbook,sheetColumn.getSheetName(),response);
    }


    public void excelToDb2(String sheetName,File fileName,Date validity) throws IOException {
        SheetColumn sheetColumn=new SheetColumn();
        sheetColumn.setSheetName(sheetName);
        sheetColumn.setValidity(validity);
        sheetColumn.setLocked(1);

        //声明工作簿
        Workbook workbook;
        //声明工作表
        Sheet sheet;
        try {
            //初始化工作簿，Excel 2007及以上，xlsx
            workbook = new XSSFWorkbook(fileName);
            //初始化工作表,默认第一个工作表
            sheet = workbook.getSheetAt(0);
            sheetColumn.setColumnName(getSheetData(sheet));

        } catch (InvalidFormatException e) {
            //初始化工作簿，Excel 2007以下
            workbook = new HSSFWorkbook(new FileInputStream(fileName));
            //初始化工作表
            sheet = workbook.getSheetAt(0);
            sheetColumn.setColumnName(getSheetData(sheet));
        }
        workbook.close();
        if(sheetColumnMapper.selectByPrimaryKey(sheetName)!=null){
            sheetColumnMapper.updateByPrimaryKey(sheetColumn);
        }else {
            sheetColumnMapper.insert(sheetColumn);
        }
    }

    /*
    管理员的整合下载
     */

    public boolean exportExcel2(SheetColumn sheetColumn,ArrayList<ArrayList<String>> dataList,HttpServletResponse response){
        XSSFWorkbook workbook = new XSSFWorkbook(); //创建工作簿
        XSSFSheet sheet = workbook.createSheet();  //创建工作表
        workbook.setSheetName(0, sheetColumn.getSheetName());  //设置工作表名称
        List<String> columnName=sheetColumn.getColumnName();

        //标题字体样式
        XSSFFont font1 = workbook.createFont();
        font1.setFontHeightInPoints((short) 24);
        //通用字体样式
        XSSFFont font3 = workbook.createFont();
        //通用样式库
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        cellStyle.setWrapText(true);
        cellStyle.setFont(font3);
        cellStyle.setBorderBottom(BorderStyle.THIN); //设置边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //标题样式库
        XSSFCellStyle cellTitleStyle = workbook.createCellStyle();
        cellTitleStyle.setAlignment(HorizontalAlignment.CENTER);   //居中对齐
        cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);  //垂直居中
        cellTitleStyle.setFont(font1);


        XSSFRow titleRow = sheet.createRow(0); //创建标题行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnName.size()-1)); //合并单元格

        XSSFCell titleCell = titleRow.createCell(0); //创建单元格
        titleCell.setCellValue(sheetColumn.getSheetName().split("\\.")[0]);  //设置单元格内容
        titleCell.setCellStyle(cellTitleStyle);  //使用样式库

        //生成表头
        XSSFRow rowTitle=sheet.createRow(1);
        for (int i = 0; i < columnName.size(); i++) {
            XSSFCell cell=rowTitle.createCell(i);
            cell.setCellValue(columnName.get(i));
            cell.setCellStyle(cellStyle);
        }

        //填充数据
        for (int i = 2; i < dataList.size()+2; i++) {
            XSSFRow row=sheet.createRow(i);
            for (int j = 0; j < columnName.size(); j++) {
                XSSFCell cell=row.createCell(j);
                String cellData=dataList.get(i-2).get(j);
                if (cellData!=null) {
                    cell.setCellValue(cellData);
                }else {
                    cell.setCellValue(" ");
                }
                cell.setCellStyle(cellStyle);
            }
        }

        return ExportExcel2.exportExcel(workbook,sheetColumn.getSheetName(),response);
    }

}
