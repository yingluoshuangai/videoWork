package xyz.xnmq.inf.users.dto;

import xyz.xnmq.entity.users.UserSearchRecord;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description:
 */
public class UserSearchRecordDto {
    UserSearchRecord userSearchRecord = new UserSearchRecord();

    public Long getUserId() {
        return userSearchRecord.getUserId();
    }

    public void setUserId(Long userId) {
        userSearchRecord.setUserId(userId);
    }

    public String getContent() {
        return userSearchRecord.getContent();
    }

    public void setContent(String content) {
        userSearchRecord.setContent(content);
    }

    public int getDisabled() {
        return userSearchRecord.getDisabled();
    }

    public void setDisabled(int disabled) {
        userSearchRecord.setDisabled(disabled);
    }

    public Long getId() {
        return userSearchRecord.getId();
    }

    public void setId(Long id) {
        userSearchRecord.setId(id);
    }
}
