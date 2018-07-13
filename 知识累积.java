
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
		|-- dubbo.registry.address=10.20.153.10:9090 等价于<dubbo:registry address="10.20.153.10:9090" /> 公用配置可以通过properties文件配置
		|-- 加载顺序：jvm参数>dubbo.xml>dubbo.properties
		
		|-- dubbo启动默认检查依赖是否可用，不可以抛异常，阻止spring初始化，以便上线更早发现问题,默认check=true
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

		
		



