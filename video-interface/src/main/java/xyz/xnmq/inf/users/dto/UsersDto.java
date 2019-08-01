package xyz.xnmq.inf.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.xnmq.annototion.Clazz;
import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.users.Users;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
@Clazz("用户DTO")
@ApiModel(value = "用户对象", description = "这是一个用户对象")
public class UsersDto {
    Users users = new Users();

    @Filed("用户Token")
    private String userToken;


    private Date userDate;

    public Date getUserDate() {
        return userDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @ApiModelProperty(value = "用户名", name = "username", required = true)
    public String getUsername() {
        return users.getUsername();
    }

    public void setUsername(String username) {
        users.setUsername(username);
    }

    @ApiModelProperty(value = "密码", name = "password", required = true)
    public String getPassword() {
        return users.getPassword();
    }
    public void setPassword(String password) {
        users.setPassword(password);
    }

    public String getFaceImage() {
        return users.getFaceImage();
    }

    public void setFaceImage(String faceImage) {
        users.setFaceImage(faceImage);
    }

    public String getNickname() {
        return users.getNickname();
    }

    public void setNickname(String nickname) {
        users.setNickname(nickname);
    }

    public Long getFansCounts() {
        return users.getFansCounts();
    }

    public void setFansCounts(Long fansCounts) {
        users.setFansCounts(fansCounts);
    }

    public Long getFollowCounts() {
        return users.getFollowCounts();
    }

    public void setFollowCounts(Long followCounts) {
        users.setFollowCounts(followCounts);
    }

    public Long getReceiveLikeCounts() {
        return users.getReceiveLikeCounts();
    }

    public void setReceiveLikeCounts(Long receiveLikeCounts) {
        users.setReceiveLikeCounts(receiveLikeCounts);
    }

    public Long getId() {
        return users.getId();
    }

    public void setId(Long id) {
        users.setId(id);
    }


}
