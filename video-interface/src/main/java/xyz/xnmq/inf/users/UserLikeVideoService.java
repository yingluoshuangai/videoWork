package xyz.xnmq.inf.users;

import xyz.xnmq.inf.users.dto.UserLikeVideoDto;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description:
 */
public interface UserLikeVideoService {
    void likeVideo(UserLikeVideoDto dto);

    void unlikeVideo(UserLikeVideoDto dto);

    String isLikeVideo(UserLikeVideoDto dto);
}
