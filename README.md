##### spring cloud 学习
```$xslt
maven 使用 dependencyManagement元素提供了一种管理以来版本号的方式
通常会是一个组织或者项目的最顶层的父pom中看到dependencyManagement元素
使用pom.xml中的dependencyManagement元素能让所有在子项目中引用一个依赖而不显式的列出版本号
maven 会沿着父级层次向上走 知道找到一个用友dependencyManagement元素的项目,
然后就会使用这个dependencyManagement元素中执行的版本号
```
微服务架构
- 建module
- 改pom
- 写yml
- 主启动
- 业务类
远程服务调用 使用RestTemplate用来跨域间的请求访问