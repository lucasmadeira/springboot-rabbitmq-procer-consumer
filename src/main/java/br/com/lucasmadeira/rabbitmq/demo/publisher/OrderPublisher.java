package br.com.lucasmadeira.rabbitmq.demo.publisher;

import br.com.lucasmadeira.rabbitmq.demo.config.MessagingConfig;
import br.com.lucasmadeira.rabbitmq.demo.dto.Order;
import br.com.lucasmadeira.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order,"PROCESS","order placed succesfully in"+restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Sucess";
    }
}
