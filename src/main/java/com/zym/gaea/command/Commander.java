package com.zym.gaea.command;

import com.zym.gaea.model.Command;
import com.zym.gaea.model.Pom;
import com.zym.gaea.model.Project;
import com.zym.gaea.model.Tip;
import com.zym.gaea.service.ProjectService;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author master
 */
public class Commander {
    public static void main(String[] args) throws IOException {
        List<Tip> tipList = new ArrayList<Tip>() {
            {
                add(new Tip("dir", "输入项目所在路径:"));
                add(new Tip("groupId", "输入GroupId:"));
                add(new Tip("artifactId", "输入ArtifactId:"));
            }
        };
        Command command = new Command();
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            Iterator<Tip> iterator = tipList.iterator();
            while (iterator.hasNext()) {
                Tip tip = iterator.next();
                String key = tip.getKey();
                String message = tip.getMessage();
                System.out.print(message);
                String value = buff.readLine();
                Class<Command> commandClass = Command.class;
                Method method = commandClass.getMethod("set" + StringUtils.capitalize(key), String.class);
                method.invoke(command, value);
            }
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Project project = new Project(command.getDir(), command.getArtifactId());
        Pom pom = new Pom(command.getGroupId(), command.getArtifactId());
        ProjectService projectService = new ProjectService();
        String projectFileDir = projectService.createProject(project);
        projectService.createPomFile(projectFileDir, pom);
        projectService.createSrc(projectFileDir, pom);
    }
}
