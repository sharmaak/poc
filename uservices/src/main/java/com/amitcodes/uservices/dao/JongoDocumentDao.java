package com.amitcodes.uservices.dao;

import com.amitcodes.uservices.model.Document;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.Oid;
import org.jongo.marshall.jackson.JacksonMapper;

import java.util.LinkedList;
import java.util.List;

public class JongoDocumentDao
{
    private MongoCollection collection;

    public JongoDocumentDao(MongoClient mongoClient, String dbName, String collectionName)
    {
        DB db = mongoClient.getDB(dbName);
        // Create custom mapper and register JodaModule to handle
        // (un)marshalling of Joda DateTime classes.
        Mapper mapper = new JacksonMapper.Builder()
                .registerModule(new JodaModule())
                .build();
        Jongo jongo = new Jongo(db, mapper);
        collection = jongo.getCollection(collectionName);
    }

    //Create
    public void insert(Document document)
    {
        collection.insert(document);
    }

    //Read
    public Document get(String documentId)
    {
        return collection.findOne(Oid.withOid(documentId)).as(Document.class);
    }

    //Update
    public void update(Document document)
    {
        //collection.save(document);
        collection.update(Oid.withOid(document.getId())).with(document);
    }

    //Delete
    public void delete(String... documentIds)
    {
        if (documentIds == null || documentIds.length == 0) {
            return;
        }

        // bulk delete is better, but doing iterative delete as this is a prototype
        for (String documentId : documentIds) {
            collection.remove(Oid.withOid(documentId));
        }
    }

    //Find
    public List<Document> find(String criteria, int limit)
    {
        if (criteria == null) {
            throw new IllegalArgumentException("criteria can not be null");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("limit can not be less than 1");
        }

        MongoCursor<Document> cursor = collection.find(criteria).limit(limit).as(Document.class);

        List<Document> result = new LinkedList<>();
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }

        return result;
    }


}
