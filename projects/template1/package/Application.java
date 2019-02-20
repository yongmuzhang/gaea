package #{package};

import com.zym.gaea.config.SrcConfig;
import com.zym.gaea.constant.Constants;
import com.zym.gaea.enumeration.DirectoryTypeEnum;
import com.zym.gaea.exception.LogicException;
import com.zym.gaea.model.Directory;
import com.zym.gaea.model.Pom;
import com.zym.gaea.model.Project;
import com.zym.gaea.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author master
 */
public class Application {

    public String createProject(Project project) {
        String projectDir = project.getDir();
        File dir = new File(projectDir);
        if (!dir.exists()) {
            throw new LogicException("项目所在目录不存在");
        }
        if (!projectDir.endsWith(File.separator)) {
            projectDir = projectDir + File.separator;
        }
        String projectFileDir = projectDir + project.getName() + File.separator;
        File projectFile = new File(projectFileDir);
        if (!projectFile.exists()) {
            if (projectFile.mkdirs()) {
                return projectFileDir;
            } else {
                throw new LogicException("创建项目失败");
            }
        } else {
            throw new LogicException("所在项目已存在");
        }
    }

    public void createPomFile(String projectFileDir, Pom pom) {
        File sourcePomFile = new File(Utils.directoryJoin(System.getProperty("user.dir"), Constants.SOURCE_POM_PATH));
        File destPomFile = new File(Utils.directoryJoin(projectFileDir, "pom.xml"));
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(sourcePomFile));
            bufferedWriter = new BufferedWriter(new FileWriter(destPomFile));
            String lineStr;
            while((lineStr = bufferedReader.readLine()) != null) {
                lineStr = lineStr.replace("#{groupId}", pom.getGroupId()).replace("#{artifactId}", pom.getArtifactId());
                bufferedWriter.write(lineStr);
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            throw new LogicException("生成Pom文件失败");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String createSrc(String projectFileDir, Pom pom) throws IOException {
        SrcConfig srcConfig = new SrcConfig();
        Directory srcDirectory = srcConfig.getSrcDirectory();
        Queue<Directory> srcDirectoryQueue = new LinkedList<Directory>();
        String realName = Utils.directoryJoin(projectFileDir, srcDirectory.getName());
        srcDirectory.setName(realName);
        srcDirectoryQueue.offer(srcDirectory);
        Directory directory;
        while ((directory = srcDirectoryQueue.poll()) != null) {
            File srcFile = new File(directory.getName());
            if (!srcFile.exists()) {
                boolean isMkDirs = srcFile.mkdirs();
                if (!isMkDirs) {
                    throw new LogicException("SRC创建目录失败");
                }
            } else {
                throw new LogicException("SRC创建项目已存在");
            }

            if (directory.getType().equals(DirectoryTypeEnum.sources_root)) {
                File sourceFolder = new File(Utils.directoryJoin(System.getProperty("user.dir"), Constants.SOURCE_CODE_PATH));
                File destFolder = new File(Utils.directoryJoin(directory.getName(), pom.getGroupId()));
                if (!destFolder.exists()) {
                    destFolder.mkdir();
                }
                Utils.copyPackage(sourceFolder, destFolder, pom.getGroupId());
            }
            for (Directory d : directory.getDirectoryList()) {
                String name = d.getName();
                if (name != null) {
                    d.setName(Utils.directoryJoin(directory.getName(), name));
                }
                srcDirectoryQueue.offer(d);
            }
        }
        return null;
    }
}
