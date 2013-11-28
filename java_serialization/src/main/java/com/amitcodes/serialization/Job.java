package com.amitcodes.serialization;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {

    private static final long serialVersionUID = 5103471929336297627L;

    private String id;
    private Date creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Job job = (Job) o;

        if (!id.equals(job.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
