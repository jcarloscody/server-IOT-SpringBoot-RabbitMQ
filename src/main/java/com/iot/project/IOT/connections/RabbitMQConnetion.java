package com.iot.project.IOT.connections;

import com.iot.project.IOT.consta.RabbitMQConst;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;



@Component
public class RabbitMQConnetion {
    private  static  final String NAME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnetion( AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQueue){
        return  new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directExchange(){
        return  new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relation(Queue queue, DirectExchange directExchange){
        return  new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add(){
        Queue queueLight =  queue(RabbitMQConst.QUEUE_LIGHT);

        DirectExchange change = directExchange();

        Binding bing = relation(queueLight, change);

        amqpAdmin.declareQueue(queueLight);
        amqpAdmin.declareExchange(change);;
        amqpAdmin.declareBinding(bing);

    }
}
