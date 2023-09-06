package com.example.TaskManager.logging;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TaskMontoring {

    public String getPath() {
        return "C:\\Users\\USER\\TaskManager\\src\\main\\java\\com\\example\\TaskManager\\logging\\logs";
    }
    public static void monitor(String text){
//        This will log book all added tasks by title to the log file to track
        try(BufferedWriter write= new BufferedWriter(new FileWriter(new TaskMontoring().getPath(), true))){
            write.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
