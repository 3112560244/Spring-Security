package com.qx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: ZedQ
 * @Date: 2022/12/16 16:08
 * @Description: 自定义线程池
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    // 定义一个线程池 方法名很重要！其他方法使用线程池必须指定此方法！
    @Bean(name = "topExecutor")
    public TaskExecutor topExecutor(){
        log.info("Start topExecutor");

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        // 配置设定核心线程数: 最小存在的线程数，如果当前线程小于队列数，依旧会创建线程！使得总线程数 >= 核心线程数
        threadPoolExecutor.setCorePoolSize(5);
        // 配置最大线程数
        threadPoolExecutor.setMaxPoolSize(5);
        // 配置非核心线程空闲线程存活时间 单位秒 默认是60秒
        threadPoolExecutor.setKeepAliveSeconds(60);
        // 允许运行线程数量为 0   这个一般不设置
        //threadPoolExecutor.setAllowCoreThreadTimeOut(true);
        // 配置待执行任务队列大小 默认是(Interger.MaxValue)
        threadPoolExecutor.setQueueCapacity(10);
        // 配置线程池中线程的前缀名
        threadPoolExecutor.setThreadNamePrefix("Async-service-top-");

        // 配置阻塞队列满后执行拒绝策略。
        /*
         AbortPolicy 这是默认策略。队列满了，是直接抛出 RejectedExecutionException 异常。
         CallerRunsPolicy 是阻塞新任务进入队列。如果当线程池的线程有空余，才回去读取队列任务执行，才会将阻塞任务加入队列。
         DiscardOldestPolicy 是丢弃队列中靠最前的任务。 如果队列有A、B、C 的3个阻塞，此时没有新的线程空闲，突然来了D线程，A线程就会被D代替。此时队列的排序是 B、C、D
         DiscardPolicy 是队列满了，直接丢弃新的任务。
         */
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.initialize();
        log.info("End topExecutor");
        return threadPoolExecutor;
    }


    @Bean(name = "testExecutor")
    public TaskExecutor testExecutor(){
        log.info("Start testExecutor");

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        // 配置设定核心线程数: 最小存在的线程数，如果当前线程小于队列数，依旧会创建线程！使得总线程数 >= 核心线程数
        threadPoolExecutor.setCorePoolSize(5);
        // 配置最大线程数
        threadPoolExecutor.setMaxPoolSize(5);
        // 配置非核心线程空闲线程存活时间 单位秒 默认是60秒
        threadPoolExecutor.setKeepAliveSeconds(60);
        // 允许运行线程数量为 0   这个一般不设置
        //threadPoolExecutor.setAllowCoreThreadTimeOut(true);
        // 配置待执行任务队列大小 默认是(Interger.MaxValue)
        threadPoolExecutor.setQueueCapacity(10);
        // 配置线程池中线程的前缀名
        threadPoolExecutor.setThreadNamePrefix("Async-service-test-");

        // 配置阻塞队列满后执行拒绝策略。
        /*
         AbortPolicy 这是默认策略。队列满了，是直接抛出 RejectedExecutionException 异常。
         CallerRunsPolicy 是阻塞新任务进入队列。如果当线程池的线程有空余，才回去读取队列任务执行，才会将阻塞任务加入队列。
         DiscardOldestPolicy 是丢弃队列中靠最前的任务。 如果队列有A、B、C 的3个阻塞，此时没有新的线程空闲，突然来了D线程，A线程就会被D代替。此时队列的排序是 B、C、D
         DiscardPolicy 是队列满了，直接丢弃新的任务。
         */
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.initialize();
        log.info("End testExecutor");
        return threadPoolExecutor;
    }
}
