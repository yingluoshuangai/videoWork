package xyz.xnmq.controller.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.xnmq.entity.users.Users;
import xyz.xnmq.inf.users.UsersService;
import xyz.xnmq.inf.users.dao.UsersDao;
import xyz.xnmq.inf.users.dto.UsersDto;
import xyz.xnmq.json.Json;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
@RestController
@Api(value = "用户注册登陆接口", tags = {"注册和登陆的controller"})
public class LoginController {
    @Autowired
    private UsersService usersService;


    @RequestMapping("findList")
    public Json findList(){
        List<Users> usersList = usersService.findList();
        List<UsersDto> usersDtoList = usersService.findDtoList();
        return Json.success(usersDtoList);
    }

    /**
     * 用户注册
     * @param dto
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册接口")
    @PostMapping("regist")
    public Json regist(@RequestBody UsersDto dto){
        return usersService.regist(dto);
    }

    /**
     * 用户登陆
     * @param dto
     * @return
     */
    @PostMapping("login")
    public Json login(@RequestBody UsersDto dto){
        return usersService.login(dto);
    }

    /**
     * 用户注销
     * @param userId
     * @return
     */
    @ApiOperation(value = "用户注销",  notes = "用户注销接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @PostMapping("logout")
    public Json logout(String userId){
        if(StringUtils.isEmpty(userId)){
            return Json.error("用户id不能为空");
        }
        return usersService.logout(userId);
    }
}
