package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping(value="/hchk")
    public String healthCheck()
    {
        logger.info(this.getClass().getSimpleName());
        return "Ping from api!";
    }
}
