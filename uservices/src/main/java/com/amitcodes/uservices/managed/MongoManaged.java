package com.amitcodes.uservices.managed;

import com.mongodb.Mongo;
import io.dropwizard.lifecycle.Managed;

// MongoManaged which allows us to manage resources on application start and stop.
// This implements com.yammer.dropwizard.lifecycle.Managed. we close the MongoDB \
// connections in stop method.

public class MongoManaged implements Managed
{

    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }

}