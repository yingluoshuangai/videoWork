package xyz.xnmq.inf.users;

import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.entity.users.Users;
import xyz.xnmq.inf.users.dto.UsersDto;
import xyz.xnmq.json.Json;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
public interface UsersService {
    List<Users> findList();

    List<UsersDto> findDtoList();

    Json regist(UsersDto dto);

    Json login(UsersDto dto);

    Json logout(String userId);

    Json uploadFace(String userId, MultipartFile[] files);

    Json findOne(String userId);
}
