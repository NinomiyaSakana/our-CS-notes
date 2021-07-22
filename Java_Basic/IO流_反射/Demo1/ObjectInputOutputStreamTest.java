package atguigu;

import com.sun.corba.se.impl.io.InputStreamHook;
import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1、ObjectInputStream和ObjectOutputStream
 * 2、用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可
 * 以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 * <p>
 * 3、要想一个java对象是可序列化的需要满足：
 */

//首先是String类的序列化和反序列化

public class ObjectInputOutputStreamTest {
    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */


    //序列化
    @Test
    public void testObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            //1、造流造文件
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            //2、写出
            oos.writeObject(new String("I love LHK."));

            oos.flush();//刷新操作


            oos.writeObject(new Person("kayasu", 23));
            oos.flush();


            oos.writeObject(new Person("kayasu1", 23,2003,new Account(23.34)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //反序列化:将磁盘文件中的对象还原为内存中的一个java对象
    //用ObjectInputStream()实现
    //读取这个dat文件
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;  //强转


            Person p = (Person) ois.readObject();
            System.out.println(str);
            System.out.println(p);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
