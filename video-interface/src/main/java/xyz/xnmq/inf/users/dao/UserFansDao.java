package xyz.xnmq.inf.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.users.UserFans;

/**
 * @Author: by xnmq
 * @Data: 2019/10/1
 * @Description:
 */
public interface UserFansDao extends JpaRepository<UserFans,Long>, JpaSpecificationExecutor<UserFans>{
    UserFans findByUserIdAndFansId(Long userId, Long fansId);
}
