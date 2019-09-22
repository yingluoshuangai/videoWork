package xyz.xnmq.entity.users;

import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description: 用户喜欢视频表
 */
@Entity
@Table(name = "user_like_video")
public class UserLikeVideo extends AuditAuto{
    @Filed("用户id")
    private Long userId;
    @Filed("视频id")
    private Long videoId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
