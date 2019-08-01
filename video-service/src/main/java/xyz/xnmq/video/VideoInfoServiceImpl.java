package xyz.xnmq.video;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.inf.video.VideoInfoService;
import xyz.xnmq.inf.video.dao.VideoInfoDao;
import xyz.xnmq.inf.video.mapper.VideoInfoMapper;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Author: by xnmq
 * @Data: 2019/8/1
 * @Description:
 */
@Service
public class VideoInfoServiceImpl implements VideoInfoService{
    @Autowired
    private VideoInfoDao videoInfoDao;
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    public void uploadVideo(Long userId, Double videoDuration, Long videoHeight, Long videoWidth,
                            Long videoSize, MultipartFile file){

        String fileSpace = "D://material/video_space";//文件保存的命名空间
        String uploadPathDB = "/private/" + userId + "/video";//保存到数据库的相对路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        String fileName = file.getOriginalFilename();//获得文件名
        if(StringUtils.isNotEmpty(fileName)){
            String finalVideoPath = fileSpace + uploadPathDB + "/" +   fileName;
        }

    }
}
