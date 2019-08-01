package xyz.xnmq.entity.video;

import xyz.xnmq.annototion.Clazz;
import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
@Entity
@Table(name = "video_info")
@Clazz("视频信息表")
public class VideoInfo extends AuditAuto{
    @Filed("发布者id")
    private Long userId;
    @Filed("背景音乐id")
    private Long audioId;
    @Filed("视频标题")
    private String videoTitle;
    @Filed("视频描述")
    private String videoDesc;
    @Filed("地址")
    private String videoPath;
    @Filed("视频长度 单位秒")
    private Double videoDuration;
    @Filed("视频高度")
    private Long videoHeight;
    @Filed("视频宽度")
    private Long videoWidth;
    @Filed("封面地址")
    private String coverPath;
    @Filed("喜欢数")
    private Long linkCounts;
    @Filed("状态 0 正常 1 禁播")
    private int status;
    @Filed("是否禁用 0 使用 1 禁用")
    private int disabled = 0;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Double getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Double videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Long getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Long videoHeight) {
        this.videoHeight = videoHeight;
    }

    public Long getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Long videoWidth) {
        this.videoWidth = videoWidth;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Long getLinkCounts() {
        return linkCounts;
    }

    public void setLinkCounts(Long linkCounts) {
        this.linkCounts = linkCounts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
