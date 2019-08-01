package xyz.xnmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * _________________________
 * |   |     |     |    | |  \
 * |___|_____|_____|____|_|___\
 * |                    | |    \
 * `--(o)(o)--------------(o)--'
 * ```````````````````````````````
 *
 * @Description:
 * @Data ${DATA}
 */
@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    public String Hello(){
        return "hello world";
    }
}
