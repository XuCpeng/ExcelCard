package cn.medemede.excelcard.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SheetColumn {

    private String sheetName;

    private Date validity;

    private Integer locked;

    private String columnList;

    private ArrayList<String> columnName;

    public SheetColumn(String sheetName, Date validity, Integer locked, String columnList) {
        this.sheetName = sheetName;
        this.validity = validity;
        this.locked = locked;
        this.columnList = columnList;

        //通过分割字符串的方式将String转换为List
        assert columnList != null;
        this.columnName= new ArrayList<String>(Arrays.asList(columnList.split(",")));
    }

    public SheetColumn() {
        super();
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName == null ? null : sheetName.trim();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getColumnList() {
        return columnList;
    }

    public void setColumnList(String columnList) {
        this.columnList = columnList == null ? null : columnList.trim();

        //通过分割字符串的方式将String转换为List
        assert columnList != null;
        this.columnName= new ArrayList<String>(Arrays.asList(columnList.split(",")));

    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(ArrayList<String> columnName) {
        this.columnName = columnName;

        //将List组合为String
        StringBuilder cList= new StringBuilder();
        for (int i = 0; i < columnName.size() - 1; i++) {
            cList.append(columnName.get(i)).append(",");
        }
        cList.append(columnName.get(columnName.size()-1));
        this.columnList= cList.toString();

    }

    @Override
    public String toString() {
        return "SheetColumn{" +
                "sheetName='" + sheetName + '\'' +
                ", validity=" + validity +
                ", locked=" + locked +
                ", columnList='" + columnList + '\'' +
                ", columnName=" + columnName +
                '}';
    }
}