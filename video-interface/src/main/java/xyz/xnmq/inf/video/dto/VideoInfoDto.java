package xyz.xnmq.inf.video.dto;

import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.video.VideoInfo;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
public class VideoInfoDto {
    VideoInfo videoInfo = new VideoInfo();

    @Filed("视频文件")
    private MultipartFile file;//视频文件

    public Long getVideoSize() {
        return videoInfo.getVideoSize();
    }

    public void setVideoSize(Long videoSize) {
        videoInfo.setVideoSize(videoSize);
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getUserId() {
        return videoInfo.getUserId();
    }

    public void setUserId(Long userId) {
        videoInfo.setUserId(userId);
    }

    public Long getAudioId() {
        return videoInfo.getAudioId();
    }

    public void setAudioId(Long audioId) {
        videoInfo.setAudioId(audioId);
    }

    public String getVideoTitle() {
        return videoInfo.getVideoTitle();
    }

    public void setVideoTitle(String videoTitle) {
        videoInfo.setVideoTitle(videoTitle);
    }

    public String getVideoDesc() {
        return videoInfo.getVideoDesc();
    }

    public void setVideoDesc(String videoDesc) {
        videoInfo.setVideoDesc(videoDesc);
    }

    public String getVideoPath() {
        return videoInfo.getVideoPath();
    }

    public void setVideoPath(String videoPath) {
        videoInfo.setVideoPath(videoPath);
    }

    public Double getVideoDuration() {
        return videoInfo.getVideoDuration();
    }

    public void setVideoDuration(Double videoDuration) {
        videoInfo.setVideoDuration(videoDuration);
    }

    public Long getVideoHeight() {
        return videoInfo.getVideoHeight();
    }

    public void setVideoHeight(Long videoHeight) {
        videoInfo.setVideoHeight(videoHeight);
    }

    public Long getVideoWidth() {
        return videoInfo.getVideoWidth();
    }

    public void setVideoWidth(Long videoWidth) {
        videoInfo.setVideoWidth(videoWidth);
    }

    public String getCoverPath() {
        return videoInfo.getCoverPath();
    }

    public void setCoverPath(String coverPath) {
        videoInfo.setCoverPath(coverPath);
    }

    public Long getLinkCounts() {
        return videoInfo.getLinkCounts();
    }

    public void setLinkCounts(Long linkCounts) {
        videoInfo.setLinkCounts(linkCounts);
    }

    public int getStatus() {
        return videoInfo.getStatus();
    }

    public void setStatus(int status) {
        videoInfo.setStatus(status);
    }

    public int getDisabled() {
        return videoInfo.getDisabled();
    }

    public void setDisabled(int disabled) {
        videoInfo.setDisabled(disabled);
    }

    public Long getId() {
        return videoInfo.getId();
    }

    public void setId(Long id) {
        videoInfo.setId(id);
    }
}
