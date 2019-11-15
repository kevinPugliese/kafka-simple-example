package com.kafka.kevin.Teste.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaConsumerService {

  public void consumer() {
    String bootstrapServers = "127.0.0.1:9092";
    String groupId = "my-fourth-application";
    String topic = "first_topic";

    // create consumer configs
    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    // create consumer
    KafkaConsumer<String, String> consumer =
      new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);

    // subscribe consumer to our topic(s)
    consumer.subscribe(Arrays.asList(topic));

    // poll for new data
    while(true){
      ConsumerRecords<String, String> records =
        consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

      for (ConsumerRecord<String, String> record : records){
        System.out.println("Key: " + record.key() + ", Value: " + record.value());
        System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset());
      }
    }
  }
}
