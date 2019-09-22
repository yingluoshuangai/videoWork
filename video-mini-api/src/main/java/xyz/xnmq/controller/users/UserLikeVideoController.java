package xyz.xnmq.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xnmq.entity.users.UserLikeVideo;
import xyz.xnmq.inf.users.UserLikeVideoService;
import xyz.xnmq.inf.users.dto.UserLikeVideoDto;
import xyz.xnmq.json.Json;

/**
 * @Author: by xnmq
 * @Data: 2019/9/22
 * @Description:
 */
@RestController
@RequestMapping("userLikeVideo")
public class UserLikeVideoController {
    @Autowired
    private UserLikeVideoService userLikeVideoService;

    /**
     * 喜欢视频
     * @param dto
     * @return
     */
    @RequestMapping("likeVideo")
    public Json likeVideo(UserLikeVideoDto dto){
        userLikeVideoService.likeVideo(dto);
        return Json.success();
    }

    /**
     * 取消喜欢视频
     * @param dto
     * @return
     */
    @RequestMapping("unlikeVideo")
    public Json unlikeVideo(UserLikeVideoDto dto){
        userLikeVideoService.unlikeVideo(dto);
        return Json.success();
    }

    /**
     * 判断用户是否喜欢该视频 1 是 0 否
     * @param dto
     * @return
     */
    @RequestMapping("isLikeVideo")
    public Json isLikeVideo(UserLikeVideoDto dto){
        return Json.success(userLikeVideoService.isLikeVideo(dto));
    }
}
