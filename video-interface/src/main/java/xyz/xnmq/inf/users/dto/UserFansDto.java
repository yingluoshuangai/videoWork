package xyz.xnmq.inf.users.dto;

import xyz.xnmq.entity.users.UserFans;

/**
 * @Author: by xnmq
 * @Data: 2019/10/1
 * @Description:
 */
public class UserFansDto {
    private UserFans userFans = new UserFans();

    public Long getUserId() {
        return userFans.getUserId();
    }

    public void setUserId(Long userId) {
        userFans.setUserId(userId);
    }

    public Long getFansId() {
        return userFans.getFansId();
    }

    public void setFansId(Long fansId) {
        userFans.setFansId(fansId);
    }

    public Long getId() {
        return userFans.getId();
    }

    public void setId(Long id) {
        userFans.setId(id);
    }
}
