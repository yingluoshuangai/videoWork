package xyz.xnmq.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.xnmq.json.Json;
import xyz.xnmq.json.JsonUtils;
import xyz.xnmq.redis.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: by xnmq
 * @Data: 2019/9/21
 * @Description: 主拦截器
 * 在拦截器中，通过将错误信息写入response中，来向前端传递错误信息
 */
public class MimiInterceptor implements HandlerInterceptor{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperator redis;

    @Value("${user.redisSession}")
    private String userRedisSession;


    /**
     * 拦截请求， 在controller调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");
        if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(userToken)){
            String redisToken = redis.get(userRedisSession + ":" + userId);
            if(StringUtils.isEmpty(redisToken)){
                logger.info("********** 用户信息验证失败, redisToken缺失************");
                returnErrorResponse(response, Json.loginError("请登录..."));
                return false;
            }
            if(!redisToken.equals(userToken)){
                logger.info("********** 用户信息验证失败, 令牌不相等************");
                returnErrorResponse(response, Json.loginError("用户在另一台机器上登陆"));
                return false;
            }
        }else {
            logger.info("********** 用户信息验证失败 ************");
            //response.getWriter().write(JsonUtils.toJson(Json.loginError("用户信息验证失败")));
            returnErrorResponse(response, Json.loginError("用户信息验证失败"));
            return false;
        }
        return true;
    }

    /**
     * 将错误信息 写入response中 传给前端
     * @param response
     * @param json
     * @throws IOException
     */
    public void returnErrorResponse(HttpServletResponse response, Json json) throws IOException{
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.toJson(json).getBytes("utf-8"));
            out.flush();
        } finally {
            if(out != null){
                out.close();
            }
        }
    }

    /**
     * 拦截请求， 在controller调用之后，渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 拦截请求， 在controller调用之后，渲染视图之后
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
