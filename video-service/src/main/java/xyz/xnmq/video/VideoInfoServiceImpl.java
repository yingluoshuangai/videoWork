package xyz.xnmq.video;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.entity.video.VideoInfo;
import xyz.xnmq.inf.video.VideoInfoService;
import xyz.xnmq.inf.video.dao.VideoInfoDao;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.inf.video.mapper.VideoInfoMapper;
import xyz.xnmq.json.Json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    @Value("${file.fileSpace}")
    private String fileSpace;

    /**
     * 视频上传
     */
    @Override
    public Json uploadVideo(VideoInfoDto dto) throws Exception{

       // String fileSpace = "D://material/video_space";//文件保存的命名空间
        String uploadPathDB = "/" + dto.getUserId() + "/video";//保存到数据库的相对路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        MultipartFile file = dto.getFile();

        //上传视频
        try {
            String fileName = file.getOriginalFilename();//获得文件名
            if(StringUtils.isNotEmpty(fileName)){
                String finalVideoPath = fileSpace + uploadPathDB + "/" +   fileName;//文件上传的最终路径
                uploadPathDB = uploadPathDB + "/" + fileName;//保存到数据库的路径

                File outFile = new File(finalVideoPath);
                if(!outFile.getParentFile().exists()){//父文件不存在
                    outFile.getParentFile().mkdir();//创建父文件目录
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Json.error("视频上传失败");
        } finally {
            if(fileOutputStream != null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        //保存视频
        VideoInfo videoInfo = new VideoInfo();
        BeanUtils.copyProperties(dto, videoInfo);
        videoInfo.setVideoPath(uploadPathDB);
        videoInfoDao.save(videoInfo);

        return Json.success();

    }
}
