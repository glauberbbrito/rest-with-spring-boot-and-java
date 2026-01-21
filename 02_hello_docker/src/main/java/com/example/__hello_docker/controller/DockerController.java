package com.example.__hello_docker.controller;

import com.example.__hello_docker.enviroment.InstanceInformationService;
import com.example.__hello_docker.model.HelloDocker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.xpath.XPath;
import java.nio.file.Path;

@RestController
public class DockerController {

    Logger logger = LoggerFactory.getLogger(DockerController.class);

    @Autowired
    private InstanceInformationService service;

    @GetMapping(path = "/")
    public String imUpAndRunning(){
        logger.info("Endpoint / is called!!!");
        return "healthy:true";
    }

    @RequestMapping("/hello-docker")
    public HelloDocker greeting(){
        logger.info("Endpoint /hello-docker is called!!!");

        return new HelloDocker("Hello Docker V1!", service.retrieveInstanceInfo());
    }

}
