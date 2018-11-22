
第一家:
	spring怎么配置多个数据源(已解决)
	aop原理(已解决)
	mybatis怎么实现批处理(已解决)
	dubbo和zookeeper的工作原理
	aop代码级别怎么配置
	事物能修饰private方法么，为什么(已解决)
	泛型干什么的
	vatoer和list的区别(已解决)
	序列化的作用
	dubbo怎么避免consumer直连的
	线程池，线程池队列满了怎么办
	rpc和http的区别
	springboot有几种配置  各个配置的区别 
	还有mq的ack怎么保证数据不丢失的 
	还有高并发的处理，zookeeper怎么进行选主的，上限分支怎么搞的
	阻塞队列有哪些
	redis穿透怎么回事，怎么解决redis穿透！(已解决)
第二家：
	数据库的主从怎么配置
	怎么减少数据库主从复制的时间
	索引用没用，怎么避免失效
	springboot怎么配置多个数据源
	高并发问题怎么处理的
	分布式事物怎么处理的
	mq用过么，fq和mq的区别
	多线程用过么，多线程在项目里面怎么用的
	gc垃圾回收算法，jvm什么时对象从年轻带到老年代
	线程的应用场景





	1.https类型的url，在通过httpclient访问的时候 需要证书验证

		解决方案：第一种就是下载该网站的证书，第二种方案则是绕过证书验证

	2.缓存击穿/缓存穿透/缓存雪崩

		2.1缓存穿透：当查询一个数据时，缓存中没有，就去数据库中查询，数据库也没有故而不写入缓存，如此反复操作，
				当流量很大时,db容易挂掉。该现象称为缓存穿透。
			|--解决方案：
				|--1.当数据库没有数据的时候，仍然写入缓存，写入空数据，设定缓存时间为5分钟内，降低数据库压力
				|--2.布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被 这个bitmap拦截掉，从而避免了对底层存储系统的查询压力
		
		2.2缓存雪崩：指缓存的过期时间设置相同，在某个时刻突然缓存为空，大量流量突然并发访问db,db瞬时雪崩。
			|--解决方案：
				|--1.大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线程（进程）写，从而避免失效时大量的并发请求落到底层存储系统上
				|--2.在原有过期时间上加上随机值(仍有可能发生雪崩)

		2.3缓存击穿：与缓存雪崩类似，不同的是前者是很多Key,后者是针对热点Key,若该数据是热点数据，访问量很大，当该缓存失效时，发生缓存穿透，访问数据库，
		若此时请求量很大，依然会对db造成致命影响。
			|--解决方案：
					|--1.思路：当key失效时，大量线程访问数据，仅让一个线程load db，然后重新写入缓存,其他线程读取已写入的缓存
						使用互斥锁(mutex key)模拟同步的效果，redis.setnx(key_mutex,value,time) 该方法返回0或1 相当于同步方法同一时间仅有一个线程访问
															 memcache使用 add(key_mutex,time)==true实现，条件判断里load db 条件外 线程稍微睡眠，然后重新从缓存获取数据
					|--2.永不过期：redis 不设置过期时间的话 key就不会过期。
						 逻辑过期：key值不会过期，把过期时间放进缓存。
						 也可以设置永不过期，然后写定时任务，定时更新缓存。

		针对业务系统，永远都是具体情况具体分析，没有最好，只有最合适

	3.vector 和 list 的区别
		|--1.vector 线程安全，List非同步，list性能更优
		|--2.元素超容，list增加50%容量，vector翻倍，list更加节约内存空间

	4.LinkeList可以首尾添加、 首尾获取数据

	5.solr facet的用法
		 query.setFacet(true);
		 query.setFacetLimit(100);
		 query.addFacetField("idShopDefault","idBrand","idCategoryDefault");
		 将模糊查询的结果按照 上述三个key进行分组，并获得对应分组中的 分组名称：该分组名称下的结果数量
		 比如按照  品牌分组：得到的处理过的结果就是 {品牌：{[华为:128],[小米:23],...}

	6.solr4.3版本 高亮结果时，查询条件若带有 AND filed:*  则所有结果都会高亮

	7.幂等性：对于相同的参数重复的执行，结果都是相同的
		幂等性是系统的接口对外一种承诺(而不是实现), 承诺只要调用接口成功, 外部多次调用对系统的影响是一致的. 
		声明为幂等的接口会认为外部调用失败是常态, 并且失败之后必然会有重试

	8.dubbo学习

		|-- dubbo基于spring的Schema扩展进行加载，若不用spring可以通过API方式调用，不过不推荐
		|-- 标签支持自定义参数，用于不同扩展点实现的特殊配置
		|-- 配置的查找顺序:方法级别优先，接口级别次之，全局配置再次之。消费者配置优先与提供者
		
		|-- dubbo 自动加载classpath下面的dubbo.properties文件，可以通过jvm启动参数：-Ddubbo.properties.file=xxx.properties更改缺省配置
		|-- properties 配置文件中 dubbo.registry.address=10.20.153.10:9090 等价于<dubbo:registry address="10.20.153.10:9090" /> 公用配置可以通过properties文件配置
		|-- 配置加载优先级：jvm参数>dubbo.xml>dubbo.properties
		
		|-- dubbo启动默认检查依赖是否可用，不可用抛异常，阻止spring初始化，以便上线更早发现问题,默认check=true
		|-- reference引用的接口默认是延迟初始化的，只有被使用的时候才初始化，init=true 可以立即初始化
		|-- 集群调用失败时，dubbo默认容错方案是 failover重试  重试次数配置：retries=2 仅failover模式生效 集群模式配置：cluster='failfast'

		|-- 负载均衡:默认为random随机调用 loadbanlance="roundrobin"  负载均衡算法：随机，轮询，活跃数，一致性hash
		

		provider:
			|-- application：name属性,提供方的服务名称定义，用于计算依赖关系
			|-- registry:address属性 指定暴露服务的中介 可以是zookeeper可以是multicast
			|-- protocol:name,port  指定暴露服务的协议和端口，提供方指定，消费方被动接受
			|-- service: interface,ref  指定服务暴露提供者 ，一个服务可以注册多个注册中心也可以使用多个协议暴露
		consumer:
			|-- application：name 定义消费者的名称
			|-- registry:address 发现服务的中介
			|-- reference:id,interface,check,timeout，一个引用可以指向多个注册中心
			
		调用关系：
			|-- 注册中心基于[长连接]将provider地址列表 推送给 消费者
			|-- 消费者 默认软负载均衡算法，选择provider,若失败则选另一个Provider
			|-- prodiver and consumer ，定时每分钟发送调用数据到监控中心

			连通性：
				|-- register,provider,consumer之间均为长连接，monitor除外
				|-- provider宕机，register立即通知consumer
				|-- provider和consumer可以直连，monitor和register是可选的
			
			健壮性
				|-- register全部宕机，无任何影响，consumer本地缓存了prodider列表
				|-- register增加集群，客户端可自动发现新的register
				|-- provider全部宕机，consumer无法使用，无限重连
				|-- provider无状态，可建立集群，register推送给consumer
		
		点对点直连：只测试指定服务，以服务接口为单位，忽略注册中心的提供者列表
			|-- consumer 无需再发现服务，直接在接口中指定url路径 
			    <dubbo:reference id="demoService" check="false" interface="com.alibaba.dubbo.demo.DemoService" url="dubbo://localhost:20880"/>
		
		只订阅：
			|-- 问题描述：当公用一个注册中心时，正在开发的服务若注册到该注册中心，则会影响consumer的消费，产生负载均衡
			|-- 解决方案：只订阅服务，禁用注册服务功能   register=false / address="...2181?register=false"
		
		只注册：
			|-- 需求：一个provider需要同时注册到两个register，且该provider仅能消费其中一个register服务
			|-- 方案：禁用订阅功能  subscribe=false

		静态服务：
			|-- 通过人工的方式管理服务的上线和下线，此时需要将注册中心标识为非动态管理模式
			|-- dynamic=false 该模式下，monitor中的服务不会随着服务的关闭消失，一直存在于服务列表，除非你手动删除，而动态的服务会自动消失
				但是服务关闭的情况下，消费者无法连接到服务的
		多协议：
			|-- 不同服务适应于不同的协议，大数据适合短连接协议，小数据大并发用长连接协议
		
		多register :使用id 区分
		
		服务分组：
			|-- 当一个服务有多个实现时，可以采用分组的方法
			|-- 发布服务时 group='service1',group="service2",ref引用不同的实现类，然后消费者在消费的时候也带上group分组的id ,可以指定实现类进行消费
		
		多版本：
			|-- 当服务升级的时候，可能存在不兼容的情况，通过添加服务的版本号，在调用的时候可以加以区别

		分组聚合：
			|-- 当接口一样，但是实现类有多个，比如菜单，用group加以区分，需要将每个分组的结果进行合并，
				这样就能实现分组的聚合效果，将所有菜单的分组聚合到一个结果中
			|-- reference group="*/a,b,c" merger=true  还有一些方法的合并策略，返回值最好用 map set list ，其他不支持
		
		结果缓存：
			|-- 用于加速热门数据的访问速度 lru 基于最近最少使用原则删除多余缓存，保持最热的数据被缓存
			|-- reference cache="lru" 每个方法缺省保存1000条最热数据，不同方法的缓存不相互影响，先进先出原则
			|-- 设置缓存的大小：<dubbo:parameter key="cache.size" value="100"/>

		回声测试：
			|-- 用于检测服务是否可用，可用于监控。所有服务自动实现EchoService接口，直接强转调用$echo("ok")方法测试结果

		上下文信息：
			|-- 保存的是当前调用过程中所需的环境信息
			|-- RpcContext.getContext(); 
				|-- isProviderSide：是否为Provider
				|-- getRemoteHost:获取调用方地址
				|-- getUrl().getParameter("application"):获取当前服务的配置信息

		异步调用：
			|-- 基于NIO的异步非阻塞原理，相对于多线程开销较小
			|-- 设置方法级别的异步策略：async="true"，若不关心返回值，可以设置return="false",减少future创建和管理的成本
				异步使用方法：
					|-- 1.先和往常一样使用服务调用方法，此时返回值为null
					|-- 2.使用上下文获取future，获取完毕后，之前调用的方法结果会被异步放回future
						然后使用future的get阻塞方法获取结果。
		
		参数回调：
			|-- 在服务端provider运行consumer的实现，即提供者可以运行消费者的代码。服务端可以调用客户端逻辑
				服务端只提供接口，消费者提供实现，消费者调用服务者，达到使用提供者运行消费者实现的目的
			<dubbo:service  interface="com.alibaba.dubbo.demo.DemoService" ref="demoService" connections="1" callbacks="1000">
				<dubbo:method name="addListener">//设置方法是否可以回调 并设置回调的接口 被provider调用的接口 consumer实现的接口
					<dubbo:argument callback="true" type="com.alibaba.dubbo.demo.CallbackListener"/>
				</dubbo:method>
			</dubbo:service>
		
		延迟暴露：
			|-- 如果服务需要等待点时间才能完全就位，或者应当在服务完全初始化之后再被消费，应该延迟暴露
			|-- service deplay="500"  
			|-- <dubbo:provider deplay="-1"/> 可以设置spring初始化加载完毕后再暴露服务 

		并发控制：
			|-- 限制服务和消费调用服务的并发数量
			|-- 服务端：<dubbo:service exeutes="10"> 
			|-- 客户端：<dubbo:reference actives="10"> 或者 在服务端限制客户端的并发数量：<dubbo:service actives="10"> 

		连接控制：限制服务器端的连接数量 <dubbo:provider accepts="10"/>
		延迟连接: 当有调用发起时，再创建长连接 只对长连接的dubbo协议生效  <dubbo:protocol lazy="true"/>
		粘滞连接：自动开启延迟连接。尽可能让客户端总是调用同一提供者的服务，除非该provider挂了再连接另一台   <dubbo:protocol sticky="true"/>
		
		令牌验证：
			|-- 1.防止消费者绕过注册中心直接访问提供者 2.在注册中心控制调用权限 3.注册中心可以灵活改变授权方式
			|-- 经过测试，设置token验证时，如果consumer经过register，可以正常访问，如果是直连方式，只有设置和服务端一样的token值时，
				客户端才能正常访问provider。也就是说，令牌验证可防止consumer直连。
		
		路由规则：
			|-- 可通过ExtensionLoader扩展加载器使用工厂模式获取Registry注册类，写入对应配置，对指定服务进行设置路由规则
			|-- 支持 条件路由和脚本路由
		配置规则
			|-- 可以向注册中心动态的覆盖原先的服务配置
			|-- 和路由规则相似，规则不同而已，路由的开头是：condition/script，配置规则是：override
		服务降级
			|-- 可以使消费者对指定服务不发起远程调用，直接返回null值 mock=force:return+null
			|-- 也可以在消费方对指定服务的方法调用失败后，返回null而不抛异常 mock=fail:return+null
			|-- 实例：override://0.0.0.0/com.foo.BarService?category=configurators&dynamic=false&application=foo&mock=force:return+null
			
		9.基于spring schema的支持，可以设计xml文件 初始化bean
			|-- 1 设计Javabean
			|-- 2 设计xsd文件
			|-- 3 继承NamespaceHandlerSupport 和 BeandefinitionParser 实现xml的解析
			|-- 4 编写spring.handlers -指明解析的命名空间处理器 和spring.schemas -指明依据的xsd文件 配置文件
		
		10.泛型 通配符
			|-- 泛型作用：参数化类型，即类的类型作为一种参数，T,K,V,E等字母都可以代表任意的bean类型，以增强扩展性并提高代码的可读性
			|-- 通配符：制定泛型中的类型范围，<? extend/super T>，指定 上下限，但是局限性是：删减了具体元素的添加删除功能，只提供和元素无关的操作
			|-- 泛型擦除：若没有指定泛型的上下限，泛型擦除之后都是Object类型，编译之后没有泛型的存在。编译阶段泛型的局限性可以通过反射动态修改泛型的类型
			|-- Java不能创建具体类型的泛型数组，因为泛型擦除后基本都是Object类型，不能判断数组中的类型具体是什么类型




面试总结：
	1.简历写啥问啥
	2.多线程必问
		|-- 线程池怎么使用，用过没，怎么用的
		|-- 线程池满了如何处理
		|-- 搞明白ThreadPoolExcutor的几个参数就差不多了
	3.缓存
		|-- redis 数据类型 有什么区别特点 你用过哪些
		|-- 项目缓存了哪些数据  缓存数据需要更新的时候怎么操作 
		|-- 缓存击穿是什么 如何避免
	4.
		


linux 常用命令

	netstat -lnp|grep 8080  查看进程
	
	|-- grep 筛选命令
		|-- grep 'INFO' demo.log     #在文件demo.log中查找所有包行INFO的行
		|-- grep -c 'ERROR' demo.log   #输出文件demo.log中查找所有包行ERROR的行的数量
		|-- grep -v 'ERROR' demo.log   #查找不含"ERROR"的行
		|-- grep -o 'order-fix.curr_id:\([0-9]\+\)' demo.log    #-o选项只提取order-fix.curr_id:xxx的内容（而不是一整行），并输出到屏幕上
	
	|-- sed 命令

		可以使用一下命令查使用内存最多的10个进程     
		ps -aux | sort -k4nr | head -n 10
		可以使用一下命令查使用CPU最多的10个进程     
		ps -aux | sort -k3nr | head -n 10

		查看Linux自启服务
			systemctl list-unit-files --type=service | grep enabled

		
Docker
	启动docker
		|--sudo systemctl start docker
	docker客户端使用
		|--docker pull training/webapp 载入某镜像
		|--docker run -d -p 8080:5000 training/webapp python app.py 指定映射端口执行容器 8080:主机器端口 5000:容器端口
	删除所有退出的容器
	    |--docker rm $(docker ps -q -f status=exited)
		|-- docker ps -q -f status=exited 查询所有状态是exited的容器的id

	   docker run -d --name master -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mysql 
		   -v /usr/local/src/mysql/master-data:/var/lib/mysql 
		   -v /usr/local/src/mysql/mater.cnf:/etc/mysql/mysql.conf.d/mysql.cnf 
		   mysql:5.6




Shiro
	
	三大概念：Subject  SecurityManager Realm 
		|-- Subject :当前用户
		|-- SecurityManager ：管理所有用户 核心
		|-- Realm ：连接DB 获取认证和授权信息 ，包含两个概念：Authentication，Authorization
			|-- Authentication : 认证  用于验证登陆账号和密码是否匹配
			|-- Authorization : 授权 验证该用户是否拥有某个操作的权限


				1.ShiroFilterFactoryBean：设置过滤规则 要set  SecurityManager
				2.SecurityManager要set 自定义Realm  使用默认的 DefaultWebSecurityManager
				3.自定义Realm 实现AuthorizingRealm 并实现认证和授权方法即可
				4.DelegatingFilterProxy 用于过滤url


RocketMq
	 cd /usr/local/src/rocketmq-all-4.3.0/distribution/target/apache-rocketmq/
	1.启动nameserver
			nohup sh bin/mqnamesrv & tail -f ~/logs/rocketmqlogs/namesrv.log
	2.启动broker
			nohup sh bin/mqbroker -n localhost:9876 & tail -f ~/logs/rocketmqlogs/broker.log 
	3.启动控制台(jar包启动)
			nohup java -jar rocketmq-console-ng-1.0.0.jar &

	简单使用教程：
		生产者：DefaultMqProducer
			1.设置group名称 2.设置nameserver地址 3.开启链接 4.构造消息体Message body是二进制 5.发送消息 6.关闭链接
		消费者：DefaultPushMqConsumer|DefaultPullMqConsumer
			1.设置group名称 2.设置nameserver地址 3.设置消费策略 4.设置消费主题规则 5.监听消息内容 6.建立连接

	概念：
		1.集群消费：一个consumer group中的所有consumer实例 平均分摊消费消息   MessageModel.CLUSTERING
		2.广播消费：
		3.producer group 一类prouder的集合 比如一个producer叫 PG 分布式情况下会部署到n台机器，那么就相当于有n个producer 就可以看成一个组
			同样的 consumer group 需要采用集群模式消费消息 保证分布式的consumer平均消费消息 负载均衡

	消息中间件 能解决什么问题：
		1.最基本的消息的发布和订阅
		2.MessagePriority
			|-- rocketmq的所有消息都是持久化的，如果支持优先级则开销会很大，没有特意支持消息的优先级功能
			|-- 可以通过变通的形式 使用不同的Topic作为不用的优先级
		3.MessageOrder
			消息有序指能按照消息的发送顺序消费，rocketmq支持严格的有序消费
		4.MessageFilter
			|-- broker过滤 按照consumer的规则过滤消息  减少了consumer端的网络传输 增加了broker的负担 实现发杂
				rocketmq支持 通过Message [tag header body] 的过滤规则
			|-- consumer过滤 有应用程序自定义过滤规则 缺点是网络开销大 无用的消息都会传输到consumer端
		5.MessagePersistence
			消息持久化的方式：1.数据库 2.KV存储系统 3.文件记录形式 4.对内存数据缓存数据做一个持久化镜像
			rocketMq 第三种方式 利用Linux的文件系统内存cache来提供性能
		6.MessageReliability  (消息都由broker存储)
			1.broker正常关闭 2.broker异常crash 3.OS crash 4.断电 可恢复电源 5.无法开机 6.磁盘损坏
			1234 属于硬件资源可立即恢复情况 rocketmq可以保证不丢失消息 或者根据刷盘方式的不同 损失少量消息
			56 属于单点故障无法立即恢复 该单点的数据全部丢失 
				|-- 通过异步复制可保证99%的消息不丢失
				|-- 通过同步双写模式可以避免单点故障，但是会影响性能，适合对可靠性要求非常高的业务 比如money的业务
		7.Message Low Latency
			消息不堆积的情况下，消息到达broker后，立即送达consumer
			rocketmq使用Pull长轮询的方式，可以保证消息非常实时，实时性不低于push
		8.At Least One
			每个消息必须投递一次
			rocketmq消费完成后才发送Ack 没有消费就不会发送Ack确认消费的消息
		9.Exactyl Only Once --不支持
			1.不允许发送重复消息
			2.不允许消费重复消息
			rocketmq不支持，所以消费方要做到幂等性。正常情况下不会发生消息重复的情况，网络异常或者consumer启动异常可能会出现。
		10.Broker Buffer满了怎么办？
			Buffer通常指一个队列中的Buffer大小，通常大小都是有限的，满了怎么办呢？
			通常其他MQ解决方案都是使用抛弃策略，rocketmq没有这个概念，队列无限长。
			不过无限长是有前提的，rocketmq会定时清理过期数据。
		11.回溯消费
			consumer已经消费成功的消息，业务上可能要求重新消费。
			broker要提供一种机制，可以按照时间维度回退消费进度。
			rocketmq支持回溯消费，向前向后均可，毫秒级别
		12.消息堆积
			消息中间件的主要功能是异步解耦。为了保证后端系统的稳定性，在接收前端海量的消息后需要有一定的堆积能力。
				|-- buffer堆积 buffer满了如何处理？丢弃策略 取决于内存的大小
				|-- 持久化堆积 DB｜KV系统｜文件记录　当消息不能在缓存中命中时，势必会读取磁盘IO，读IO的吞吐量决定了消息堆积后的访问能力
				
				考虑点：
					１.消息堆积容量　２.消息堆积后,producer 和 consumer是否会受到影响　３.消息堆积后　访问磁盘堆积的消息时　吞吐量多大
		13.分布式事务
			分布式事务规范：XA　JTA　
					XA：数据库的两阶段提交
			rockemq支持分布式事务

		14.定时消息
			消息发送到broker之后，消息不被立即消费，等到特定的时间点再去消费
			rocketmq支持定时消息 但是不支持任意精度，支持特定的level 比如5s 10s 1m
		15.消息重试
			consumer消费失败后，需要提供一种机制，再消费一次，以使消息最终被消费掉。
			1.消息本身原因：
				比如序列化失败，消息本身不能被处理(例如充话费 手机号已注销)
				这种消息要跳过，消费下一条消息，即便立即重试，也会99%不成功。可以定时重试，10s 20s
			2.由于应用依赖的下游不可用 
				例如DB连接失败 网络不可达等原因
				这种消息即使执行下一条消息 依然会失败。这种情况建议休眠30s 再次重试消费

	RocketMq 是什么？
		1.是一个【队列模型】的消息中间件，具有 高可用、高性能、高实时、分布式的特点
		2.producer consumer queue 都可以实现分布式
		3.producer像 topic中的队列轮流发送消息 consumer如果做广播消费，则一个consumer实例消费所有的队列消息 如果做集群消费 平均消费
		4.保证严格的消息顺序
		5.拉取模式丰富
		6.亿级消息堆积能力
		7.实时的订阅机制
		8.高效的订阅者水平扩展能力
		9.依赖少
	

	
	rocketMq 多Master安装
		1.修改/etc/hosts 文件 添加 ip对域名的映射 ip nameserver ip master
		2.修改broker-*.properties 文件 添加配置属性
		3.broker nameserver jvm调优
		4.启动顺序：先NameServer 后Broker






