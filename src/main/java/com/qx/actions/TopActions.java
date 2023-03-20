package com.qx.actions;

import com.qx.service.impl.TopServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * TODO
 *
 * @Description top抓取
 * @Author ZedQ
 * @Date 2023/3/17 22:38
 * @Version 1.0
 **/
@Slf4j
@Component
public class TopActions {
    @Resource
    private TopServiceImpl topService;

    @PostConstruct
    public void start(){
        ActionsApiList();
    }

    @Scheduled(cron = "${corn.top}")
    public void ActionsApiList(){
        log.info("开始抓取top");
        for (int i=100000;i<100113;i++)
            topService.getRequest(i);
        log.info("结束抓取top共113");
        log.info("结束抓取top共113");
    }





}

