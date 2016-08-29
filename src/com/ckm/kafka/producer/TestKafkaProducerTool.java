package com.ckm.kafka.producer;

import com.ckm.kafka.producer.impl.KafkaProducerToolImpl;
import com.ckm.kafka.producer.inter.KafkaProducerTool;

import java.util.Properties;

/**
 * Created by ckm on 2016/8/29.
 *
 */
public class TestKafkaProducerTool {
    public static void main(String[] args) {
        KafkaProducerTool kafkaProducerTool = new KafkaProducerToolImpl();
        Properties properties = kafkaProducerTool.getProducerProperties();
        properties.put("partitioner.class", "com.xx.yy.TestPartitioner");

        kafkaProducerTool.publishMessage("test message");
    }
}
