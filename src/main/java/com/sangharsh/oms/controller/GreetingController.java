package com.sangharsh.oms.controller;

import com.sangharsh.oms.dto.HelloMessage;
import com.sangharsh.oms.dto.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/97769fc3-9fd9-47ee-b9ba-7ed52aa2a7b0")
    public Greeting send(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
