package atguigu;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.*;

/**
 * 一、流的分类
 * 1、操作数据单位：字节流、字符流
 * 2、数据流向：输入流、输出流
 * 3、流的角色：节点流、处理流
 * <p>
 * <p>
 * 二、流的体系结构
 * 抽象基类         节点流(或文件)                                  缓冲流（处理流的一种）
 * InputStream     FileInputStream(read byte[] buffer)            BufferedInputStream(read byte[] buffer)
 * OutputStream    FileOutputStream(write byte[] buffer,0,len)    BufferedOutputStream(write byte[] buffer,0,len) /flush()
 * Reader          FileReader(read char[] cbuf)                   BufferedReader(read char[] cbuf 或者 readLine())
 * Writer          FileWriter(write char[] cbuf,0,len)            BufferedWriter(write char[] cbuf,0,len)  flush()
 */

public class FileReaderWriterTest {

    public static void main(String[] args) {
        File file = new File("hello.txt");  //相较于当前工程
        System.out.println(file.getAbsolutePath());


        File file1 = new File("day4/hello.txt");
        System.out.println(file1.getAbsolutePath());

    }

    /*
    将day4下的hello.txt文件内容读入程序中并输出到控制台

    说明点：
    1、read()的理解：返回读入一个字符，如果达到文件末尾，返回-1；
    2、异常的处理：为了保证流资源一定可以执行关闭操作，需要使用try-catch-finally处理
    3、读入的文件一定要存在，否则FileNotFoundException
     */

    @Test
    public void testFileReader() {
        FileReader fr = null;

        try {
            //1、实例化file类的对象，指明要操作的文件
            File file = new File("hello.txt");  //相较于当前module
            //2、提供具体的流（流的实例化）
            fr = new FileReader(file);

            //3、数据的读入过程
            //read()返回读入的字符，如果达到文件末尾，则返回-1。
//        int data= fr.read();
//        while(data!=-1){
//            System.out.print((char)data);
//            data=fr.read(); //后移，跟迭代器一样
//        }

            //优化:语法上的修改
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、流的关闭操作（注意）。所以用finally不用throws，不然会不关闭流，直接throws异常
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    //对read操作进行升级，使用read的重载方法
    @Test
    public void testFileReader1() throws IOException {
        //1、File类的实例化
        FileReader fr = null;
        try {
            File file = new File("hello.txt");

            //2、FileReader流的实例化
            fr = new FileReader("hello.txt");

            //3、读入/写出的操作(如果要读入的数据比较多)
            //read(char[] cbuf):返回每次读入cbuf数组中字符的个数
            //如果达到文件末尾，返回-1。
            char[] cbuf = new char[5];
            int len;
//            while (len = fr.read(cbuf) != -1) {
//                for(int i=0;i<cbuf.length;i++){
//                    System.out.println();
//                }
//            }

            //cbuf.length换成len，每次读进去几个就输出几个，不然最后一次输出还是char的长度
            while ((len = fr.read(cbuf)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
            }

            //其余写法String构造器
            //错误的写法
//            String str=new String(cbuf);
//            System.out.println(str);

            //正确的写法
//            String str = new String(cbuf, 0, len);
//            System.out.println(str);


            //正确的写法
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                //4、资源的关闭（一个或多个）
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    从内存中写出数据到硬盘的文件里
    1、输出操作对应的file是可以不存在的，可以自动创建，不报错
    2、file 如果存在那就是对文件的覆盖
            如果流使用的构造器是FileWriter(file,false)/FileWriter(file,false)
            那就是对原有文件的覆盖
            如果流使用的是FileWriter(file,true)，那就是直接追加内容

     */

    @Test
    public void testFileWriter() throws IOException {
        //1、提供file类的对象，指明写出到的文件
        File file = new File("hello.txt");

        //2、提供FileWriter的对象用于数据的写出
        FileWriter fw = new FileWriter(file, false); //false覆盖，true不覆盖 默认一个参数是false

        //3、写出操作
        //fw.write("I love LHK".toCharArray()); //是数组的形式
        fw.write("I love LHK\n");   //是字符串形式 直接把文件做了覆盖
        fw.write("I love LHK for 100 years");

        //4、流的关闭
        fw.close();
        //
    }

    @Test
    public void testFileReaderFileWriter() {
        //null是try catch自动加的
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1、创建file类对象，指明读入和写出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");

            //2、创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            //3、数据的读入和写出
            char[] cbuf = new char[5];
            int len; //记录每次读入到cbud数组中的字符个数
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(cbuf, 0, len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、关闭流
            //try catch执行后不影响后面的执行，所以两个try-catch并列也可以
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

}
