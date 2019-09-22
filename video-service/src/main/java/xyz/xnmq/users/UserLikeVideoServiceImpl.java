package xyz.xnmq.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.xnmq.entity.users.UserLikeVideo;
import xyz.xnmq.entity.users.Users;
import xyz.xnmq.entity.video.VideoInfo;
import xyz.xnmq.inf.users.UserLikeVideoService;
import xyz.xnmq.inf.users.dao.UserLikeVideoDao;
import xyz.xnmq.inf.users.dao.UsersDao;
import xyz.xnmq.inf.users.dto.UserLikeVideoDto;
import xyz.xnmq.inf.users.mapper.UserLikeVideoMapper;
import xyz.xnmq.inf.video.dao.VideoInfoDao;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description: 用户喜欢业务类
 */
@Service
public class UserLikeVideoServiceImpl implements UserLikeVideoService{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserLikeVideoMapper userLikeVideoMapper;
    @Autowired
    private UserLikeVideoDao userLikeVideoDao;
    @Autowired
    private VideoInfoDao videoInfoDao;
    @Autowired
    private UsersDao usersDao;

    @Override
    public void likeVideo(UserLikeVideoDto dto){
        List<UserLikeVideo> likeVideoList = userLikeVideoDao.findByUserIdAndVideoId(dto.getUserId(), dto.getVideoId());
        if(CollectionUtils.isEmpty(likeVideoList)){
            UserLikeVideo userLikeVideo = new UserLikeVideo();
            userLikeVideo.setUserId(dto.getUserId());
            userLikeVideo.setVideoId(dto.getVideoId());
            userLikeVideoDao.save(userLikeVideo);

            //视频喜欢数 加一
            VideoInfo videoInfo = videoInfoDao.findOne(dto.getVideoId());
            Long videoLikeCount = Objects.isNull(videoInfo.getLikeCounts()) ? 1 : videoInfo.getLikeCounts() + 1;
            videoInfo.setLikeCounts(videoLikeCount);
            videoInfoDao.save(videoInfo);

            //视频发布者获赞数 加一
            Users users = usersDao.findOne(videoInfo.getUserId());
            Long receiveLikeCount = Objects.isNull(users.getReceiveLikeCounts()) ? 1 : users.getReceiveLikeCounts() + 1;
            users.setReceiveLikeCounts(receiveLikeCount);
            usersDao.save(users);
        }else {
            logger.info("***** 用户已经喜欢过该视频， 无法添加 *****");
        }
    }

    @Override
    public void unlikeVideo(UserLikeVideoDto dto){
        List<UserLikeVideo> likeVideoList = userLikeVideoDao.findByUserIdAndVideoId(dto.getUserId(), dto.getVideoId());
        if(!CollectionUtils.isEmpty(likeVideoList)){
            UserLikeVideo userLikeVideo = likeVideoList.get(0);
            userLikeVideoDao.delete(userLikeVideo);

            //视频喜欢数 减一
            VideoInfo videoInfo = videoInfoDao.findOne(dto.getVideoId());
            Long likeCount = Objects.isNull(videoInfo.getLikeCounts()) ? 0 : videoInfo.getLikeCounts() - 1;
            videoInfo.setLikeCounts(likeCount);
            videoInfoDao.save(videoInfo);

            //视频发布者获赞数 减一
            Users users = usersDao.findOne(videoInfo.getUserId());
            Long receiveLikeCount = Objects.isNull(users.getReceiveLikeCounts()) ? 0 : users.getReceiveLikeCounts() - 1;
            users.setReceiveLikeCounts(receiveLikeCount);
            usersDao.save(users);
        }else{
            logger.info("***** 用户没有喜欢过该视频， 无法删除 *****");
        }
    }

    /**
     * 判断用户是否喜欢该视频 1 是 0 否
     * @param dto
     * @return
     */
    @Override
    public String isLikeVideo(UserLikeVideoDto dto){
        List<UserLikeVideo> likeVideoList = userLikeVideoDao.findByUserIdAndVideoId(dto.getUserId(), dto.getVideoId());
        if(CollectionUtils.isEmpty(likeVideoList)){
            return "0";
        }else {
            return "1";
        }
    }
}
