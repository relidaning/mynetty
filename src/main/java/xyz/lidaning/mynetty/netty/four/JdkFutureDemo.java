package xyz.lidaning.mynetty.netty.four;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class JdkFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("sub thread executed...");
                Thread.sleep(2000);
                return 100;
            }
        });

        log.debug("waiting for result.");
        log.debug("结果是{}", future.get());
    }
}
