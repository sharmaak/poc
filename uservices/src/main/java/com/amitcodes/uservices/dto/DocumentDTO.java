package com.amitcodes.uservices.dto;

import org.joda.time.DateTime;

public class DocumentDTO
{
    private String id;
    private String name;
    private String path;
    private String creationTime;

    public DocumentDTO()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(String creationTime)
    {
        this.creationTime = creationTime;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("DocumentDTO{");
        sb.append("creationTime='").append(creationTime).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
