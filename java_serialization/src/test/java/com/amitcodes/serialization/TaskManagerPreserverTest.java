package com.amitcodes.serialization;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManagerPreserverTest {

    private Logger logger = Logger.getLogger(TaskManagerPreserverTest.class.getCanonicalName());

    @Test
    public void testSerialize() {
        TaskManager taskManager = new TaskManager();

        Job job1 = new Job();
        job1.setCreationTime(new Date());
        Job job2 = new Job();
        job2.setCreationTime(new Date());

        taskManager.submitJob(job1);
        taskManager.submitJob(job2);

        TaskManagerPreserver preserver = new TaskManagerPreserver();
        File serializedFile = preserver.serialize(taskManager);

        Assert.assertTrue(serializedFile.exists());
        logger.log(Level.INFO, "serializedFile: {0}", serializedFile.getAbsolutePath());
    }

    @Test
    public void testDeSerialize() {
        TaskManagerPreserver preserver = new TaskManagerPreserver();

        File testSerializedInstance = new File("target/test-classes/TaskManager-1385628081424.ser");
        TaskManager tm = preserver.deSerialize(testSerializedInstance);

        Job job1 = tm.taskQueue.poll();
        Assert.assertEquals("0375adfb-410e-46fc-9e47-87b25c033e24", job1.getId());

        Job job2 = tm.taskQueue.poll();
        Assert.assertEquals("11249bd4-0b3a-4d73-b52c-9044e906c809", job2.getId());

        Assert.assertNotNull(tm.startupTimeInMillis);
    }
}
