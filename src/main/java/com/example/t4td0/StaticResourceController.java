package com.example.t4td0;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class StaticResourceController {
    @GetMapping("/css/{fileName:.+}")
    @ResponseBody
    public Resource serveCSS(@PathVariable String fileName) {
        Resource resource = new ClassPathResource("static/css/" + fileName);
        return resource;
    }
}