package com.ckm.kafka.producer.impl;

import com.ckm.kafka.producer.inter.KafkaProducerTool;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ckm on 2016/8/25.
 */
public class KafkaProducerToolImpl implements KafkaProducerTool {
    private Producer<String, String> producer;
    private Properties configProperties;
    private Properties producerProperties;



    private ProducerConfig producerConfig;


    public KafkaProducerToolImpl() {
        this("default");
    }


    public KafkaProducerToolImpl(String configFilePath) {
        configProperties = generateConfigProperties(configFilePath);

        producerProperties = new Properties();
        for (String configStr : configProperties.stringPropertyNames()) {
            // 非application自定义参数
            if (!configStr.startsWith("app."))
                if (producerProperties.get(configStr) == null)
                    producerProperties.put(configStr, configProperties.getProperty(configStr));
        }

        producerConfig = new ProducerConfig(producerProperties);
        producer = new Producer<String, String>(producerConfig);
    }


    /**
     * 获取默认config.properties对象
     * @return
     */
    private Properties generateConfigProperties(String configFilePath) {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            if (configFilePath.equals("default"))
                inputStream = this.getClass().getClassLoader().getResourceAsStream("kafkaconfig.properties");
            else
                inputStream = new FileInputStream(configFilePath);

            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    public void publishMessage(String topic, String message) {
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, message);
        producer.send(data);
    }


    public void publishMessage(String message) {
        String topic = configProperties.getProperty("app.topic", "default-topic");
        publishMessage(topic, message);
    }

    public void publishPartitionedMessage(String topic, String partitionKey, String message) {
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, partitionKey, message);
        producer.send(data);
    }

    public void publishPartitionedMessage(String partitionKey, String message) {
        String topic = configProperties.getProperty("app.topic", "default-topic");
        publishPartitionedMessage(topic, partitionKey, message);
    }

    public void setProducerProperties(Properties producerProperties) {
        this.producerProperties = producerProperties;
    }

    public Properties getProducerProperties() {
        return producerProperties;
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public Properties getConfigProperties() {
        return configProperties;
    }

    public Producer<String, String> getProducer() {
        return producer;
    }

    public void setProducer(Producer<String, String> producer) {
        this.producer = producer;
    }

}
