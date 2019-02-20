package com.zym.gaea.config;

import com.alibaba.fastjson.JSONObject;
import com.zym.gaea.model.Directory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 */
public class SrcConfig {
    private final String srcDirectoryJson = "{\n" +
            "\t\"name\":\"src\",\"type\":\"normal\",\n" +
            "\t\"directoryList\":[\n" +
            "\t\t{\n" +
            "\t\t\t\"name\":\"main\",\"type\":\"normal\",\n" +
            "\t\t\t\"directoryList\":[\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"name\":\"java\",\"type\":\"sources_root\",\n" +
            "\t\t\t\t\t\"directoryList\":[]\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"name\":\"resources\",\"type\":\"resources_root\",\n" +
            "\t\t\t\t\t\"directoryList\":[]\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t\t\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\":\"test\",\"type\":\"normal\",\n" +
            "\t\t\t\"directoryList\":[\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"name\":\"java\",\"type\":\"test_sources_root\",\n" +
            "\t\t\t\t\t\"directoryList\":[]\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t]\n" +
            "\n" +
            "}";
    public Directory getSrcDirectory() {
        Directory srcDirectory = JSONObject.parseObject(srcDirectoryJson, Directory.class);
        return srcDirectory;
    }
}
