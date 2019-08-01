package xyz.xnmq.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Copyright (C), 2017-2077 上海从开网络科技有限公司 版权所有
 * @Website www.congkai.net
 * @Author: Created by YangLiang
 * @Date: 2017/6/15
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * YangLiang          2017/6/15            00000001         创建文件
 */
public interface ILog {
    Logger logger = LoggerFactory.getLogger(ILog.class);
}
