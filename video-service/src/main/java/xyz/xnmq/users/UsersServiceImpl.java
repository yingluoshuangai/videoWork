package xyz.xnmq.users;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.entity.users.Users;
import xyz.xnmq.inf.users.UsersService;
import xyz.xnmq.inf.users.dao.UsersDao;
import xyz.xnmq.inf.users.dto.UsersDto;
import xyz.xnmq.inf.users.mapper.UsersMapper;
import xyz.xnmq.json.Json;
import xyz.xnmq.md5.MD5s;
import xyz.xnmq.redis.RedisOperator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Author: by xnmq
 * @Data: 2019/7/9
 * @Description:
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RedisOperator redis;

    private static final String USER_REDIS_SESSION = "user-redis-session";

    @Override
    public List<Users> findList() {
        return usersDao.findAll();
    }

    @Override
    public List<UsersDto> findDtoList() {
        return usersMapper.findList();
    }

    /**
     * 注册
     *
     * @param dto
     * @return
     */
    @Override
    public Json regist(UsersDto dto) {
        if (StringUtils.isEmpty(dto.getUsername()) || StringUtils.isEmpty(dto.getPassword())) {
            return Json.error("用户名或密码不能为空");
        }
        Users isUsers = usersDao.findByUsername(dto.getUsername());
        if (Objects.nonNull(isUsers)) {
            return Json.error("用户名已经存在");
        }

        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPassword(MD5s.string2MD5(dto.getPassword()));
        users.setFansCounts(0L);
        users.setReceiveLikeCounts(0L);
        users.setFollowCounts(0L);
        users = usersDao.save(users);
        users.setPassword("");//将密码置为空

        dto = setRedisUserSession(users);

        return Json.success(dto);
    }

    /**
     * 登陆
     *
     * @param dto
     * @return
     */
    @Override
    public Json login(UsersDto dto) {
        if (StringUtils.isEmpty(dto.getUsername()) || StringUtils.isEmpty(dto.getPassword())) {
            return Json.error("用户名或密码不能为空");
        }
        Users users = usersDao.findByUsernameAndPassword(dto.getUsername(), MD5s.string2MD5(dto.getPassword()));
        if (Objects.isNull(users)) {
            return Json.error("用户名或密码不正确");
        }

        users.setPassword("");//将密码置为空
        dto = setRedisUserSession(users);
        return Json.success(dto);
    }

    /**
     * 用户注销
     *
     * @param userId
     * @return
     */
    @Override
    public Json logout(String userId) {
        try {
            redis.del(USER_REDIS_SESSION + ":" + userId);
            return Json.success();
        } catch (Exception e) {
            return Json.error("注销失败");
        }
    }

    /**
     * 将用户id保存到redis
     *
     * @param users
     * @return
     */
    private UsersDto setRedisUserSession(Users users) {

        UsersDto dto = new UsersDto();
        BeanUtils.copyProperties(users, dto);
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + users.getId(), uniqueToken, 60 * 30);//过期时间为30min
        dto.setUserToken(uniqueToken);
        return dto;
    }

    /**
     * 上传头像
     *
     * @param userId
     * @param files
     * @return
     * @throws IOException
     */
    @Override
    public Json uploadFace(String userId, MultipartFile[] files) {
        //文件保存规则 : 头像--用户id/face/图片  视频-- 用户id/voide/视频
        if (Objects.isNull(files) || files.length <= 0) {
            return Json.error("文件不存在");
        }
        if(StringUtils.isEmpty(userId)){
            return Json.error("用户id不存在");
        }
        //文件保存的命名空间
        String fileSpace = "D:/material/video_space";
        //保存到数据库的相对路径
        String uploadPathDB = "/private/" + userId + "/face";//
        FileOutputStream fileOutputStream = null;//文件输出流
        InputStream inputStream = null;//输入流
        try {

            String fileName = files[0].getOriginalFilename();//获得文件名
            if (StringUtils.isNotEmpty(fileName)) {
                //文件上传的最终保存路径
                String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                //保存到数据库的最终路径
                uploadPathDB = uploadPathDB + "/" + fileName;

                File outFile = new File(finalFacePath);
                if (!outFile.getParentFile().exists()) {//父文件夹不存在
                    //创建父文件夹
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = files[0].getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);

                //保存用户头像地址
                Users users = usersDao.findOne(Long.valueOf(userId));
                users.setFaceImage(uploadPathDB);
                usersDao.save(users);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return Json.error("文件上传失败");
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Json.success(uploadPathDB);//返回头像路径
    }

    /**
     * 查询用户信息
     * @return
     */
    @Override
    public Json findOne(String userId){
        UsersDto dto = new UsersDto();
        Users users = usersDao.findOne(Long.valueOf(userId));
        users.setPassword("");//密码置为空
        BeanUtils.copyProperties(users, dto);
        return Json.success(dto);
    }


}
