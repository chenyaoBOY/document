
��һ��:
	spring��ô���ö������Դ(�ѽ��)
	aopԭ��(�ѽ��)
	mybatis��ôʵ��������(�ѽ��)
	dubbo��zookeeper�Ĺ���ԭ��
	aop���뼶����ô����
	����������private����ô��Ϊʲô(�ѽ��)
	���͸�ʲô��
	vatoer��list������(�ѽ��)
	���л�������
	dubbo��ô����consumerֱ����
	�̳߳أ��̳߳ض���������ô��
	rpc��http������
	springboot�м�������  �������õ����� 
	����mq��ack��ô��֤���ݲ���ʧ�� 
	���и߲����Ĵ���zookeeper��ô����ѡ���ģ����޷�֧��ô���
	������������Щ
	redis��͸��ô���£���ô���redis��͸��(�ѽ��)
�ڶ��ң�
	���ݿ��������ô����
	��ô�������ݿ����Ӹ��Ƶ�ʱ��
	������û�ã���ô����ʧЧ
	springboot��ô���ö������Դ
	�߲���������ô�����
	�ֲ�ʽ������ô�����
	mq�ù�ô��fq��mq������
	���߳��ù�ô�����߳�����Ŀ������ô�õ�
	gc���������㷨��jvmʲôʱ�����������������
	�̵߳�Ӧ�ó���





	1.https���͵�url����ͨ��httpclient���ʵ�ʱ�� ��Ҫ֤����֤

		�����������һ�־������ظ���վ��֤�飬�ڶ��ַ��������ƹ�֤����֤

	2.�������/���洩͸/����ѩ��

		2.1���洩͸������ѯһ������ʱ��������û�У���ȥ���ݿ��в�ѯ�����ݿ�Ҳû�йʶ���д�뻺�棬��˷���������
				�������ܴ�ʱ,db���׹ҵ����������Ϊ���洩͸��
			|--���������
				|--1.�����ݿ�û�����ݵ�ʱ����Ȼд�뻺�棬д������ݣ��趨����ʱ��Ϊ5�����ڣ��������ݿ�ѹ��
				|--2.��¡�������������п��ܴ��ڵ����ݹ�ϣ��һ���㹻���bitmap�У�һ��һ�������ڵ����ݻᱻ ���bitmap���ص����Ӷ������˶Եײ�洢ϵͳ�Ĳ�ѯѹ��
		
		2.2����ѩ����ָ����Ĺ���ʱ��������ͬ����ĳ��ʱ��ͻȻ����Ϊ�գ���������ͻȻ��������db,db˲ʱѩ����
			|--���������
				|--1.�����ϵͳ����߿����ü������߶��еķ�ʽ��֤����ĵ��̣߳����̣�д���Ӷ�����ʧЧʱ�����Ĳ��������䵽�ײ�洢ϵͳ��
				|--2.��ԭ�й���ʱ���ϼ������ֵ(���п��ܷ���ѩ��)

		2.3����������뻺��ѩ�����ƣ���ͬ����ǰ���Ǻܶ�Key,����������ȵ�Key,�����������ȵ����ݣ��������ܴ󣬵��û���ʧЧʱ���������洩͸���������ݿ⣬
		����ʱ�������ܴ���Ȼ���db�������Ӱ�졣
			|--���������
					|--1.˼·����keyʧЧʱ�������̷߳������ݣ�����һ���߳�load db��Ȼ������д�뻺��,�����̶߳�ȡ��д��Ļ���
						ʹ�û�����(mutex key)ģ��ͬ����Ч����redis.setnx(key_mutex,value,time) �÷�������0��1 �൱��ͬ������ͬһʱ�����һ���̷߳���
															 memcacheʹ�� add(key_mutex,time)==trueʵ�֣������ж���load db ������ �߳���΢˯�ߣ�Ȼ�����´ӻ����ȡ����
					|--2.�������ڣ�redis �����ù���ʱ��Ļ� key�Ͳ�����ڡ�
						 �߼����ڣ�keyֵ������ڣ��ѹ���ʱ��Ž����档
						 Ҳ���������������ڣ�Ȼ��д��ʱ���񣬶�ʱ���»��档

		���ҵ��ϵͳ����Զ���Ǿ���������������û����ã�ֻ�������

	3.vector �� list ������
		|--1.vector �̰߳�ȫ��List��ͬ����list���ܸ���
		|--2.Ԫ�س��ݣ�list����50%������vector������list���ӽ�Լ�ڴ�ռ�

	4.LinkeList������β��ӡ� ��β��ȡ����

	5.solr facet���÷�
		 query.setFacet(true);
		 query.setFacetLimit(100);
		 query.addFacetField("idShopDefault","idBrand","idCategoryDefault");
		 ��ģ����ѯ�Ľ������ ��������key���з��飬����ö�Ӧ�����е� �������ƣ��÷��������µĽ������
		 ���簴��  Ʒ�Ʒ��飺�õ��Ĵ�����Ľ������ {Ʒ�ƣ�{[��Ϊ:128],[С��:23],...}

	6.solr4.3�汾 �������ʱ����ѯ���������� AND filed:*  �����н���������

	7.�ݵ��ԣ�������ͬ�Ĳ����ظ���ִ�У����������ͬ��
		�ݵ�����ϵͳ�Ľӿڶ���һ�ֳ�ŵ(������ʵ��), ��ŵֻҪ���ýӿڳɹ�, �ⲿ��ε��ö�ϵͳ��Ӱ����һ�µ�. 
		����Ϊ�ݵȵĽӿڻ���Ϊ�ⲿ����ʧ���ǳ�̬, ����ʧ��֮���Ȼ��������

	8.dubboѧϰ

		|-- dubbo����spring��Schema��չ���м��أ�������spring����ͨ��API��ʽ���ã��������Ƽ�
		|-- ��ǩ֧���Զ�����������ڲ�ͬ��չ��ʵ�ֵ���������
		|-- ���õĲ���˳��:�����������ȣ��ӿڼ����֮��ȫ�������ٴ�֮�������������������ṩ��
		
		|-- dubbo �Զ�����classpath�����dubbo.properties�ļ�������ͨ��jvm����������-Ddubbo.properties.file=xxx.properties����ȱʡ����
		|-- properties �����ļ��� dubbo.registry.address=10.20.153.10:9090 �ȼ���<dubbo:registry address="10.20.153.10:9090" /> �������ÿ���ͨ��properties�ļ�����
		|-- ���ü������ȼ���jvm����>dubbo.xml>dubbo.properties
		
		|-- dubbo����Ĭ�ϼ�������Ƿ���ã����������쳣����ֹspring��ʼ�����Ա����߸��緢������,Ĭ��check=true
		|-- reference���õĽӿ�Ĭ�����ӳٳ�ʼ���ģ�ֻ�б�ʹ�õ�ʱ��ų�ʼ����init=true ����������ʼ��
		|-- ��Ⱥ����ʧ��ʱ��dubboĬ���ݴ����� failover����  ���Դ������ã�retries=2 ��failoverģʽ��Ч ��Ⱥģʽ���ã�cluster='failfast'

		|-- ���ؾ���:Ĭ��Ϊrandom������� loadbanlance="roundrobin"  ���ؾ����㷨���������ѯ����Ծ����һ����hash
		

		provider:
			|-- application��name����,�ṩ���ķ������ƶ��壬���ڼ���������ϵ
			|-- registry:address���� ָ����¶������н� ������zookeeper������multicast
			|-- protocol:name,port  ָ����¶�����Э��Ͷ˿ڣ��ṩ��ָ�������ѷ���������
			|-- service: interface,ref  ָ������¶�ṩ�� ��һ���������ע����ע������Ҳ����ʹ�ö��Э�鱩¶
		consumer:
			|-- application��name ���������ߵ�����
			|-- registry:address ���ַ�����н�
			|-- reference:id,interface,check,timeout��һ�����ÿ���ָ����ע������
			
		���ù�ϵ��
			|-- ע�����Ļ���[������]��provider��ַ�б� ���͸� ������
			|-- ������ Ĭ�����ؾ����㷨��ѡ��provider,��ʧ����ѡ��һ��Provider
			|-- prodiver and consumer ����ʱÿ���ӷ��͵������ݵ��������

			��ͨ�ԣ�
				|-- register,provider,consumer֮���Ϊ�����ӣ�monitor����
				|-- provider崻���register����֪ͨconsumer
				|-- provider��consumer����ֱ����monitor��register�ǿ�ѡ��
			
			��׳��
				|-- registerȫ��崻������κ�Ӱ�죬consumer���ػ�����prodider�б�
				|-- register���Ӽ�Ⱥ���ͻ��˿��Զ������µ�register
				|-- providerȫ��崻���consumer�޷�ʹ�ã���������
				|-- provider��״̬���ɽ�����Ⱥ��register���͸�consumer
		
		��Ե�ֱ����ֻ����ָ�������Է���ӿ�Ϊ��λ������ע�����ĵ��ṩ���б�
			|-- consumer �����ٷ��ַ���ֱ���ڽӿ���ָ��url·�� 
			    <dubbo:reference id="demoService" check="false" interface="com.alibaba.dubbo.demo.DemoService" url="dubbo://localhost:20880"/>
		
		ֻ���ģ�
			|-- ����������������һ��ע������ʱ�����ڿ����ķ�����ע�ᵽ��ע�����ģ����Ӱ��consumer�����ѣ��������ؾ���
			|-- ���������ֻ���ķ��񣬽���ע�������   register=false / address="...2181?register=false"
		
		ֻע�᣺
			|-- ����һ��provider��Ҫͬʱע�ᵽ����register���Ҹ�provider������������һ��register����
			|-- ���������ö��Ĺ���  subscribe=false

		��̬����
			|-- ͨ���˹��ķ�ʽ�����������ߺ����ߣ���ʱ��Ҫ��ע�����ı�ʶΪ�Ƕ�̬����ģʽ
			|-- dynamic=false ��ģʽ�£�monitor�еķ��񲻻����ŷ���Ĺر���ʧ��һֱ�����ڷ����б��������ֶ�ɾ��������̬�ķ�����Զ���ʧ
				���Ƿ���رյ�����£��������޷����ӵ������
		��Э�飺
			|-- ��ͬ������Ӧ�ڲ�ͬ��Э�飬�������ʺ϶�����Э�飬С���ݴ󲢷��ó�����Э��
		
		��register :ʹ��id ����
		
		������飺
			|-- ��һ�������ж��ʵ��ʱ�����Բ��÷���ķ���
			|-- ��������ʱ group='service1',group="service2",ref���ò�ͬ��ʵ���࣬Ȼ�������������ѵ�ʱ��Ҳ����group�����id ,����ָ��ʵ�����������
		
		��汾��
			|-- ������������ʱ�򣬿��ܴ��ڲ����ݵ������ͨ����ӷ���İ汾�ţ��ڵ��õ�ʱ����Լ�������

		����ۺϣ�
			|-- ���ӿ�һ��������ʵ�����ж��������˵�����group�������֣���Ҫ��ÿ������Ľ�����кϲ���
				��������ʵ�ַ���ľۺ�Ч���������в˵��ķ���ۺϵ�һ�������
			|-- reference group="*/a,b,c" merger=true  ����һЩ�����ĺϲ����ԣ�����ֵ����� map set list ��������֧��
		
		������棺
			|-- ���ڼ����������ݵķ����ٶ� lru �����������ʹ��ԭ��ɾ�����໺�棬�������ȵ����ݱ�����
			|-- reference cache="lru" ÿ������ȱʡ����1000���������ݣ���ͬ�����Ļ��治�໥Ӱ�죬�Ƚ��ȳ�ԭ��
			|-- ���û���Ĵ�С��<dubbo:parameter key="cache.size" value="100"/>

		�������ԣ�
			|-- ���ڼ������Ƿ���ã������ڼ�ء����з����Զ�ʵ��EchoService�ӿڣ�ֱ��ǿת����$echo("ok")�������Խ��

		��������Ϣ��
			|-- ������ǵ�ǰ���ù���������Ļ�����Ϣ
			|-- RpcContext.getContext(); 
				|-- isProviderSide���Ƿ�ΪProvider
				|-- getRemoteHost:��ȡ���÷���ַ
				|-- getUrl().getParameter("application"):��ȡ��ǰ�����������Ϣ

		�첽���ã�
			|-- ����NIO���첽������ԭ������ڶ��߳̿�����С
			|-- ���÷���������첽���ԣ�async="true"���������ķ���ֵ����������return="false",����future�����͹���ĳɱ�
				�첽ʹ�÷�����
					|-- 1.�Ⱥ�����һ��ʹ�÷�����÷�������ʱ����ֵΪnull
					|-- 2.ʹ�������Ļ�ȡfuture����ȡ��Ϻ�֮ǰ���õķ�������ᱻ�첽�Ż�future
						Ȼ��ʹ��future��get����������ȡ�����
		
		�����ص���
			|-- �ڷ����provider����consumer��ʵ�֣����ṩ�߿������������ߵĴ��롣����˿��Ե��ÿͻ����߼�
				�����ֻ�ṩ�ӿڣ��������ṩʵ�֣������ߵ��÷����ߣ��ﵽʹ���ṩ������������ʵ�ֵ�Ŀ��
			<dubbo:service  interface="com.alibaba.dubbo.demo.DemoService" ref="demoService" connections="1" callbacks="1000">
				<dubbo:method name="addListener">//���÷����Ƿ���Իص� �����ûص��Ľӿ� ��provider���õĽӿ� consumerʵ�ֵĽӿ�
					<dubbo:argument callback="true" type="com.alibaba.dubbo.demo.CallbackListener"/>
				</dubbo:method>
			</dubbo:service>
		
		�ӳٱ�¶��
			|-- ���������Ҫ�ȴ���ʱ�������ȫ��λ������Ӧ���ڷ�����ȫ��ʼ��֮���ٱ����ѣ�Ӧ���ӳٱ�¶
			|-- service deplay="500"  
			|-- <dubbo:provider deplay="-1"/> ��������spring��ʼ��������Ϻ��ٱ�¶���� 

		�������ƣ�
			|-- ���Ʒ�������ѵ��÷���Ĳ�������
			|-- ����ˣ�<dubbo:service exeutes="10"> 
			|-- �ͻ��ˣ�<dubbo:reference actives="10"> ���� �ڷ�������ƿͻ��˵Ĳ���������<dubbo:service actives="10"> 

		���ӿ��ƣ����Ʒ������˵��������� <dubbo:provider accepts="10"/>
		�ӳ�����: ���е��÷���ʱ���ٴ��������� ֻ�Գ����ӵ�dubboЭ����Ч  <dubbo:protocol lazy="true"/>
		ճ�����ӣ��Զ������ӳ����ӡ��������ÿͻ������ǵ���ͬһ�ṩ�ߵķ��񣬳��Ǹ�provider������������һ̨   <dubbo:protocol sticky="true"/>
		
		������֤��
			|-- 1.��ֹ�������ƹ�ע������ֱ�ӷ����ṩ�� 2.��ע�����Ŀ��Ƶ���Ȩ�� 3.ע�����Ŀ������ı���Ȩ��ʽ
			|-- �������ԣ�����token��֤ʱ�����consumer����register�������������ʣ������ֱ����ʽ��ֻ�����úͷ����һ����tokenֵʱ��
				�ͻ��˲�����������provider��Ҳ����˵��������֤�ɷ�ֹconsumerֱ����
		
		·�ɹ���
			|-- ��ͨ��ExtensionLoader��չ������ʹ�ù���ģʽ��ȡRegistryע���࣬д���Ӧ���ã���ָ�������������·�ɹ���
			|-- ֧�� ����·�ɺͽű�·��
		���ù���
			|-- ������ע�����Ķ�̬�ĸ���ԭ�ȵķ�������
			|-- ��·�ɹ������ƣ�����ͬ���ѣ�·�ɵĿ�ͷ�ǣ�condition/script�����ù����ǣ�override
		���񽵼�
			|-- ����ʹ�����߶�ָ�����񲻷���Զ�̵��ã�ֱ�ӷ���nullֵ mock=force:return+null
			|-- Ҳ���������ѷ���ָ������ķ�������ʧ�ܺ󣬷���null�������쳣 mock=fail:return+null
			|-- ʵ����override://0.0.0.0/com.foo.BarService?category=configurators&dynamic=false&application=foo&mock=force:return+null
			
		9.����spring schema��֧�֣��������xml�ļ� ��ʼ��bean
			|-- 1 ���Javabean
			|-- 2 ���xsd�ļ�
			|-- 3 �̳�NamespaceHandlerSupport �� BeandefinitionParser ʵ��xml�Ľ���
			|-- 4 ��дspring.handlers -ָ�������������ռ䴦���� ��spring.schemas -ָ�����ݵ�xsd�ļ� �����ļ�
		
		10.���� ͨ���
			|-- �������ã����������ͣ������������Ϊһ�ֲ�����T,K,V,E����ĸ�����Դ��������bean���ͣ�����ǿ��չ�Բ���ߴ���Ŀɶ���
			|-- ͨ������ƶ������е����ͷ�Χ��<? extend/super T>��ָ�� �����ޣ����Ǿ������ǣ�ɾ���˾���Ԫ�ص����ɾ�����ܣ�ֻ�ṩ��Ԫ���޹صĲ���
			|-- ���Ͳ�������û��ָ�����͵������ޣ����Ͳ���֮����Object���ͣ�����֮��û�з��͵Ĵ��ڡ�����׶η��͵ľ����Կ���ͨ�����䶯̬�޸ķ��͵�����
			|-- Java���ܴ����������͵ķ������飬��Ϊ���Ͳ������������Object���ͣ������ж������е����;�����ʲô����




�����ܽ᣺
	1.����дɶ��ɶ
	2.���̱߳���
		|-- �̳߳���ôʹ�ã��ù�û����ô�õ�
		|-- �̳߳�������δ���
		|-- ������ThreadPoolExcutor�ļ��������Ͳ����
	3.����
		|-- redis �������� ��ʲô�����ص� ���ù���Щ
		|-- ��Ŀ��������Щ����  ����������Ҫ���µ�ʱ����ô���� 
		|-- ���������ʲô ��α���
	4.
		


linux ��������

	netstat -lnp|grep 8080  �鿴����
	
	|-- grep ɸѡ����
		|-- grep 'INFO' demo.log     #���ļ�demo.log�в������а���INFO����
		|-- grep -c 'ERROR' demo.log   #����ļ�demo.log�в������а���ERROR���е�����
		|-- grep -v 'ERROR' demo.log   #���Ҳ���"ERROR"����
		|-- grep -o 'order-fix.curr_id:\([0-9]\+\)' demo.log    #-oѡ��ֻ��ȡorder-fix.curr_id:xxx�����ݣ�������һ���У������������Ļ��
	
	|-- sed ����

		����ʹ��һ�������ʹ���ڴ�����10������     
		ps -aux | sort -k4nr | head -n 10
		����ʹ��һ�������ʹ��CPU����10������     
		ps -aux | sort -k3nr | head -n 10

		�鿴Linux��������
			systemctl list-unit-files --type=service | grep enabled

		
Docker
	����docker
		|--sudo systemctl start docker
	docker�ͻ���ʹ��
		|--docker pull training/webapp ����ĳ����
		|--docker run -d -p 8080:5000 training/webapp python app.py ָ��ӳ��˿�ִ������ 8080:�������˿� 5000:�����˿�
	ɾ�������˳�������
	    |--docker rm $(docker ps -q -f status=exited)
		|-- docker ps -q -f status=exited ��ѯ����״̬��exited��������id

	   docker run -d --name master -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mysql 
		   -v /usr/local/src/mysql/master-data:/var/lib/mysql 
		   -v /usr/local/src/mysql/mater.cnf:/etc/mysql/mysql.conf.d/mysql.cnf 
		   mysql:5.6




Shiro
	
	������Subject  SecurityManager Realm 
		|-- Subject :��ǰ�û�
		|-- SecurityManager �����������û� ����
		|-- Realm ������DB ��ȡ��֤����Ȩ��Ϣ �������������Authentication��Authorization
			|-- Authentication : ��֤  ������֤��½�˺ź������Ƿ�ƥ��
			|-- Authorization : ��Ȩ ��֤���û��Ƿ�ӵ��ĳ��������Ȩ��


				1.ShiroFilterFactoryBean�����ù��˹��� Ҫset  SecurityManager
				2.SecurityManagerҪset �Զ���Realm  ʹ��Ĭ�ϵ� DefaultWebSecurityManager
				3.�Զ���Realm ʵ��AuthorizingRealm ��ʵ����֤����Ȩ��������
				4.DelegatingFilterProxy ���ڹ���url


RocketMq
	 cd /usr/local/src/rocketmq-all-4.3.0/distribution/target/apache-rocketmq/
	1.����nameserver
			nohup sh bin/mqnamesrv & tail -f ~/logs/rocketmqlogs/namesrv.log
	2.����broker
			nohup sh bin/mqbroker -n localhost:9876 & tail -f ~/logs/rocketmqlogs/broker.log 
	3.��������̨(jar������)
			nohup java -jar rocketmq-console-ng-1.0.0.jar &

	��ʹ�ý̳̣�
		�����ߣ�DefaultMqProducer
			1.����group���� 2.����nameserver��ַ 3.�������� 4.������Ϣ��Message body�Ƕ����� 5.������Ϣ 6.�ر�����
		�����ߣ�DefaultPushMqConsumer|DefaultPullMqConsumer
			1.����group���� 2.����nameserver��ַ 3.�������Ѳ��� 4.��������������� 5.������Ϣ���� 6.��������

	���
		1.��Ⱥ���ѣ�һ��consumer group�е�����consumerʵ�� ƽ����̯������Ϣ   MessageModel.CLUSTERING
		2.�㲥���ѣ�
		3.producer group һ��prouder�ļ��� ����һ��producer�� PG �ֲ�ʽ����»Ჿ��n̨��������ô���൱����n��producer �Ϳ��Կ���һ����
			ͬ���� consumer group ��Ҫ���ü�Ⱥģʽ������Ϣ ��֤�ֲ�ʽ��consumerƽ��������Ϣ ���ؾ���

	��Ϣ�м�� �ܽ��ʲô���⣺
		1.���������Ϣ�ķ����Ͷ���
		2.MessagePriority
			|-- rocketmq��������Ϣ���ǳ־û��ģ����֧�����ȼ�������ܴ�û������֧����Ϣ�����ȼ�����
			|-- ����ͨ����ͨ����ʽ ʹ�ò�ͬ��Topic��Ϊ���õ����ȼ�
		3.MessageOrder
			��Ϣ����ָ�ܰ�����Ϣ�ķ���˳�����ѣ�rocketmq֧���ϸ����������
		4.MessageFilter
			|-- broker���� ����consumer�Ĺ��������Ϣ  ������consumer�˵����紫�� ������broker�ĸ��� ʵ�ַ���
				rocketmq֧�� ͨ��Message [tag header body] �Ĺ��˹���
			|-- consumer���� ��Ӧ�ó����Զ�����˹��� ȱ�������翪���� ���õ���Ϣ���ᴫ�䵽consumer��
		5.MessagePersistence
			��Ϣ�־û��ķ�ʽ��1.���ݿ� 2.KV�洢ϵͳ 3.�ļ���¼��ʽ 4.���ڴ����ݻ���������һ���־û�����
			rocketMq �����ַ�ʽ ����Linux���ļ�ϵͳ�ڴ�cache���ṩ����
		6.MessageReliability  (��Ϣ����broker�洢)
			1.broker�����ر� 2.broker�쳣crash 3.OS crash 4.�ϵ� �ɻָ���Դ 5.�޷����� 6.������
			1234 ����Ӳ����Դ�������ָ���� rocketmq���Ա�֤����ʧ��Ϣ ���߸���ˢ�̷�ʽ�Ĳ�ͬ ��ʧ������Ϣ
			56 ���ڵ�������޷������ָ� �õ��������ȫ����ʧ 
				|-- ͨ���첽���ƿɱ�֤99%����Ϣ����ʧ
				|-- ͨ��ͬ��˫дģʽ���Ա��ⵥ����ϣ����ǻ�Ӱ�����ܣ��ʺ϶Կɿ���Ҫ��ǳ��ߵ�ҵ�� ����money��ҵ��
		7.Message Low Latency
			��Ϣ���ѻ�������£���Ϣ����broker�������ʹ�consumer
			rocketmqʹ��Pull����ѯ�ķ�ʽ�����Ա�֤��Ϣ�ǳ�ʵʱ��ʵʱ�Բ�����push
		8.At Least One
			ÿ����Ϣ����Ͷ��һ��
			rocketmq������ɺ�ŷ���Ack û�����ѾͲ��ᷢ��Ackȷ�����ѵ���Ϣ
		9.Exactyl Only Once --��֧��
			1.���������ظ���Ϣ
			2.�����������ظ���Ϣ
			rocketmq��֧�֣��������ѷ�Ҫ�����ݵ��ԡ���������²��ᷢ����Ϣ�ظ�������������쳣����consumer�����쳣���ܻ���֡�
		10.Broker Buffer������ô�죿
			Bufferͨ��ָһ�������е�Buffer��С��ͨ����С�������޵ģ�������ô���أ�
			ͨ������MQ�����������ʹ���������ԣ�rocketmqû���������������޳���
			�������޳�����ǰ��ģ�rocketmq�ᶨʱ����������ݡ�
		11.��������
			consumer�Ѿ����ѳɹ�����Ϣ��ҵ���Ͽ���Ҫ���������ѡ�
			brokerҪ�ṩһ�ֻ��ƣ����԰���ʱ��ά�Ȼ������ѽ��ȡ�
			rocketmq֧�ֻ������ѣ���ǰ�����ɣ����뼶��
		12.��Ϣ�ѻ�
			��Ϣ�м������Ҫ�������첽���Ϊ�˱�֤���ϵͳ���ȶ��ԣ��ڽ���ǰ�˺�������Ϣ����Ҫ��һ���Ķѻ�������
				|-- buffer�ѻ� buffer������δ����������� ȡ�����ڴ�Ĵ�С
				|-- �־û��ѻ� DB��KVϵͳ���ļ���¼������Ϣ�����ڻ���������ʱ���Ʊػ��ȡ����IO����IO����������������Ϣ�ѻ���ķ�������
				
				���ǵ㣺
					��.��Ϣ�ѻ���������.��Ϣ�ѻ���,producer �� consumer�Ƿ���ܵ�Ӱ�졡��.��Ϣ�ѻ��󡡷��ʴ��̶ѻ�����Ϣʱ�����������
		13.�ֲ�ʽ����
			�ֲ�ʽ����淶��XA��JTA��
					XA�����ݿ�����׶��ύ
			rockemq֧�ֲַ�ʽ����

		14.��ʱ��Ϣ
			��Ϣ���͵�broker֮����Ϣ�����������ѣ��ȵ��ض���ʱ�����ȥ����
			rocketmq֧�ֶ�ʱ��Ϣ ���ǲ�֧�����⾫�ȣ�֧���ض���level ����5s 10s 1m
		15.��Ϣ����
			consumer����ʧ�ܺ���Ҫ�ṩһ�ֻ��ƣ�������һ�Σ���ʹ��Ϣ���ձ����ѵ���
			1.��Ϣ����ԭ��
				�������л�ʧ�ܣ���Ϣ�����ܱ�����(����仰�� �ֻ�����ע��)
				������ϢҪ������������һ����Ϣ�������������ԣ�Ҳ��99%���ɹ������Զ�ʱ���ԣ�10s 20s
			2.����Ӧ�����������β����� 
				����DB����ʧ�� ���粻�ɴ��ԭ��
				������Ϣ��ʹִ����һ����Ϣ ��Ȼ��ʧ�ܡ����������������30s �ٴ���������

	RocketMq ��ʲô��
		1.��һ��������ģ�͡�����Ϣ�м�������� �߿��á������ܡ���ʵʱ���ֲ�ʽ���ص�
		2.producer consumer queue ������ʵ�ֲַ�ʽ
		3.producer�� topic�еĶ�������������Ϣ consumer������㲥���ѣ���һ��consumerʵ���������еĶ�����Ϣ �������Ⱥ���� ƽ������
		4.��֤�ϸ����Ϣ˳��
		5.��ȡģʽ�ḻ
		6.�ڼ���Ϣ�ѻ�����
		7.ʵʱ�Ķ��Ļ���
		8.��Ч�Ķ�����ˮƽ��չ����
		9.������
	

	
	rocketMq ��Master��װ
		1.�޸�/etc/hosts �ļ� ��� ip��������ӳ�� ip nameserver ip master
		2.�޸�broker-*.properties �ļ� �����������
		3.broker nameserver jvm����
		4.����˳����NameServer ��Broker






