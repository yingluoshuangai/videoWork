package xyz.xnmq.inf.video;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.json.Json;
import xyz.xnmq.mybatis.Page;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
public interface VideoInfoService {

    Json uploadVideo(VideoInfoDto dto) throws Exception;

    PageInfo findList(VideoInfoDto dto, Page page);

    PageInfo findByDesc(VideoInfoDto dto, Page page);
}
