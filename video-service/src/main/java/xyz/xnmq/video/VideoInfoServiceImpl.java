package xyz.xnmq.video;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.xnmq.entity.users.UserSearchRecord;
import xyz.xnmq.entity.video.Bgm;
import xyz.xnmq.entity.video.VideoInfo;
import xyz.xnmq.ffmpeg.FfmpegUtil;
import xyz.xnmq.inf.users.dao.UserSearchRecordDao;
import xyz.xnmq.inf.video.VideoInfoService;
import xyz.xnmq.inf.video.dao.BgmDao;
import xyz.xnmq.inf.video.dao.VideoInfoDao;
import xyz.xnmq.inf.video.dto.VideoInfoDto;
import xyz.xnmq.inf.video.mapper.VideoInfoMapper;
import xyz.xnmq.json.Json;
import xyz.xnmq.mybatis.Page;

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
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VideoInfoDao videoInfoDao;
    @Autowired
    private VideoInfoMapper videoInfoMapper;
    @Autowired
    private BgmDao bgmDao;
    @Autowired
    private UserSearchRecordDao userSearchRecordDao;

    @Value("${file.fileSpace}")
    private String fileSpace;
    @Value("${file.bgmPath}")
    private String bgmPath;

    /**
     * 视频上传
     */
    @Override
    public Json uploadVideo(VideoInfoDto dto) throws Exception{
        logger.info("-----文件上传开始， start-------");
        String uploadPathDB = "/" + dto.getUserId() + "/video/";//保存到数据库的相对路径  /5/video/
        String videoPath = fileSpace + uploadPathDB;//文件上传的磁盘路径  D://material/video_space/private/5/video/
        String finalUploadPathDB = "";//原视频保存到数据库的最终路径  /5/video/xxx.mp4
        String finalVideoPath = "";//原视频上传的最终磁盘路径  D://material/video_space/private/5/video/xxx.mp4
        String finalMergeUploadPathDB = "";//合成视频保存到数据库的最终路径  /5/video/merge_xxx.mp4
        String finalMergeVideoOutputPath = "";//合成视频保存到磁盘的最终路径  D://material/video_space/private/5/video/merge_xxx.mp4
        String finalCoverUploadPathDB = ""; //视频封面的最终数据库路径
        String finalCoverPath = "";//视频封面的最终磁盘路径

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        MultipartFile file = dto.getFile();

        //上传视频
        try {
            String fileName = file.getOriginalFilename();//获得文件名
            if(StringUtils.isNotEmpty(fileName)){

                finalUploadPathDB = uploadPathDB + fileName;
                finalVideoPath = videoPath + fileName;//原文件上传的最终磁盘路径

                File outFile = new File(finalVideoPath);
                if(!outFile.getParentFile().exists()){//父文件不存在
                    outFile.getParentFile().mkdir();//创建父文件目录
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);


                FfmpegUtil ffmpegUtil = new FfmpegUtil("D:/soft/soft3/ffmpeg/bin/ffmpeg.exe");
                //合并视频
                if(dto.getAudioId() != null){
                    Bgm bgm = bgmDao.findOne(dto.getAudioId());
                    String videoInputPath = finalVideoPath;//视频地址
                    String audioInputPath = bgmPath + bgm.getPath();//音频地址
                    String outPutFileName = "merge_" + bgm.getId() + "_" + UUID.randomUUID().toString() + "_" + fileName;//合成视频的文件名
                    finalMergeUploadPathDB = uploadPathDB + outPutFileName;//合成视频保存到数据库的最终路径
                    finalMergeVideoOutputPath = videoPath + outPutFileName;//合成视频保存到磁盘的最终路径
                    ffmpegUtil.mergeVideoAndAudio(videoInputPath, audioInputPath, dto.getVideoDuration(), finalMergeVideoOutputPath);
                }

                //截取视频封面
                String fileNamePrefix = fileName.split("\\.mp4")[0];
                String coverFileName = fileNamePrefix + ".jpg";
                finalCoverUploadPathDB = uploadPathDB + coverFileName; //视频封面的最终数据库路径
                finalCoverPath = videoPath + coverFileName;//视频封面的最终磁盘路径
                ffmpegUtil.cutVideoCover(finalVideoPath, finalCoverPath);



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
        videoInfo.setCoverPath(finalCoverUploadPathDB);
        videoInfo.setLinkCounts(0L);
        videoInfoDao.save(videoInfo);

        return Json.success();

    }


    /**
     * 分页查询
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageInfo findList(VideoInfoDto dto, Page page){
        dto.setStatus(0);//查询未禁播视频
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        return new PageInfo(videoInfoMapper.findList(dto));
    }

    /**
     * 通过视频描述， 查询视频
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageInfo findByDesc(VideoInfoDto dto, Page page){
        //添加查询记录
        UserSearchRecord userSearchRecord = new UserSearchRecord();
        userSearchRecord.setUserId(dto.getUserId());
        userSearchRecord.setContent(dto.getVideoDesc());
        userSearchRecordDao.save(userSearchRecord);

        //查询
        dto.setStatus(0);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo(videoInfoMapper.findList(dto));
    }
}
