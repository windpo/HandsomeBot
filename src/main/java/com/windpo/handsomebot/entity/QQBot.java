//package com.windpo.handsomebot.entity;
//
//
//import com.windpo.handsomebot.listener.ChatHandler;
//import com.windpo.handsomebot.listener.MyEventHandlers;
//import lombok.extern.slf4j.Slf4j;
//import net.mamoe.mirai.Bot;
//import net.mamoe.mirai.BotFactory;
//import net.mamoe.mirai.event.events.MessageEvent;
//import net.mamoe.mirai.utils.BotConfiguration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
///**
// * @author 风之诗
// * @version 1.0
// */
//@Component
//@Slf4j
//public class QQBot {
//    //自行添加配置文件
//    @Value("${bot.username}")
//    public Long username;
//    @Value("${bot.password}")
//    public String password;
//
//    private static Bot bot;
//
//    public static Bot getBot() {
//        return bot;
//    }
//
//    //设备认证信息文件
//    private static final String deviceInfo = "device.json";
//
//    /**
//     * 启动BOT
//     */
//    public void startBot() {
//        if (null == username || null == password) {
//            System.err.println("*****未配置账号或密码*****");
//            log.warn("*****未配置账号或密码*****");
//        }
//
//        bot = BotFactory.INSTANCE.newBot(username, password, new BotConfiguration() {
//            {
//                //保存设备信息到文件deviceInfo.json文件里相当于是个设备认证信息
//
//                setProtocol(MiraiProtocol.ANDROID_PHONE); // 切换协议
//                setHeartbeatStrategy(HeartbeatStrategy.STAT_HB); // 切换心跳策略
//                setCacheDir(new File("data/cache")); // 缓存目录
//                fileBasedDeviceInfo("data/device.json");// 设备信息
////                redirectBotLogToDirectory();//重定向日志
//                enableContactCache(); // 列表缓存
//            }
//        });
//        bot.login();
//        //添加监听器
//        bot.getEventChannel().registerListenerHost(new MyEventHandlers());
//        bot.getEventChannel().registerListenerHost(new ChatHandler());
//    }
//}
