package xyz.xnmq.inf.users.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.xnmq.inf.users.dto.UsersDto;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
@Repository
public interface UsersMapper {
    public List<UsersDto> findList();
}
