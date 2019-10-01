package xyz.xnmq.controller.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.users.UserFansService;
import xyz.xnmq.inf.users.UsersService;
import xyz.xnmq.inf.users.dto.UserFansDto;
import xyz.xnmq.json.Json;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: by xnmq
 * @Data: 2019/7/23
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UserFansService userFansService;

    @RequestMapping("uploadFace")
    @ApiOperation(value = "头像上传")
    public Json uploadFace(String userId, @RequestParam("files") MultipartFile[] files) throws IOException {
        if (Objects.isNull(files) || files.length <= 0) {
            return Json.error("文件不存在");
        }
        if(StringUtils.isEmpty(userId)){
            return Json.error("用户id不存在");
        }
        return usersService.uploadFace(userId, files);
    }

    @RequestMapping("findOne")
    @ApiOperation(value = "查询用户")
    public Json findOne(String userId){
        if(StringUtils.isEmpty(userId)){
            return Json.error("用户id为空");
        }
        return usersService.findOne(userId);
    }

    /**
     * 用户关注
     * @param dto
     * @return
     */
    @RequestMapping("attention")
    public Json attention(UserFansDto dto){
        if(Objects.isNull(dto.getUserId()) || Objects.isNull(dto.getFansId())){
            return Json.error("信息不正确");
        }
        userFansService.attention(dto);
        return Json.success("用户关注成功");
    }

    /**
     * 用户取消关注
     * @param dto
     * @return
     */
    @RequestMapping("noAttention")
    public Json noAttention(UserFansDto dto){
        if(Objects.isNull(dto.getUserId()) || Objects.isNull(dto.getFansId())){
            return Json.error("信息不正确");
        }
        userFansService.noAttention(dto);
        return Json.success("用户取消关注成功");
    }

    /**
     * 是否关注该用户 1 是 0 否
     * @param dto
     * @return
     */
    @RequestMapping("isFollow")
    public Json isFollow(UserFansDto dto){
        if(Objects.isNull(dto.getUserId()) || Objects.isNull(dto.getFansId())){
            return Json.error("信息不正确");
        }
        return Json.success(userFansService.isFollow(dto));
    }
}
