package com.intsig.Dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;



/**
 * Created by yi_zhang on 2016/7/26.
 */
public interface BaseMongoDAO<T> {
    /**
     * ͨ��������ѯʵ��(����)
     *
     * @param query
     */
    public List<T> find(Query query) ;

    /**
     * ͨ��һ����������ѯһ��ʵ��
     *
     * @param query
     * @return
     */
    public T findOne(Query query) ;

    /**
     * ͨ��������ѯ��������
     *
     * @param query
     * @param update
     * @return
     */
    public void update(Query query, Update update) ;

    /**
     * ����һ������mongodb
     *
     * @param entity
     * @return
     */
    public T save(T entity) ;

    /**
     * ͨ��ID��ȡ��¼
     *
     * @param id
     * @return
     */
    public T findById(String id) ;

    /**
     * ͨ��ID��ȡ��¼,����ָ���˼�����(�����˼)
     *
     * @param id
     * @param collectionName
     *            ������
     * @return
     */
    public T findById(String id, String collectionName) ;


    /**
     * �������ܺ�
     * @param query
     * @return
     */
    public long count(Query query);
}
