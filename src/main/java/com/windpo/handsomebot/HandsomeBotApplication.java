package com.windpo.handsomebot;

import com.windpo.handsomebot.config.QQBotConfig;
import com.windpo.handsomebot.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HandsomeBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandsomeBotApplication.class, args);
        final QQBotConfig qqBot = SpringUtil.getBean(QQBotConfig.class);
        qqBot.startBot();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
