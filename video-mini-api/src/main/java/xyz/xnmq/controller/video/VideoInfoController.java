package xyz.xnmq.controller.video;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.video.VideoInfoService;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.json.Json;

import java.util.Objects;

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

    @RequestMapping("uploadVideo")
    public Json uploadVideo(VideoInfoDto dto) throws Exception{
        if(dto.getUserId() == null){
            return Json.error("用户id不能为空");
        }
        if(Objects.isNull(dto.getFile()) || dto.getFile().getSize() <= 0){
            return Json.error("文件为空");
        }
        return videoInfoService.uploadVideo(dto);

    }


}
