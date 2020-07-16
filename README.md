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

##### eureka自我保护

```$xslt
导致自我保护的原因: 某一时刻一个微服务不能用了,eureka不会立即清理,依旧会对该微服务的信息进行保存
设计哲学, 宁可保留错误的服务注册信息,也不盲目注销任何可能健康的服务实例.

注册中心如何禁用自我保护模式
eureka.server.enable-self-preservation:false
eureka.server.enable-interval-time-in-ms:2000

服务提供方向注册中心发送心跳的频率设置
客户端向服务端发送心跳的时间间隔
eureka.instance.lease-renewal-interval-in-seconds=30
# eureka服务端在收到最后一次心跳后等待事件上限 单位时间为秒  超时剔除服务
eureka.instance.lease-expiration-duration-in-seconds=90
```

##### consul服务注冊中心筆記
```$xslt
从官网下载consul
下载后只有一个consul.exe文件,此时双击后 在同级目录下打开cmd  运行consul --version  如果显示版本说明安装成功
运行consul   consul agent -dev  
然后在浏览器输入 http://localhost:8500
```


#####技术积累点
```$xslt
在springboot项目中如果没有配置数据库的相关操作,需要在springbootApplication的注解中添加exclude= {DataSourceAutoConfiguration.class}
即@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

CAP
C: consistency 强一致性  
A: availabiltily 可用性   
P  Partition tolerance  分区容错性

```

##### Ribbon负载均衡
```$xslt
负载均衡  简单的来说就是将用户的请求平摊的分配到多个服务上,从而达到系统的HA
常见的负载均衡有软件 Nginx LVS

Ribbon本地负载均衡  VS Nginx 服务端负载均衡区别
Nginx 服务端负载均衡 客户端所有的请求都会交给nginx ,然后nginx实现转发请求, 即负载均衡是由服务端实现的
Ribbon本地负载均衡,在调用微服务接口时候,会在注册中心上获取注册信息服务列表之后缓存到JVM本地,从而实现本地RPC远程服务调用技术

默认的负载均衡使用就是 RestTemplate来进行调用服务的
```

##### openFeign的工作原理
```$xslt
首先在消费方定义调用服务方的接口,并且在接口上加上@FeignClient(value="注册中心")
接口的定义就是服务方对外暴露的接口
注意Feign 依赖于eureka的注册中心 所以要导入依赖
<!--将服务提供者注册到eureka服务中心-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
然后在消费方定义消费的接口  并将服务方的接口注入到消费方的controller中

并且在启动类上加上@EnableFeignClients注解


openfeign超时控制
注意对ribbon进行控制  openfeign底层使用的就是ribbon  即负载均衡加上restemplate

openfiengn 默认只等待一秒钟 超过一秒钟就报错
```


