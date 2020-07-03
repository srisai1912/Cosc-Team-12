package com.example.myapp;

public class carditem2 {
    private String roleid;
    private String appid;
    private String deptname;
    private String vacantpos;
    private String status;
    public carditem2()
    {

    }
    public carditem2(String appid,String deptname,String vacantpos,String roleid,String status)
    {
        this.roleid=roleid;
        this.appid=appid;
        this.deptname=deptname;
        this.vacantpos=vacantpos;
        this.status=status;

    }
    public String getRoleid()
    {
        return roleid;
    }
    public String getAppid()
    {
        return appid;
    }
    public String getDeptname()
    {
        return deptname;
    }
    public String getVacantpos()
    {
        return vacantpos;
    }
    public String getStatus()
    {
        return status;
    }

}