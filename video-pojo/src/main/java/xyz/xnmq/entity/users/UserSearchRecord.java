package xyz.xnmq.entity.users;

import xyz.xnmq.annototion.Filed;
import xyz.xnmq.entity.AuditAuto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description: 用户搜素记录表
 */
@Entity
@Table(name = "user_search_record")
public class UserSearchRecord extends AuditAuto{
    @Filed("用户id")
    private Long userId;
    @Filed("内容")
    private String content;
    @Filed("是否禁用 0使用 1禁用")
    private int disabled;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
