package com.iot.project.IOT.consumer;

import com.iot.project.IOT.consta.RabbitMQConst;
import com.iot.project.IOT.dto.LightDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LightConsumer {

    @RabbitListener(queues = RabbitMQConst.QUEUE_LIGHT)
    public void consumidor(LightDTO lightDTO){
        System.out.println("-------->>>   " + lightDTO.on);
    }
}
