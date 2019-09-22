package xyz.xnmq.inf.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.users.UserLikeVideo;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description:
 */
public interface UserLikeVideoDao extends JpaRepository<UserLikeVideo, Long>, JpaSpecificationExecutor<UserLikeVideo>{

    List<UserLikeVideo> findByUserIdAndVideoId(Long userId, Long videoId);
}
