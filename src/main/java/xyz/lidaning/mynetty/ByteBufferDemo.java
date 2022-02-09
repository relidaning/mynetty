package xyz.lidaning.mynetty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

@Slf4j
public class ByteBufferDemo {

    public static void main(String[] args) {
        demo1();


    }

    /**
     * 从文件读取
     */
    public static void demo1(){
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);        //分配
            while (true){
                int len = channel.read(buffer);
                log.debug("读取到了{}个字节", len);
                if(len == -1){
                    break;
                }
                buffer.flip();      //切换读写模式
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    log.debug("读取到：{}", (char)b);
                }
                buffer.clear();
            }

        } catch (IOException e) {
        }
    }

}
