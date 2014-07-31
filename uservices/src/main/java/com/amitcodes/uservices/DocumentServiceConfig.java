package com.amitcodes.uservices;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
//import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class DocumentServiceConfig extends Configuration
{

    @JsonProperty
    @NotEmpty
    public String mongoHost;

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoPort;

    @JsonProperty
    @NotEmpty
    public String mongodb;

    @JsonProperty
    @NotEmpty
    public String documentsCollectionName;

    @JsonProperty
    @NotEmpty
    public String docStoreRoot;

    public String getMongoHost()
    {
        return mongoHost;
    }

    public void setMongoHost(String mongoHost)
    {
        this.mongoHost = mongoHost;
    }

    public int getMongoPort()
    {
        return mongoPort;
    }

    public void setMongoPort(int mongoPort)
    {
        this.mongoPort = mongoPort;
    }

    public String getMongodb()
    {
        return mongodb;
    }

    public void setMongodb(String mongodb)
    {
        this.mongodb = mongodb;
    }

    public String getDocStoreRoot()
    {
        return docStoreRoot;
    }

    public void setDocStoreRoot(String docStoreRoot)
    {
        this.docStoreRoot = docStoreRoot;
    }

    public String getDocumentsCollectionName()
    {
        return documentsCollectionName;
    }

    public void setDocumentsCollectionName(String documentsCollectionName)
    {
        this.documentsCollectionName = documentsCollectionName;
    }
}
