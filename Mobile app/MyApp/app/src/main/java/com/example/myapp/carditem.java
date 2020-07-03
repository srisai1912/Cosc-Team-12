package com.example.myapp;

public class carditem {
            private String roleid;
            private String role;
            private String prereq;
            private String deptname;


            public carditem()
            {

            }

            public carditem(String roleid,String role,String prereq,String deptname)
            {
                this.roleid=roleid;
                this.role=role;
               this.deptname=deptname;
                this.prereq=prereq;
            }
            public String getRoleid() { return roleid; }
            public String getRole(){return role;}
            public String getPrereq(){return prereq;}
            public String getDeptname(){return deptname;}


}
