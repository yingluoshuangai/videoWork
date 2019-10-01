package xyz.xnmq.entity.users;

import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/10/1
 * @Description: 用户粉丝表
 */
@Entity
@Table(name = "user_fans")
public class UserFans extends AuditAuto{
    @Filed("用户id")
    private Long userId;
    @Filed("粉丝id")
    private Long fansId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFansId() {
        return fansId;
    }

    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }
}
