# gaea
JAVA工程脚手架，快速生产项目，团队代码统一，节省多项目拷贝时间



- 使用范围
```
java工程
```
- 把仓库克隆到本地
```$xslt
git clone git@github.com:yongmuzhang/gaea.git
```
- 运行Commander，按照如下输入项目信息
```$xslt
输入项目所在路径:/Users/xxx  //项目所在本地路径
输入GroupId:com.zym.gaea //项目groupId
输入ArtifactId:gaea //项目名称 
```

- 运行完成之后，就会在目录下创建想要的工程

- 配置说明
````$xslt
把统一不变的配置文件或者java代码放到projects/template1/package下的所在文件内
注意：
    1、替换pom.xml
        <groupId>#{groupId}</groupId>
        <artifactId>#{artifactId}</artifactId>
    2、替换java源代码文件的package
        package #{package};
    请参照projects/template1/package下的文件
````
