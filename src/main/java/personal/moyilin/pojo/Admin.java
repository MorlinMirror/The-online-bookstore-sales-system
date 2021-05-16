package personal.moyilin.pojo;

import lombok.Data;

@Data
public class Admin {
    int manager_id;
    String manager_name;
    String manager_phone;
    String manager_pw;
    String manager_grade;

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getManager_phone() {
        return manager_phone;
    }

    public void setManager_phone(String manager_phone) {
        this.manager_phone = manager_phone;
    }

    public String getManager_pw() {
        return manager_pw;
    }

    public void setManager_pw(String manager_pw) {
        this.manager_pw = manager_pw;
    }

    public String getManager_grade() {
        return manager_grade;
    }

    public void setManager_grade(String manager_grade) {
        this.manager_grade = manager_grade;
    }
}
