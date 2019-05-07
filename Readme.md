# 生动智慧课堂

本科毕业设计，基于Spring实现。该项目向学生提供课前预习，课后学习情况反馈，教师管理教学资源，后台查看学生学习情况的数据以及图表，同时提供系统数据下载的功能。

## 项目环境

Spring 4.1.6

mybatis 3.2.4

mysql 6.0.3

## 安装步骤

1. 配置maven，详细可见<https://www.cnblogs.com/eagle6688/p/7838224.html>

2. 导入项目，详细可见<https://www.cnblogs.com/coder-zhang/p/3556223.html>

   若运行时，中文编码异常，解决方案见注意事项

3. 数据库搭建


​        （1）请修改路径src/main/resources/conf/jdbc.properties中的信息

- jdbc_username：数据库用户名。

- jdbc_password：数据库连接密码

- jdbc_url：jdbc:mysql://localhost:3306/<数据库名>?characterEncoding=utf-8

  （2）在mysql中导入active.sql文件

## 注意事项

在导入项目后，若网页显示编码异常，请使用记事本打开，选择另存为，编码格式设置为UTF-8