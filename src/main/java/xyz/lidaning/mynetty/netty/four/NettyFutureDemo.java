package xyz.lidaning.mynetty.netty.four;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Slf4j
public class NettyFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(new Callable<Integer>(){

            @Override
            public Integer call() throws Exception {
                log.debug("sub thread executed...");
                Thread.sleep(2000);
                return 100;
            }
        });

        log.debug("waiting for result.");

        /**
         * 方式1：future.get()
         *      主线程获取到结果
         */
//        log.debug("结果是{}", future.get());


        /**
         * 方式2：future.addListener
         *      子线程获取到结果
         */
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("结果是{}", future.getNow());
            }
        });

    }


}
