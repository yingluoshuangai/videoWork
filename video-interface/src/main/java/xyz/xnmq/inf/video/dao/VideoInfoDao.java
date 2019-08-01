package xyz.xnmq.inf.video.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.video.VideoInfo;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
public interface VideoInfoDao extends JpaRepository<VideoInfo, Long>, JpaSpecificationExecutor<VideoInfo>{
}
