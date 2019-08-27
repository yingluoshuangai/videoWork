package xyz.xnmq.inf.video.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.xnmq.inf.video.dto.VideoInfoDto;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
@Repository
public interface VideoInfoMapper {

    List<VideoInfoDto> findList(@Param("dto") VideoInfoDto dto);
}
