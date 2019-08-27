package xyz.xnmq.inf.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.users.UserSearchRecord;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description:
 */
public interface UserSearchRecordDao extends JpaRepository<UserSearchRecord, Long>, JpaSpecificationExecutor<UserSearchRecord> {
}
