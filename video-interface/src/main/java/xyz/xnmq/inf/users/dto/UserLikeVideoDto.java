package xyz.xnmq.inf.users.dto;

import xyz.xnmq.entity.users.UserLikeVideo;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description:
 */
public class UserLikeVideoDto {
    private UserLikeVideo userLikeVideo = new UserLikeVideo();

    public Long getUserId() {
        return userLikeVideo.getUserId();
    }

    public void setUserId(Long userId) {
        userLikeVideo.setUserId(userId);
    }

    public Long getVideoId() {
        return userLikeVideo.getVideoId();
    }

    public void setVideoId(Long videoId) {
        userLikeVideo.setVideoId(videoId);
    }

    public Long getId() {
        return userLikeVideo.getId();
    }

    public void setId(Long id) {
        userLikeVideo.setId(id);
    }
}
