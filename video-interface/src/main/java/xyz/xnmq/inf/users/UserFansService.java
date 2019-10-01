package xyz.xnmq.inf.users;

import xyz.xnmq.inf.users.dto.UserFansDto;

/**
 * @Author: by xnmq
 * @Data: 2019/10/1
 * @Description:
 */
public interface UserFansService {
    void attention(UserFansDto dto);

    void noAttention(UserFansDto dto);

    String isFollow(UserFansDto dto);
}
