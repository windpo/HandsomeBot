package com.windpo.handsomebot.listener;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author 风之诗
 * @version 1.0
 */
@Slf4j
public class MyEventHandlers extends SimpleListenerHost {

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception){
        //log.warn("Message:{}, Cause: {}\n\t\tstackTrace:{}",exception.getMessage(),exception.getCause(),exception.getStackTrace());
        //log.warn(exception.getMessage(), (Object) exception.getStackTrace());
        // 处理事件处理时抛出的异常
        exception.printStackTrace();
    }

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        //获取所有文字内容，如果为图片等非文字内容，则返回“”
        String judgeString = ((PlainText) Objects.requireNonNull(event.getMessage().stream().filter(PlainText.class::isInstance).findFirst().orElse(new PlainText("")))).getContent().trim();
        //获取图片内容
        //final Image image = (Image) Objects.requireNonNull(event.getMessage().stream().filter(Image.class::isInstance).findFirst()).get();
        /**
         * 获取原神图片
         */
        if("/获取原神美图".equals(judgeString)){
            log.info("开始获取原神图片");
//            String result1= HttpUtil.get("https://www.baidu.com");
//            final Image image = Image.newBuilder(result).build();
//            HttpResponse getResponse = HttpRequest.get("https://api.dujin.org/pic/yuanshen/")
//                    .header("Content-Type", "image/jpeg")
//                    .header("Accept-Encoding", "gzip, deflate, br")
//                    .header("Connection","keep-alive").execute();
            //调用获取原神图片接口
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(new HttpGet("https://api.dujin.org/pic/yuanshen/"));
            HttpEntity httpEntity = response.getEntity();
            InputStream content = httpEntity.getContent();
            ExternalResource resourse = ExternalResource.Companion.create(content);
            Image image1 = ExternalResource.uploadAsImage(resourse, event.getSubject());
            event.getSubject().sendMessage(image1);
            resourse.close();
//            final JSONArray objects = JSONUtil.parseArray(result1);
//            objects.forEach(System.out::println);
            log.info("获取原神图片成功");
        }
        // 无返回值, 表示一直监听事件
        /**
         * 获取功能列表
         */
        if("/help".equals(judgeString)||"你能做些啥呀".equals(judgeString)){
            log.info("开始获取功能列表");
            String res = HttpUtil.get("localhost:8080/help");
            event.getSubject().sendMessage(res);
            log.info("获取功能列表结束");
        }

        /**
         * 获取当前版本
         */
        if("/version".equals(judgeString)){
            log.info("开始获取当前版本");
            final String res = HttpUtil.get("localhost:8080/version");
            event.getSubject().sendMessage(res);
            log.info("获取版本列表结束");
        }

        /**
         * 获取版本历史列表
         */
        if("/version history".equals(judgeString)){
            log.info("开始获取版本历史列表");
            final String res = HttpUtil.get("localhost:8080/version history");
            event.getSubject().sendMessage(res);
            log.info("获取版本历史列表结束");
        }
        /**
         * 通过打招呼获取人设
         */
        if("你好".equals(judgeString)){
            log.info("开始获取人设");
            final String res = HttpUtil.get("localhost:8080/characterSetting");
            event.getSubject().sendMessage(res);
            log.info("获取人设结束");
        }
        /**
         * 吃什么功能
         */
        //从redis中获取吃什么
        if("/吃什么".equals(judgeString)){
            log.info("开始吃什么");
            final String res = HttpUtil.get("localhost:8080/daily/getFood");
            event.getSubject().sendMessage(res);
            log.info("结束吃什么");
        }
        //向redis中添加吃什么
        if(judgeString.matches("/吃什么 .*")){
            final char[] chars = judgeString.toCharArray();
            final String res = String.valueOf(chars, 5, judgeString.length() - 5);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("foodName",res);
            final String rString = HttpUtil.post("localhost:8080/daily/addFood",hashMap);
            event.getSubject().sendMessage(rString);
        }
        /**
         * 喝什么功能
         */
        //从redis中获取吃什么
        if("/喝什么".equals(judgeString)){
            log.info("开始喝什么");
            final String res = HttpUtil.get("localhost:8080/daily/getDrink");
            event.getSubject().sendMessage(res);
            log.info("结束吃什么");
        }
        //向redis中添加吃什么
        if(judgeString.matches("/喝什么 .*")){
            final char[] chars = judgeString.toCharArray();
            final String res = String.valueOf(chars, 5, judgeString.length() - 5);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("drinkName",res);
            final String rString = HttpUtil.post("localhost:8080/daily/addDrink",hashMap);
            event.getSubject().sendMessage(rString);
        }
    }
}

// 在 QQbot 的 startBot() 方法中注册：
// bot.getEventChannel.registerListenerHost(new MyEventHandlers())
