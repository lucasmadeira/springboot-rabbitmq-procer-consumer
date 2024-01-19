package br.com.lucasmadeira.rabbitmq.demo.consumer;

import br.com.lucasmadeira.rabbitmq.demo.config.MessagingConfig;
import br.com.lucasmadeira.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue: "+ orderStatus);
    }
}
