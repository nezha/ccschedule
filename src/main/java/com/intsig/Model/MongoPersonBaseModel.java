package com.intsig.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yi_zhang on 2016/7/26.
 */
@Document(collection = "users")
public class MongoPersonBaseModel {
    @Id
    private String id;
    String name;
    String pwd;

    public MongoPersonBaseModel(String name, String pwd) {
        super();
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + name + ", password=" + pwd + "]";
    }
}
