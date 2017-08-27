package com.example.demo;

import com.example.interceptors.audit.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card/v1")
public class CardController {
    private static final Logger logger = LogManager.getLogger(HealthController.class);

    @GetMapping(value="/chk")
    public String checkCardType(String cardNo)
    {
        logger.info(this.getClass().getSimpleName());


        return "Ping from api!" + cardNo;
    }
}
