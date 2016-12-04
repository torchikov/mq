package com.torchikov.config;

import com.torchikov.JmsMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by sergei on 04.12.16.
 */
@Configuration
@ComponentScan(basePackages = {
    "com.torchikov"
})
public class SpringConfig {

    @Bean
    public ActiveMQConnectionFactory amqConnectionFactory(){
       return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    CachingConnectionFactory connectionFactory(){
        return new CachingConnectionFactory(amqConnectionFactory());
    }

    @Bean
    DefaultMessageListenerContainer messageListenerContainer(CachingConnectionFactory connectionFactory, JmsMessageListener jmsMessageListener){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("IN");
        container.setMessageListener(jmsMessageListener);
        return container;
    }
}
