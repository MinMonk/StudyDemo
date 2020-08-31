package com.monk.demo;

import java.util.List;
import java.util.concurrent.Callable;

public class CheckTask implements Callable<String> {

    private List<String> workData;

    public CheckTask(List<String> workData) {
        this.workData = workData;
    }

    @Override
    public String call() throws Exception {
        String currThreadName = Thread.currentThread().getName();
        System.out.println(currThreadName + "工作数据为" + workData);
        for (String str : workData) {
            //System.out.println(currThreadName + ": " + str);
        }
        return currThreadName + "执行完毕";

    }
}
