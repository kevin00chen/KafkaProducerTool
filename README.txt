这个项目对Kafka的producer进行封装，使得Producer的使用能够最简单化。


所有配置参数都写入kafkaconfig.properties文件中，参数主要分为两种：
1、用户自定义参数
这类参数需要以"app."开头，
比如指定topic，app.tipic=test-topic

2、Kafka Producer参数
这类参数都是kafka producer的参数，参数的key和value必须符合kafka的规范，
更多参数可以参考官方文档：http://kafka.apache.org/documentation.html#producerconfigs

使用示例：
1、最简单的调用
KafkaProducerTool kafkaProducerTool = new KafkaProducerToolImpl();
kafkaProducerTool.publishMessage("test message");
这两行代码，就会将"test message"以kafkaconfig.properties文件中的配置写入对应的broker的topic中

2、如果代码中临时修改一些Producer参数，
KafkaProducerTool kafkaProducerTool = new KafkaProducerToolImpl();
Properties producerProperties = kafkaProducerTool.getProducerProperties();
producerProperties.put("xx", "yy");
kafkaProducerTool.publishMessage("test message");

3、自定义Parititioner的Producer
有两种办法a和b：
(a)在kafkaconfig.properties中新增一个配置参数partitioner.class=com.xx.yy.TestPartitioner
(b)使用2中的方法，获取producerProperties
Properties producerProperties = kafkaProducerTool.getProducerProperties();
producerProperties.put("partitioner.class", "SimplePartitioner");


同时，也可以在自己的项目中使用自定义的配置文件生成一个Properties类型的configProperties对象，然后set到KafkaProducerTool中。
也可基于KafkaProducerTool接口，定义自己的ProducerTool类。

更多使用方法待后续进一步开发。。。。