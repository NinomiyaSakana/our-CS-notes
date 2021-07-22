package atguigu;

import org.junit.Test;

import java.io.*;

/**
 * 1、缓冲流
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 * <p>
 * <p>
 * 2、作用：提高流的读取、写入的速度
 * 提高读写速度的原因：内部提供了一个缓冲区 flush()了一下
 * <p>
 * 3、处理流，就是套接在已有流的基础上。
 */
public class BufferedTest {
    /*
    实现非文本文件的复制
     */

    @Test
    public void BufferedStreamTest() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1、造文件
            File srcFile = new File("photoTest.PNG");
            File destFile = new File("photoTest_01.PNG");

            //2.1、造流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            //2.2、造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3、复制的细节：读取、输入
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read()) != -1) { //只是把fis和fos换成了bis和bos
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //说明：外层关闭了之后其实内层也关闭了。可以省略。
//        fos.close();
//        fis.close();


        }

        //4、资源关闭(关闭4个)
        //要求：线关闭外层的流，再关闭内层的流


    }


    /*
    使用bufferedReader和bufferedWriter复制文本文件
     */
    @Test
    public void testBufferedReaderBufferedWriter() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //创建文件
            br = new BufferedReader(new FileReader(new File("hello.txt")));
            bw = new BufferedWriter(new FileWriter(new File("hello4.txt")));

            //读写操作（char型数）
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1) {
//                bw.write(cbuf, 0, len);
//                //            bw.flush(); //刷新缓存区
//            }


            //方法二：使用String
            String data;
            while((data=br.readLine())!=null){
                //方式一：
                // bw.write(data+"\n");  //data不包含换行符
                //方式二：
                bw.write(data);
                bw.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
