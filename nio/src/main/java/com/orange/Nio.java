package com.orange;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;

public class Nio {

    public static void main(String[] args) throws Exception {


        Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };

        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

//        RandomAccessFile raf1 = new RandomAccessFile("nio/src/1.txt", "rw");
//
//        //1. 获取通道
//        FileChannel channel1 = raf1.getChannel();
        FileChannel channel1=FileChannel.open(Paths.get("nio/src/1.txt"), StandardOpenOption.READ);
        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        //4. 聚集写入

        FileChannel channel2=FileChannel.open(Paths.get("nio/src/2.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);

//        RandomAccessFile raf2 = new RandomAccessFile("nio/src/2.txt", "rw");
//        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);
    }
}
