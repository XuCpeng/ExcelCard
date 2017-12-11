package cn.medemede.excelcard.model;

public class User {
    private String id;

    private String pwd;

    private Integer power;

    public User(String id, String pwd, Integer power) {
        this.id = id;
        this.pwd = pwd;
        this.power = power;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}