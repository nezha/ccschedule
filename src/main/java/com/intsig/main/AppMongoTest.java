package com.intsig.main;


import com.intsig.Dao.MongoDBDao;
import com.intsig.Model.MongoPersonBaseModel;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



/**
 * Created by yi_zhang on 2016/7/26.
 */
public class AppMongoTest {

    @Test
    public void mongoSpringTest(){
        ApplicationContext ctx = new GenericXmlApplicationContext("db4mongo.xml");
        MongoOperations mongoOperations = (MongoTemplate)ctx.getBean("mongoTemplate");

        Query countQuery = new Query(Criteria.where("last_updated").gt(10000));
        long cnt = mongoOperations.count(countQuery,"person_base");

        MongoPersonBaseModel user = new MongoPersonBaseModel("mkyong", "password123");

        // save
        mongoOperations.save(user,"users");

        // now user object got the created id.
        System.out.println("1. user : " + user);

        // query to search user
        Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));

        // find the saved user again.
        MongoPersonBaseModel savedUser = mongoOperations.findOne(searchUserQuery, MongoPersonBaseModel.class);
        System.out.println("2. find - savedUser : " + savedUser);
    }

    @Test
    public void mongoDaoTest(){
        MongoDBDao mongodb = new MongoDBDao("192.168.8.28", 27017, "ccinfoadm", "8EBDB935F8B30DA2", "d_ccinfo");
        if(!mongodb.isConnected()) {
            System.out.println("connection to mongo  fail, program halt.");
        }
        MongoCollection<Document> personBaseCol = mongodb.getCollection("d_ccinfo", "person_base");
        long cnt = personBaseCol.count();
        System.out.print(cnt);
        mongodb.close();
    }
}
