package xyz.xnmq.controller.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xnmq.inf.video.BgmService;
import xyz.xnmq.json.Json;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
@RestController
@RequestMapping("bgm")
public class BgmController {
    @Autowired
    private BgmService bgmService;

    @RequestMapping("findList")
    public Json findList(){
        return Json.success(bgmService.findList());
    }
}
