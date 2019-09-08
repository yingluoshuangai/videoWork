package xyz.xnmq.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xnmq.entity.users.UserSearchRecord;
import xyz.xnmq.inf.users.UserSearchRecordService;
import xyz.xnmq.inf.users.dto.UserSearchRecordDto;
import xyz.xnmq.json.Json;

/**
 * @Author: by xnmq
 * @Data: 2019/8/27
 * @Description:
 */
@RestController
@RequestMapping("searchRecord")
public class UserSearchRecordController {
    @Autowired
    private UserSearchRecordService userSearchRecordService;

    /**
     * 查询 热搜词
     * @return
     */
    @RequestMapping("findHotContent")
    public Json findHotContent(){
        return Json.success(userSearchRecordService.findHotContent());
    }

    /**
     * 保存查询记录
     * @param dto
     * @return
     */
    @RequestMapping("saveRecord")
    public Json saveRecord(UserSearchRecordDto dto){
        userSearchRecordService.saveRecord(dto);
        return Json.success();
    }
}
