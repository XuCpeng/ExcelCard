package cn.medemede.excelcard.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetData {
    private Integer id;

    private String sheetName;

    private String userId;

    private String dataList;

    private ArrayList<String> dataValue;

    public SheetData(Integer id, String sheetName, String userId, String dataList) {
        this.id = id;
        this.sheetName = sheetName;
        this.userId = userId;
        this.dataList = dataList;

        //通过分割字符串的方式将String转换为List
        assert dataList != null;
        this.dataValue= new ArrayList<String>(Arrays.asList(dataList.split(",")));
    }

    public SheetData() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName == null ? null : sheetName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDataList() {
        return dataList;
    }

    public void setDataList(String dataList) {
        this.dataList = dataList == null ? null : dataList.trim();

        //通过分割字符串的方式将String转换为List
        assert dataList != null;
        this.dataValue= new ArrayList<String>(Arrays.asList(dataList.split(",")));
    }

    public ArrayList<String> getDataValue() {
        return dataValue;
    }

    public void setDataValue(ArrayList<String> dataValue) {
        this.dataValue = dataValue;

        //将List组合为String
        StringBuilder cList= new StringBuilder();
        for (int i = 0; i < dataValue.size() - 1; i++) {
            cList.append(dataValue.get(i)).append(",");
        }
        cList.append(dataValue.get(dataValue.size()-1));
        this.dataList= cList.toString();
    }
}