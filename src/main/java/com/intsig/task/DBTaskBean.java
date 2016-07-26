package com.intsig.task;

import com.intsig.Bean.MongoStates;
import com.intsig.Dao.MongoDBDao;
import com.mongodb.client.MongoCollection;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.apache.commons.dbcp.BasicDataSource;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yi_zhang on 2016/7/25.
 * ##执行提起mongodb的信息，然后写入到mysql的处理过程
 */
public class DBTaskBean implements IScheduleTaskDealSingle<MongoStates> {
    @Override
    public boolean execute(MongoStates task, String ownSign) throws Exception {
        return false;
    }

    @Override
    public List<MongoStates> selectTasks(String taskParameter, String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        //待返回的list，用于execute执行
        List<MongoStates> result = new ArrayList<MongoStates>();
        MongoDBDao mongodb = new MongoDBDao("192.168.8.28", 27017, "ccinfoadm", "8EBDB935F8B30DA2", "d_ccinfo");
        if(!mongodb.isConnected()) {
            System.out.println("connection to mongo  fail, program halt.");
        }
        MongoCollection<Document> personBaseCol = mongodb.getCollection("d_ccinfo", "person_base");
        MongoCollection<Document> weixingongzhonghaoCol = mongodb.getCollection("d_ccinfo","weixingongzhonghao");
        long cntPersonBase = personBaseCol.count();
        result.add(new MongoStates(System.currentTimeMillis(),(int)cntPersonBase,"person_base","d_ccinfo","","",""));
        long cntWeixingongzhonghao = weixingongzhonghaoCol.count();
        result.add(new MongoStates(System.currentTimeMillis(),(int)cntWeixingongzhonghao,"weixingongzhonghao","d_ccinfo","","",""));
        mongodb.close();
        return result;
    }

    @Override
    public Comparator<MongoStates> getComparator() {
        //将数据写入到数据库中去

        BasicDataSource dataSource;
        return null;
    }
}
