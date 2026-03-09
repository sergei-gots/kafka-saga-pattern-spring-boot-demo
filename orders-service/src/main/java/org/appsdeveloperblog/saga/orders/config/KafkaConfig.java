package org.appsdeveloperblog.saga.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaConfig {

    private final int TOPIC_PARTITIONS = 3;
    private final int TOPIC_REPLICAS = 3;

    @Value("${order.events.topic.name}")
    private String orderEventsTopicName;

    @Value("${product.commands.topic.name}")
    private String productCommandsTopicName;

    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    NewTopic createOrderEventsTopic() {
        return TopicBuilder
                .name(orderEventsTopicName)
                .partitions(TOPIC_PARTITIONS)
                .replicas(TOPIC_REPLICAS)
        .build();
    }

    @Bean
    NewTopic createProductCommandsTopic() {
        return TopicBuilder
                .name(productCommandsTopicName)
                .partitions(TOPIC_PARTITIONS)
                .replicas(TOPIC_REPLICAS)
        .build();
    }

}
