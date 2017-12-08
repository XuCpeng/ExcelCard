package cn.medemede.excelcard.model;

public class SheetColumn {
    private Integer id;

    private String sheetName;

    private String columnList;

    public SheetColumn(Integer id, String sheetName, String columnList) {
        this.id = id;
        this.sheetName = sheetName;
        this.columnList = columnList;
    }

    public SheetColumn() {
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

    public String getColumnList() {
        return columnList;
    }

    public void setColumnList(String columnList) {
        this.columnList = columnList == null ? null : columnList.trim();
    }
}