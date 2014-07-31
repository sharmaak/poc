package com.amitcodes.uservices;

import com.amitcodes.uservices.dao.JongoDocumentDao;
import com.amitcodes.uservices.health.MongoHealthCheck;
import com.amitcodes.uservices.managed.MongoManaged;
import com.amitcodes.uservices.resources.DocumentResource;
import com.amitcodes.uservices.transform.DocumentTransformer;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DocumentService extends Application<DocumentServiceConfig>
{

    public DocumentService()
    {
    }

    @Override
    public void initialize(Bootstrap<DocumentServiceConfig> documentServiceConfigBootstrap)
    {
    }

    @Override
    public void run(DocumentServiceConfig documentServiceConfig, Environment environment) throws Exception
    {
        ServerAddress serverAddress = new ServerAddress(documentServiceConfig.getMongoHost(),
                                                        documentServiceConfig.getMongoPort());
        MongoClient mongoClient = new MongoClient(serverAddress);

        JongoDocumentDao jongoDocumentDao = new JongoDocumentDao(
                                                    mongoClient,
                                                    documentServiceConfig.getMongodb(),
                                                    documentServiceConfig.getDocumentsCollectionName()
                                            );

        DocumentTransformer transformer = new DocumentTransformer();
        DocumentResource documentResource = new DocumentResource(jongoDocumentDao, transformer);

        //1. Add lifecycle manager for mongodb connections
        environment.lifecycle().manage(new MongoManaged(mongoClient));
        //2. Add health checks
        environment.healthChecks().register("mongodb", new MongoHealthCheck(mongoClient));
        //3. Register resources
        environment.jersey().register(documentResource);
    }

    public static void main(String[] args) throws Exception
    {
        new DocumentService().run(args);
    }
}
