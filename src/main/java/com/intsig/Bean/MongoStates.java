package com.intsig.Bean;

/**
 * Created by yi_zhang on 2016/7/25.
 */
public class MongoStates {

    private String dataCategory;
    private String fieldOne;
    private String fieldTwo;
    private String dbName;
    private String colName;
    private int cnt;
    private long timeStamp;
    private int  id;

    public MongoStates(long timeStamp, int cnt, String colName, String dbName, String fieldTwo, String fieldOne, String dataCategory) {
        this.timeStamp = timeStamp;
        this.cnt = cnt;
        this.colName = colName;
        this.dbName = dbName;
        this.fieldTwo = fieldTwo;
        this.fieldOne = fieldOne;
        this.dataCategory = dataCategory;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getDataCategory() {
        return dataCategory;
    }

    public void setDataCategory(String dataCategory) {
        this.dataCategory = dataCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
