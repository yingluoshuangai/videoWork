package xyz.xnmq.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xnmq.entity.users.UserFans;
import xyz.xnmq.entity.users.Users;
import xyz.xnmq.inf.users.UserFansService;
import xyz.xnmq.inf.users.dao.UserFansDao;
import xyz.xnmq.inf.users.dao.UsersDao;
import xyz.xnmq.inf.users.dto.UserFansDto;
import xyz.xnmq.inf.users.mapper.UserFansMapper;

import java.util.Objects;

/**
 * @Author: by xnmq
 * @Data: 2019/10/1
 * @Description:
 */
@Service
public class UserFansServiceImpl implements UserFansService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserFansDao userFansDao;
    @Autowired
    private UserFansMapper userFansMapper;
    @Autowired
    private UsersDao usersDao;

    /**
     * 关注
     * @param dto
     */
    @Override
    public void attention(UserFansDto dto){
        //判断是否已经关注
        UserFans preUserFans = userFansDao.findByUserIdAndFansId(dto.getUserId(), dto.getFansId());
        if(Objects.isNull(preUserFans)){
            //新增用户粉丝关系
            UserFans userFans = new UserFans();
            userFans.setUserId(dto.getUserId());
            userFans.setFansId(dto.getFansId());
            userFansDao.save(userFans);

            //用户的粉丝数 加一
            Users users = usersDao.findOne(dto.getUserId());
            Long fansCount = Objects.isNull(users.getFansCounts()) ? 1 : users.getFansCounts() + 1;
            users.setFansCounts(fansCount);
            usersDao.save(users);

            //粉丝的关注数 加一
            Users fansUser = usersDao.findOne(dto.getFansId());
            Long followCount = Objects.isNull(fansUser.getFollowCounts()) ? 1 : fansUser.getFollowCounts() + 1;
            fansUser.setFollowCounts(followCount);
            usersDao.save(fansUser);
        }else{
            logger.info("***** 用户正在关注 无法新增关注*****");
        }
    }

    /**
     * 取消关注
     * @param dto
     */
    @Override
    public void noAttention(UserFansDto dto){
        //判断是否关注
        UserFans preUserFans = userFansDao.findByUserIdAndFansId(dto.getUserId(), dto.getFansId());
        if(Objects.isNull(preUserFans)){
            logger.info("***** 用户没用关注 无法取消关注 *****");
        }else{
            //删除用户粉丝关系
            userFansDao.delete(preUserFans);

            //用户的粉丝数 减一
            Users users = usersDao.findOne(dto.getUserId());
            Long fansCount = Objects.isNull(users.getFansCounts()) ? 0 : users.getFansCounts() - 1;
            users.setFansCounts(fansCount);
            usersDao.save(users);

            //粉丝的关注数 减一
            Users fansUser = usersDao.findOne(dto.getFansId());
            Long followCount = Objects.isNull(fansUser.getFollowCounts()) ? 0 : fansUser.getFollowCounts() - 1;
            fansUser.setFollowCounts(followCount);
            usersDao.save(fansUser);
        }
    }

    /**
     * 是否关注该用户 1 是 0 否
     * @param dto
     * @return
     */
    @Override
    public String isFollow(UserFansDto dto){
        UserFans userFans = userFansDao.findByUserIdAndFansId(dto.getUserId(), dto.getFansId());
        if(Objects.isNull(userFans)){
            return "0";
        }else{
            return "1";
        }
    }
}
