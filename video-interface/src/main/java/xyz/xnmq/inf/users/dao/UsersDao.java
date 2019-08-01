package xyz.xnmq.inf.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.users.Users;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
public interface UsersDao extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users>{
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Users findByUsername(String username);
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param passward
     * @return
     */
    Users findByUsernameAndPassword(String username, String passward);
}
