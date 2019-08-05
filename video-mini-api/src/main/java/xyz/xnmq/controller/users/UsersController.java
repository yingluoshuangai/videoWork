package xyz.xnmq.controller.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.users.UsersService;
import xyz.xnmq.json.Json;
import java.io.IOException;

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

    @RequestMapping("uploadFace")
    @ApiOperation(value = "头像上传")
    public Json uploadFace(String userId, @RequestParam("files") MultipartFile[] files) throws IOException {
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
}
