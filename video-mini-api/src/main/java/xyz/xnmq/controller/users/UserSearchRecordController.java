package xyz.xnmq.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xnmq.inf.users.UserSearchRecordService;
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
}
