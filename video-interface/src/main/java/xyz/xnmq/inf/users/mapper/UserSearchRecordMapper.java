package xyz.xnmq.inf.users.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description:
 */
@Repository
public interface UserSearchRecordMapper {
    List<String> findHotContent(@Param("number") long number);
}
