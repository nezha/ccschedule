package com.intsig.Dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.intsig.Util.ReflectionUtils;

import java.util.List;

/**
 * Created by yi_zhang on 2016/7/26.
 */
public abstract class BaseMongoDAOImpl<T> implements BaseMongoDAO<T> {
    protected MongoTemplate mongoTemplate;

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public void update(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, this.getEntityClass());
    }
    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass(){
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 注入mongodbTemplate
     *
     * @param mongoTemplate
     */
    protected void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
}
