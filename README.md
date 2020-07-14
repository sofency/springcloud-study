##### spring cloud 学习
```$xslt
maven 使用 dependencyManagement元素提供了一种管理以来版本号的方式
通常会是一个组织或者项目的最顶层的父pom中看到dependencyManagement元素
使用pom.xml中的dependencyManagement元素能让所有在子项目中引用一个依赖而不显式的列出版本号
maven 会沿着父级层次向上走 知道找到一个用友dependencyManagement元素的项目,
然后就会使用这个dependencyManagement元素中执行的版本号
```
微服务架构

```$xslt
- 建module
- 改pom
- 写yml
- 主启动
- 业务类
```

##### 远程服务调用 使用RestTemplate用来跨域间的请求访问
```$xslt
- spring cloud 封装了netflix公司开发的Eureka模块来实现服务治理
- 在传统的rpc远程调用框架中,管理每个服务与服务之间的依赖关系比较复杂,管理比较复杂,
所以需要服务治理.管理服务与服务之间的依赖关系,可以实现服务调用,负载均衡,容错等,实现服务发现与注册.
```

##### 服务注册与发现
```
Eureka 采用了CS的设计架构,Eureka Server作为服务注册功能的服务器,它是服务注册中心,而系统中的其它微服务,
使用Eureka的客户端连接到Eureka Server并维持心跳连接,这样系统的维护人员就可以通过Eureka server 来
监控系统中各个微服务是否正常运行, 在服务注册与发现中,有一个注册中心,当服务器启动的时候,会把当前自己服务器的信息, 
比如服务地址,通信地址,等以别名方式注册到注册中心,,另一方(消费者|服务提供者),以该别名的形式去注册中心获取到实际的服务通信地址,
然后再实现本地RPC调用RPC远程调用框架设计思想:在于注册中心,
因为使用注册中心,管理每个服务与服务之间的依赖关系,在任何的 RPC远程调用框架中都会有一个注册中心.
```
##### Eureka 包含两个组件
```$xslt
Eureka server 提供服务注册的服务
各个微服务结点通过配置启动后,会在Eureka Server中的服务注册表中存储所有可用服务结点的信息,服务结点的信息可以在界面上直观的看到

EurekaClient 通过注册中心进行访问
是一个java客户端,用于简化EurekaServer的交互,可以端同时也具备一个内置的,使用轮询负载算法的负载均衡器,
在应用启动后,(默认30s)将会向Eureka Server中发送心跳,如果EurekaServer在多个心跳周期内没有收到某个结点的心跳,Eureka Server 会从服务注册表中将该服务结点移除(默认周期为90s)

```

