package xyz.xnmq.controller.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xnmq.inf.video.VideoInfoService;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
@RestController
@RequestMapping("video")
public class VideoInfoController {
    @Autowired
    private VideoInfoService videoInfoService;


}
