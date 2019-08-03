package xyz.xnmq.inf.video;

import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.json.Json;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
public interface VideoInfoService {

    Json uploadVideo(VideoInfoDto dto) throws Exception;
}
