package cn.medemede.excelcard.service;

import cn.medemede.excelcard.model.SheetColumn;
import cn.medemede.excelcard.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface ExcelService {
    public void excelToDb(String sheetName, File sheetFile, User user) throws IOException;
    public boolean exporeExcel(SheetColumn sheetColumn,HttpServletResponse response);
    public void excelToDb2(String sheetName,File fileName,Date validity) throws IOException;
    public boolean exportExcel2(SheetColumn sheetColumn,
                               ArrayList<ArrayList<String>> dataList,
                               HttpServletResponse response);
}
