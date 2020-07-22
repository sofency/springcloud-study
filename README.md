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
```$xslt
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

##### Hystrix 微服务熔断器
````$xslt
分布式面临的问题  : 防止出现一个服务的失误导致其它的服务出现服务雪崩

熔断器是什么 : 
Hystrix是一个用于处理分布式系统的延迟和容错的开源库,在分布式系统里,许多依赖不可避免的会调用失败,比如超时,异常等,Hystrix能够保证在一个依赖出现问题的情况下,不会导致整体服务失败,避免级联故障

熔断器 本身就是一个开关装置,当某个服务单元发生故障之后,通过断路器的故障监控,向调用方返回一个符合预期的可处理的备选响应,而不是长时间的等待或者抛出调用方法无法处理的异常.这样就保证了服务调用方的线程不会被长时间的 不必要的占用
服务降级:  fallback
    服务器忙 请稍后再试  ,不让客户端等待并立刻返回一个友好提示
    哪些情况会出现服务降级   1.程序运行异常,2.超时 3.服务熔断触发服务降级 4.线程池/信号量打满也会导致服务降级

服务降级的使用 
    服务方服务降级的设置
    1. 首先在启动类上加上@EnableCircuitBreaker  注解
    2. 再在服务提供方的方法上添加
       @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    
        })
       其中paymentInfoTimeoutHandler是服务发生超时,异常会触发的方法作为兜底的解决方案,
     服务降级不仅可以适用于服务提供方,而且也可以适用于消费方

    消费方服务降级的设置
    1. 首先在yml中设置feign.hystrix.enable:true
    2. 在controller中定义服务降级调用的方法 并配上上述的注解 注意 服务降级的方法参数要保持一致
    3. 在启动类上加上@EnableHystrix
服务熔断: break
    类似于保险丝 达到最大服务访问后 直接拒绝访问,拉闸断电,然后采用服务降级的方法并返回友好提示    服务降级 -> 进行熔断 - 恢复调用链路
    
    涉及到断路器的三个重要参数   快照时间窗 请求总数阈值  错误百分比阈值

    1.快照时间窗 断路器确定是否打开需要统计一些请求和错误数据,而统计的时间范围就是快照时间窗,默认为最近的10秒
    2.请求总数阈值 在快照时间窗内,必须满足请求总数阈值才有资格熔断,默认是20 意味着在10秒内如果该hystrix命令调用次数不足20次,即使所有的请求都超时或其它的原因失败,断路器都不会打开
    3.错误百分比阈值:当请求总数在快照时间窗内超过了阈值,比如发生了30次调用,如果在着30次调用中,有15次发生了超时异常,也就是超过50%的错误百分比,在默认设定50%的阈值情况下 ,这是断路器会打开
    4.断路器打开之后 再有请求调用的时候 将不会调用主逻辑,而是直接调用降级fallback 实现自动的发现错误并将降级逻辑切换为主逻辑,减少响应延迟的效果
    5.原来的主逻辑如何恢复   当断路器打开 对主逻辑进行熔断之后,hystrix会启动一个休眠时间窗,在这个事件窗内,降级逻辑是临时的成为主逻辑,
    当休眠的事件窗到期,断路器将进入半开状态,释放一次请求到原来的主逻辑上,如果此次请求正常返回那么断路器将继续闭合,主逻辑恢复,如果此次请求依然有问题,断路器继续进入打开状态,休眠时间窗重新计时

服务限流: 
    秒杀高并发操作,严禁一窝蜂的过来拥挤,大家排队,一秒N个,有序进行
    

熔断器能干什么


````



