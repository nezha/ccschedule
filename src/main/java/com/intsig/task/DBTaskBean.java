package com.intsig.task;

import com.intsig.Bean.MongoStates;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yi_zhang on 2016/7/25.
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

    }

    @Override
    public Comparator<MongoStates> getComparator() {
        return null;
    }
}
