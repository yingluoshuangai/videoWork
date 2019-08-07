package xyz.xnmq.video;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.entity.video.Bgm;
import xyz.xnmq.entity.video.VideoInfo;
import xyz.xnmq.ffmpeg.FfmpegUtil;
import xyz.xnmq.inf.video.VideoInfoService;
import xyz.xnmq.inf.video.dao.BgmDao;
import xyz.xnmq.inf.video.dao.VideoInfoDao;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.inf.video.mapper.VideoInfoMapper;
import xyz.xnmq.json.Json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
    @Autowired
    private BgmDao bgmDao;
    @Value("${file.fileSpace}")
    private String fileSpace;
    @Value("${file.bgmPath}")
    private String bgmPath;

    /**
     * 视频上传
     */
    @Override
    public Json uploadVideo(VideoInfoDto dto) throws Exception{

        String uploadPathDB = "/" + dto.getUserId() + "/video/";//保存到数据库的相对路径
        String videoPath = fileSpace + uploadPathDB;//文件上传的磁盘路径
        String finalUploadPathDB = "";//原视频保存到数据库的最终路径
        String finalVideoPath = "";//原视频上传的最终磁盘路径
        String finalMergeUploadPathDB = "";//合成视频保存到数据库的最终路径
        String finalMergeVideoOutputPath = "";//合成视频保存到磁盘的最终路径

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        MultipartFile file = dto.getFile();

        //上传视频
        try {
            String fileName = file.getOriginalFilename();//获得文件名
            if(StringUtils.isNotEmpty(fileName)){

                finalVideoPath = videoPath + fileName;//原文件上传的最终磁盘路径

                File outFile = new File(finalVideoPath);
                if(!outFile.getParentFile().exists()){//父文件不存在
                    outFile.getParentFile().mkdir();//创建父文件目录
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);

                //合并视频
                if(dto.getAudioId() != null){
                    Bgm bgm = bgmDao.findOne(dto.getAudioId());
                    String videoInputPath = finalVideoPath;//视频地址
                    String audioInputPath = bgmPath + bgm.getPath();//音频地址
                    String outPutFileName = "merge_" + bgm.getId() + "_" + UUID.randomUUID().toString() + "_" + fileName;//合成视频的文件名
                    finalMergeUploadPathDB = uploadPathDB + outPutFileName;//合成视频保存到数据库的最终路径
                    finalMergeVideoOutputPath = videoPath + outPutFileName;//合成视频保存到磁盘的最终路径
                    FfmpegUtil ffmpegUtil = new FfmpegUtil("D:/soft/soft3/ffmpeg/bin/ffmpeg.exe");
                    ffmpegUtil.mergeVideoAndAudio(videoInputPath, audioInputPath, dto.getVideoDuration(), finalMergeVideoOutputPath);


                }
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
        videoInfo.setVideoPath(finalMergeUploadPathDB);
        videoInfoDao.save(videoInfo);

        return Json.success();

    }
}
