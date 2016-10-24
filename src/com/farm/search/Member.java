package com.farm.search;

/**
 * Created by leaxus on 2016/9/28.
 */

public class Member {

    private int uid;

    private String employee_id;

    private String nick_Name;

    private String status;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getNick_Name() {
        return nick_Name;
    }

    public void setNickName(String nick_Name) {
        this.nick_Name = nick_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Member{" +
                "employee_id='" + employee_id + '\'' +
                ", uid=" + uid +
                ", nick_Name='" + nick_Name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (getUid() != member.getUid()) return false;
        if (getEmployee_id() != null ? !getEmployee_id().equals(member.getEmployee_id()) : member.getEmployee_id() != null)
            return false;
        if (getNick_Name() != null ? !getNick_Name().equals(member.getNick_Name()) : member.getNick_Name() != null)
            return false;
        return getStatus() != null ? getStatus().equals(member.getStatus()) : member.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getUid();
        result = 31 * result + (getEmployee_id() != null ? getEmployee_id().hashCode() : 0);
        result = 31 * result + (getNick_Name() != null ? getNick_Name().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
