package com.github.ganeshgowtham.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        Consumer c = new Consumer();
        c.consumeData();
    }

    private org.apache.kafka.clients.consumer.Consumer<String, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.8:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        // Create the consumer using props.
        final org.apache.kafka.clients.consumer.Consumer<String, String> consumer =
                new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList("mytopic"));
        return consumer;
    }

    private void consumeData() {
        final org.apache.kafka.clients.consumer.Consumer<String, String> consumer = createConsumer();


        while (true) {
            System.out.println("searching ...");
            final ConsumerRecords<?, String> consumerRecords =
                    consumer.poll(1000);


            consumerRecords.forEach(record1 -> {
                System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                        record1.key(), record1.value(),
                        record1.partition(), record1.offset());
            });

            consumer.commitAsync();

            // consumer.close();
        }
    }
}
