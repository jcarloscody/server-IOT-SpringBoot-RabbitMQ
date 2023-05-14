package com.iot.project.IOT.controller;

import com.iot.project.IOT.consta.RabbitMQConst;
import com.iot.project.IOT.dto.LightDTO;
import com.iot.project.IOT.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "light")
public class LightController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity changeLight(@RequestBody LightDTO lightDTO){
        rabbitMQService.sendMessage(RabbitMQConst.QUEUE_LIGHT, lightDTO);
        return  new ResponseEntity(HttpStatus.OK);
    }
}
