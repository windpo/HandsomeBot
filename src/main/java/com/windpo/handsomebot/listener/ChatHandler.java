package com.windpo.handsomebot.listener;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.windpo.handsomebot.entity.MoliBean;
import com.windpo.handsomebot.util.SpringUtil;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * @author 风之诗
 * @version 1.0
 */
@Slf4j
public class ChatHandler extends SimpleListenerHost {

    /**
     * 异常处理
     * @param context
     * @param exception
     */
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception){
        log.warn("Message:{}, Cause: {}\n\t\tstackTrace:{}",exception.getMessage(),exception.getCause(),exception.getStackTrace());
        //log.warn(exception.getMessage(), (Object) exception.getStackTrace());
        // 处理事件处理时抛出的异常
    }

    /**
     * 监听消息内容并做处理
     * @param event
     * @throws Exception
     */
    @EventHandler
    public void onMessage(@NotNull GroupMessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        //获取所有文字内容，如果为图片等非文字内容，则返回“”
        String receivedString = ((PlainText) Objects.requireNonNull(event.getMessage().stream().filter(PlainText.class::isInstance).findFirst().orElse(new PlainText("")))).getContent().trim();
        if(!("/help".equals(receivedString)|| "/version".equals(receivedString)
                ||"/version history".equals(receivedString)
                ||"/获取原神美图".equals(receivedString)
                ||"/吃什么".equals(receivedString)||"/喝什么".equals(receivedString))){
            if (new Random().nextInt(100000)%999<200){
                // 构建请求头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Api-Key", "y8x3j2rx66gehap6");
                headers.add("Api-Secret", "1xsokt6y");

                JSONObject body = new JSONObject();
                // 发送的内容
                body.set("content", receivedString);
                // 消息类型，1：私聊，2：群聊
                body.set("type", 2);
                body.set("from", event.getSender().getId());
                body.set("fromName",event.getSenderName());
                body.set("to",event.getSource().getTargetId());
                body.set("toName",event.getSource().getTarget().getName());

                RestTemplate restTemplate = SpringUtil.getApplicationContext().getBean("restTemplate", RestTemplate.class);
                HttpEntity<JSONObject> formEntity = new HttpEntity(body.toString(), headers);
                final MoliBean moliBean = restTemplate.postForEntity("https://api.mlyai.com/reply", formEntity, MoliBean.class).getBody();
                final String res = moliBean.getData().get(0).getContent();
                event.getSubject().sendMessage(res);


            }
        }
        }
    }

// 在 QQbot 的 startBot() 方法中注册：
// bot.getEventChannel.registerListenerHost(new MyEventHandlers())
