package com.github.ganeshgowtham.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Publisher {
    public static void main(String[] args) {
        Publisher p = new Publisher();
        p.publishData();
    }

    private Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.8:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    private void publishData() {
        final Producer<String, String> producer = createProducer();
        Timer timer = new Timer();
        final AtomicInteger count = new AtomicInteger();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count.addAndGet(1);
                final ProducerRecord<String, String> record =
                        new ProducerRecord("mytopic", "1",
                                "Hello Mom " + count.get());
                System.out.println("published " + count.get() + "  " + record.toString());
                producer.send(record);
                producer.flush();
                //  producer.close();
            }
        }, 0, 1000);

    }
}
