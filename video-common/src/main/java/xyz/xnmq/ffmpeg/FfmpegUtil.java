package xyz.xnmq.ffmpeg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/8/6
 * @Description: 测试ffmpeg, 通过ProcessBuilder执行cmd命令
 */
public class FfmpegUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String ffmpegEXE;

    public FfmpegUtil(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    /**
     * 视频格式转换
     *
     * @param videoInputPath
     * @param videoOutputPath
     */
    public void convertor(String videoInputPath, String videoOutputPath) throws Exception{
        //ffmpeg -i stealing.mp4 output.avi
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);

        System.out.println("ffmpeg 命令：" + command.toString());

        ProcessBuilder builder = new ProcessBuilder(command);//通过ProcessBuilder执行cmd命令
        Process process = builder.start();

        //资源上说processBuilder会产生错误流，如果不进行处理，当流过大时，会阻塞进程，所以下面对流进行处理。实际情况是什么不清楚。
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while(br.readLine() != null){
            line = br.readLine();
        }

        if(br != null){
            br.close();
        }
        if(inputStreamReader != null){
            inputStreamReader.close();
        }
        if(errorStream != null){
            errorStream.close();
        }

    }

    /**
     * 合并视频与音频
     * @param videoInputPath 视频地址
     * @param audioInputPath 音频地址
     * @param duration 合并长度
     * @param videoOutputPath 输出地址
     * @throws Exception
     */
    public void mergeVideoAndAudio(String videoInputPath, String audioInputPath,
                                   double duration, String videoOutputPath) throws Exception{
        //ffmpeg.exe -i cat_fish.mp4 -i 001.mp3 -y -t 12.0 -map 0:v:0 -map 1:a:0 merge_cat_fish.mp4
        //注意，如果视频没有声音，可以直接合并，但是如果视频有声音，需要加-map命令，否则合并的视频没有效果
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-i");
        command.add(audioInputPath);
        command.add("-y");
        command.add("-t");
        command.add(String.valueOf(duration));
        command.add("-map");
        command.add("0:v:0");
        command.add("-map");
        command.add("1:a:0");
        command.add(videoOutputPath);

        outputCommand(command);//打印命令

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while((line = br.readLine()) != null){
            logger.info(line);
            //System.out.println(line);
        }
        if(br != null){
            br.close();
        }
        if(inputStreamReader != null){
            inputStreamReader.close();
        }
        if(errorStream != null){
            errorStream.close();
        }
    }

    /**
     * 截取视频封面
     * @param videoInputPath 视频地址
     * @param coverOutputPath 截取的图片地址
     * @throws Exception
     */
    public void cutVideoCover(String videoInputPath, String coverOutputPath) throws Exception{
        //ffmpeg -i dao.mp4 -y -ss 00:00:01 -vframes 1 new_02.jpg
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-y");
        command.add("-ss");
        command.add("00:00:01");
        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);
        outputCommand(command);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        //处理因为执行命令，产生的输入流
        InputStream inputStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        if(bufferedReader != null){
            bufferedReader.close();
        }
        if (inputStreamReader != null){
            inputStreamReader.close();
        }
        if (inputStream != null){
            inputStream.close();
        }
    }

    //打印 ffmpeg命令
    public void outputCommand(List<String> commandList){
        String outputString = "";
        for(String command : commandList){
            outputString += command + " ";
        }
        logger.info("ffmpeg命令：{}", outputString);
        //System.out.println("ffmpeg命令：" + outputString);
    }

    public static void main(String[] args) throws Exception{
        FfmpegUtil ffmpeg = new FfmpegUtil("D:/soft/soft3/ffmpeg/bin/ffmpeg.exe");
        //视频格式转换 测试
//        ffmpeg.convertor("D:/material/temporary/cat_dog.mp4", "D:/material/temporary/cat_dog.avi");
        //视频与音频合并测试
//        ffmpeg.mergeVideoAndAudio("D:/material/temporary/cat_fish.mp4", "D:/material/temporary/001.mp3",
//                12, "D:/material/temporary/merge_cat_fish.mp4");

        ffmpeg.cutVideoCover("D:/material/temporary/cat_fish.mp4", "D:/material/temporary/cat_fish.jpg");
    }
}
