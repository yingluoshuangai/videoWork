package xyz.xnmq.entity.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
@Entity
@Table(name = "users")
public class Users extends AuditAuto{

    @Filed("用户名")
    private String username;

    @Filed("密码")
    private String password;

    @Filed("用户头像")
    private String faceImage;

    @Filed("昵称")
    private String nickname;

    @Filed("粉丝数")
    private Long fansCounts;

    @Filed("关注数")
    private Long followCounts;

    @Filed("获赞数")
    private Long receiveLikeCounts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getFansCounts() {
        return fansCounts;
    }

    public void setFansCounts(Long fansCounts) {
        this.fansCounts = fansCounts;
    }

    public Long getFollowCounts() {
        return followCounts;
    }

    public void setFollowCounts(Long followCounts) {
        this.followCounts = followCounts;
    }

    public Long getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    public void setReceiveLikeCounts(Long receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}
