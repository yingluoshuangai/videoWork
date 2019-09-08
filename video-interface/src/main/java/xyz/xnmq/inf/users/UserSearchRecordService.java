package xyz.xnmq.inf.users;

import xyz.xnmq.inf.users.dto.UserSearchRecordDto;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description:
 */
public interface UserSearchRecordService {
    List<String> findHotContent();

    void saveRecord(UserSearchRecordDto dto);
}
