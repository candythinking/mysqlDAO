package com.farm.model;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * Created by Administrator on 2016/10/21.
 */

public class Farm {

    private int id;

    private String province;

    private String market;

    private String type;

    private String name;

    private String standard;

    private String area;

    private String color;

    private String unit;

    private String minPrice;

    private String avgPrice;

    private String maxPrice;

    private Timestamp entertime;

    private Date time;

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", market='" + market + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", standard='" + standard + '\'' +
                ", area='" + area + '\'' +
                ", color='" + color + '\'' +
                ", unit='" + unit + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", entertime='" + entertime + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Farm)) return false;

        Farm farm = (Farm) o;

        if (getId() != farm.getId()) return false;
        if (getProvince() != null ? !getProvince().equals(farm.getProvince()) : farm.getProvince() != null)
            return false;
        if (getMarket() != null ? !getMarket().equals(farm.getMarket()) : farm.getMarket() != null) return false;
        if (getType() != null ? !getType().equals(farm.getType()) : farm.getType() != null) return false;
        if (getName() != null ? !getName().equals(farm.getName()) : farm.getName() != null) return false;
        if (getStandard() != null ? !getStandard().equals(farm.getStandard()) : farm.getStandard() != null)
            return false;
        if (getArea() != null ? !getArea().equals(farm.getArea()) : farm.getArea() != null) return false;
        if (getColor() != null ? !getColor().equals(farm.getColor()) : farm.getColor() != null) return false;
        if (getUnit() != null ? !getUnit().equals(farm.getUnit()) : farm.getUnit() != null) return false;
        if (getMinPrice() != null ? !getMinPrice().equals(farm.getMinPrice()) : farm.getMinPrice() != null)
            return false;
        if (getAvgPrice() != null ? !getAvgPrice().equals(farm.getAvgPrice()) : farm.getAvgPrice() != null)
            return false;
        if (getMaxPrice() != null ? !getMaxPrice().equals(farm.getMaxPrice()) : farm.getMaxPrice() != null)
            return false;
        if (getEntertime() != null ? !getEntertime().equals(farm.getEntertime()) : farm.getEntertime() != null)
            return false;
        return getTime() != null ? getTime().equals(farm.getTime()) : farm.getTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getProvince() != null ? getProvince().hashCode() : 0);
        result = 31 * result + (getMarket() != null ? getMarket().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStandard() != null ? getStandard().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getMinPrice() != null ? getMinPrice().hashCode() : 0);
        result = 31 * result + (getAvgPrice() != null ? getAvgPrice().hashCode() : 0);
        result = 31 * result + (getMaxPrice() != null ? getMaxPrice().hashCode() : 0);
        result = 31 * result + (getEntertime() != null ? getEntertime().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setEntertime(Timestamp entertime) {
        this.entertime = entertime;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {

        return id;
    }

    public String getProvince() {
        return province;
    }

    public String getMarket() {
        return market;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getStandard() {
        return standard;
    }

    public String getArea() {
        return area;
    }

    public String getColor() {
        return color;
    }

    public String getUnit() {
        return unit;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public Timestamp getEntertime() {
        return entertime;
    }

    public Date getTime() {
        return time;
    }
}
