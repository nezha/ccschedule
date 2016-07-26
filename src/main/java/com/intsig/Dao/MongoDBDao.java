package com.intsig.Dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shangru_yu on 2016/3/30.
 */
public class MongoDBDao {

    private MongoClient mongoClient = null;
    private String mongoDBHost;
    private int mongoDBPort;

    /* mongodb client connected flag */
    private boolean mongodb_connected = false;

    public MongoDBDao(String host, int port){
        mongoDBHost = host;
        mongoDBPort = port;
        if(null == mongoClient){
            try{
                mongoClient = new MongoClient(mongoDBHost, mongoDBPort);
                mongodb_connected = true;
            } catch (MongoException e){
                e.printStackTrace();
            }
        }
    }

    public MongoDBDao(String host, int port, String username, String password, String source){
        mongoDBHost = host;
        mongoDBPort = port;
        ServerAddress serverAddress = new ServerAddress(mongoDBHost, mongoDBPort);
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        seeds.add(serverAddress);
        MongoCredential credentials = MongoCredential.createScramSha1Credential(username, source, password.toCharArray());
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credentials);
        try {
            mongoClient = new MongoClient(seeds, credentialsList);
            mongodb_connected = true;
        } catch (MongoException e){
            e.printStackTrace();
        }
    }

    public boolean isConnected(){
        return this.mongodb_connected;
    }

    /**
     * �?查是否为空字符串或null
     */
    private boolean isInvalidName(String s){
        if(null == s || s.equals("")){
            return true;
        }
        return false;
    }

    /**
     * 关闭mongodb
     */
    public void close(){
        if(null != mongoClient) {
            mongoClient.close();
        }
    }

    /**
     * 获取db实例
     * @param dbName
     * @return
     */
    public MongoDatabase getDatabase(String dbName){
        if(isInvalidName(dbName)){
            return null;
        }

        MongoDatabase _database = mongoClient.getDatabase(dbName);
        return _database;
    }

    /**
     * 获取�?有数据库名称列表
     * @return
     */
    public MongoIterable<String> getDatabaseNames(){
        MongoIterable<String> _dbNames = mongoClient.listDatabaseNames();
        return _dbNames;
    }

    /**
     * 获取collection对象
     * @param dbName
     * @param collectionName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collectionName){

        if(isInvalidName(dbName) || isInvalidName(collectionName)){
            return null;
        }

        MongoCollection<Document> _collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        return _collection;
    }

    /**
     * 获取指定db下所有collection�?
     * @param dbName
     * @return
     */
    public List<String> getCollections(String dbName) {
        if(isInvalidName(dbName)){
            return null;
        }

        MongoIterable<String> colls = mongoClient.getDatabase(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 向指定表中插入一条数据，通过Document对象
     * @param collection
     * @param doc
     * @return
     */
    public boolean insert(MongoCollection<Document> collection, Document doc){
        if(doc.isEmpty()){
            return false;
        }

        collection.insertOne(doc);
        return true;
    }

    /**
     * 向指定表中插入多条数据，通过List<Document>
     * @param collection
     * @param docs
     * @return
     */
    public boolean insert(MongoCollection<Document> collection, List<Document> docs){
        if(0 == docs.size()){
            return false;
        }

        collection.insertMany(docs);
        return true;
    }

    /**
     * 修改指定表中的一条数�?
     * @param collection
     * @param condition
     * @param doc
     *   修改时的参数�?
        $inc 对指定的元素�?
        $mul �?
        $rename 修改元素名称
        $setOnInsert 如果以前没有这个元素则增加这个元素，否则不作任何更改
        $set 修改制定元素的�??
        $unset 移除特定的元�?
        $min 如果原始数据更大则不修改，否则修改为指定的�??
        $max �?$min相反
        $currentDate 修改为目前的时间
     * @return
     */
    public int update(MongoCollection<Document> collection, Bson condition, Document doc){
        int _updateCount = 0;
        if(doc.isEmpty()){
            return _updateCount;
        }

        UpdateResult _rsult = collection.updateOne(condition, doc);
        _updateCount = (int)_rsult.getModifiedCount();
        return _updateCount;
    }

    public int replace(MongoCollection<Document> collection, Bson condition, Document doc){
        int _updateCount = 0;
        if(doc.isEmpty()){
            return _updateCount;
        }

        UpdateResult _rsult = collection.replaceOne(condition, doc);
        _updateCount = (int)_rsult.getModifiedCount();
        return _updateCount;
    }
}
