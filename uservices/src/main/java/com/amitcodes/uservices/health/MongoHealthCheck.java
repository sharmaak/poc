package com.amitcodes.uservices.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;

// MongoHealthCheck which will check if MongoDB is connected or not.
// A health check is Dropwizard feature to do a runtime test to verify
// the service’s behaviour in production environment.

public class MongoHealthCheck extends HealthCheck
{
    private Mongo mongo;

    public MongoHealthCheck(Mongo mongo) {
        super();
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }

}
