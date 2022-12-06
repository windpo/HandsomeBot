package com.windpo.handsomebot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 风之诗
 * @version 1.0
 */
@RestController
@Slf4j
public class BasicSettingController {
    //此处修改版本
    final String version = "当前憨憨的版本为：1.1.0\n";
    //此处修改更新时间
    final String updateTime = "更新时间2022/10/26\n";
    /**
     * 查看功能列表
     * @return
     */
    @GetMapping("/help")
    public String getHelp(){
        //此处添加功能列表
        final String function = "1、/获取原神美图\n" +
                "2、/吃什么  (举例：/吃什么-->随机获取食物,/吃什么 汉堡-->添加食物)\n" +
                "3、/喝什么  (举例：/喝什么-->随机获取食物,/喝什么 汉堡-->添加饮料)\n";

        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("handsomebot\n" +
                        version +
                        updateTime +
                        "输入 /version 查看版本\n"
                        +"输入相关指令憨憨会为您服务哦\n")
                .append("--------------------\n")
                .append(function)
                .append("--------------------\n");
        final String res = String.valueOf(stringBuffer);
        return res;
    }

    /**
     * 获取此次更新的内容
     * @return
     */
    @GetMapping("/version")
    public String getVersion(){
        //此处包含该版本更新功能
        final String content = "1、/吃什么 随机获取食物或者添加食物\n"
                +"2、/喝什么 随机获取饮料或者添加饮料\n";
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("--------------------\n" +
                version +
                "更新内容：\n" +
                content +
                updateTime +
                "输入 /version history 查看历史版本\n" +
                "--------------------");
        final String res = stringBuffer.toString();
        return res;
    }

    /**
     * 获取历史更新版本
     * @return
     */
    @GetMapping("/version history")
    public String getVersionHistory(){
        final String presentVersion = "当前版本：1.1.0\n";
        final String historyVersion = "历史版本：1.0.0\n";
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("--------------------\n")
                .append(presentVersion)
                .append(historyVersion)
                .append("--------------------");
        final String res = stringBuffer.toString();
        return res;
    }

    /**
     *
     */
    @GetMapping("characterSetting")
    public String getCharacterSetting(){
        final String setting = "嗨~，我是handsomebot，为hanser而生的bot，你也可以叫我憨憨bot哦！\n" +
                "我的主人是windpo，如果你找想我的话，快去找他快去找他，憨憨很社恐的\n" +
                "名字来源：我的名字是handsomebot，因为主人很崇拜hanser，所以主人就用hanser名字的来源作为我的名字啦！你也可以叫我憨憨哦~\n" +
                "人设：憨憨很害羞很社恐但又很想和大家一起玩，所以大家一起愉快的玩耍吧！\n" +
                "功能支持：憨憨喜欢玩原神、还喜欢听歌，对其它的事情也很感兴趣哒，输入/help憨憨可以查看憨憨的功能哦\n";
        return setting;
    }


}
