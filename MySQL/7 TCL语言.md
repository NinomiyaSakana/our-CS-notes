# 7.TCL语言

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2018032811343154.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2018032811343154.png)

```sql
#tcl

/*
transaction control language 事务控制语言

事务：
一个或一组sql组成一个执行单元
这个单元要么全部执行，要么不执行。

事务的特性：ACID

事务的创建：
隐式事务：事务没有明显的开始和结束的标记
比如insert update delete等等
显式事务：事务具有明显的开始和结束的标记
前提：必须先设置自动提交功能为禁用

步骤1:开启事务
set autocommit=0;
start transaction;
步骤2:编写事务中的sql语句（select insert update delete）
仅限增删改查
语句1；
语句2；
...
步骤3:结束事务
commit; 提交事务
rollback; 回滚事务
具体笔记见下方

savepoint 节点名；设置保存点
*/

show engines;
#存储引擎 innodb支持事务，myisam memory不支持

#2、delete和truncate在事务使用时的区别
#delete支持回滚，truncate之后回滚也没了

```

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.00.27.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.00.27.png)

提交版本

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.12.08.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.12.08.png)

回滚版本

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.12.53.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.12.53.png)

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.15.24.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.15.24.png)

同时运行多个事务时（上）。  

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.23.07.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.23.07.png)

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.23.13.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.23.13.png)

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.39.29.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.39.29.png)

设置回滚点

![7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.41.21.png](7%20TCL%E8%AF%AD%E8%A8%80%203f93c6ac73cd46afa8630d454ef16c41/2021-04-17_17.41.21.png)