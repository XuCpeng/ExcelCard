package cn.medemede.excelcard.model;

public class SheetData {
    private Integer id;

    private Integer sheetId;

    private String userId;

    private String dataList;

    public SheetData(Integer id, Integer sheetId, String userId, String dataList) {
        this.id = id;
        this.sheetId = sheetId;
        this.userId = userId;
        this.dataList = dataList;
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

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
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
    }
}