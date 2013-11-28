package com.amitcodes.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManagerPreserver {

    private Logger logger = Logger.getLogger(TaskManagerPreserver.class.getCanonicalName());

    public File serialize(TaskManager taskManager) {

        String serializedFileName = "TaskManager-" + System.currentTimeMillis() + ".ser";
        File serializedTaskManager = new File(System.getProperty("java.io.tmpdir"), serializedFileName);

        try(FileOutputStream fos = new FileOutputStream(serializedTaskManager);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(taskManager);
        } catch (IOException e) {
            throw new PersistenceException("Failed to serialize TaskManager", e);
        }

        return serializedTaskManager;
    }

    public TaskManager deSerialize(File serializedTaskManager) {

        TaskManager taskManager = null;
        try(FileInputStream fis = new FileInputStream(serializedTaskManager);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            taskManager = (TaskManager)ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException("Failed to de-serialize TaskManager", e);
        } finally {
            try {
                Files.delete(serializedTaskManager.toPath());
            } catch (IOException e) {
                // Ignore. This is not a show stopper.
                logger.log(Level.INFO, "Failed to delete serialized file: " +
                        serializedTaskManager.getAbsolutePath(), e);
            }
        }
        return taskManager;
    }
}
