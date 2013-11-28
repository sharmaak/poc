package com.amitcodes.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * TaskManager accepts tasks to be executed on an imaginary execution
 * engine. One of the requirements is that if the JVM is shutdown,
 * the task queue should not be lost.
 *
 * Disclaimer: This is a basic TaskManagement service designed to
 *             demonstrate Java object serialization. It is not
 *             fit enough to be used in any sort of production.
 */
public class TaskManager implements Serializable {

    // All persistent-capable classes are automatically given a unique identifier
    // (serialVersionUID). If the identifier of the class does not equal the
    // identifier of the flattened object, java.io.InvalidClassException will
    // be thrown.
    //
    // The serialVersionUID of a class file may change if fields are added/removed
    // or object class hierarchy is altered.
    private static final long serialVersionUID = -8523330402765273893L;

    /** The queue for jobs submitted to TaskManager */
    Queue<Job> taskQueue;

    /** Time at which TaskManager started. Used to calculate up-time */
    // Marked transient to ensure that this field is not serialized.
    // Since this field will not be initialized from a de-serialized object,
    // we need to take care of it in our code. Look at method readObject(...)
    transient Long startupTimeInMillis;

    public TaskManager() {
        taskQueue = new LinkedList<>();
        startupTimeInMillis = System.currentTimeMillis();
    }

    /**
     * Accepts job and adds it to execution queue
     *
     * @param job The job to be submitted for execution
     * @return Job id for the submitted job.
     */
    public String submitJob(Job job) {
        if (job == null) throw new NullPointerException("Job can not be null");
        generateAndPopulateJobId(job);
        taskQueue.add(job);
        return job.getId();
    }

    private void generateAndPopulateJobId(Job job) {
        String jobId = UUID.randomUUID().toString();
        job.setId(jobId);
    }

    public boolean removeJob(String jobId) {
        Job surrogateJob = new Job();
        surrogateJob.setId(jobId);
        return taskQueue.remove(surrogateJob);
    }

    /**
     * Returns the time at which task manager started up.
     * The time returned is milliseconds since epoch.
     */
    public Long getUpTime() {
        return startupTimeInMillis;
    }

    /****************************************************************************************
     * The following methods are used to customize serialization and de-serialization
     * operations. The methods are marked private to ensure that they can not be inherited,
     * extended OR invoked from another class.
     *
     * During serialization and de-serialization, JVM detects that the object's class contains
     * these methods. Once detected, JVM delegates execution control to these methods.
     *
     * In these methods, first we perform default ser/de-ser operations, followed by any
     * custom initializations needed. We may also do some cool stuff like encrypting during
     * serialization and decrypting during de-serialization.
     ****************************************************************************************/
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        startupTimeInMillis = System.currentTimeMillis();
    }
}
