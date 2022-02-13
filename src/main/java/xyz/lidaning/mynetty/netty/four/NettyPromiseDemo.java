package xyz.lidaning.mynetty.netty.four;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class NettyPromiseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop event = new NioEventLoopGroup().next();
        DefaultPromise<Integer> promise = new DefaultPromise<>(event);
        new Thread(()->{
            try {
                log.debug("sub thread executed...");
                Thread.sleep(2000);
                promise.setSuccess(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        log.debug("waiting for result.");
        log.debug("结果是{}", promise.get());

    }
}
