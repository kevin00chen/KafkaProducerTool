package com.ckm.kafka.producer.inter;

import java.util.Properties;

/**
 * Created by ckm on 2016/8/25.
 */
public interface KafkaProducerTool {
    /**
     * 发布消息，指定topic
     * @param topic
     * @param message
     */
    void publishMessage(String topic, String message);

    /**
     * 发布消息，
     * 使用kafkaconfig.properties中配置的app.topic参数，
     * 如果该参数为设置，则默认topic="default-topic"
     * @param message
     */
    void publishMessage(String message);

    /**
     * 发布消息，指定topic，指定分区key，指定消息内容
     * @param topic
     * @param partitionKey
     * @param message
     */
    void publishPartitionedMessage(String topic, String partitionKey, String message);

    /**
     * 发布消息，指定分区key，指定消息内容
     * 使用kafkaconfig.properties中配置的app.topic参数，
     * 如果该参数为设置，则默认topic="default-topic"
     * @param partitionKey
     * @param message
     */
    void publishPartitionedMessage(String partitionKey, String message);

    /**
     * 设置Producer的参数配置
     * @param producerProperties
     */
    void setProducerProperties(Properties producerProperties);

    public Properties getProducerProperties();

    /**
     * 设置读取配置文件的参数配置
     * @param configProperties
     */
    void setConfigProperties(Properties configProperties);

    public Properties getConfigProperties();
}
