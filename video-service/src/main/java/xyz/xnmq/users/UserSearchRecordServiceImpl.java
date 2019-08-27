package xyz.xnmq.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xnmq.inf.users.UserSearchRecordService;
import xyz.xnmq.inf.users.dao.UserSearchRecordDao;
import xyz.xnmq.inf.users.mapper.UserSearchRecordMapper;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/8/25
 * @Description:
 */
@Service
public class UserSearchRecordServiceImpl  implements UserSearchRecordService{

    @Autowired
    private UserSearchRecordDao userSearchRecordDao;
    @Autowired
    private UserSearchRecordMapper userSearchRecordMapper;

    /**
     * 查询热搜词
     * @return
     */
    @Override
    public List<String> findHotContent(){
        List<String> contentList = userSearchRecordMapper.findHotContent(5L);
        return contentList;
    }
}
