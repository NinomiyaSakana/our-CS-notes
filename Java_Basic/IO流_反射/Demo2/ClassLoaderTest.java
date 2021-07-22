package www.atguitu.com;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    /*
    porperties:用来读取配置文件。
     */

    @Test
    public void test2() throws Exception {
        Properties pros=new Properties();

        //此时的文件默认在当前的module下
        //读取配置文件的方式一：
//        FileInputStream fis=new FileInputStream("jdbc.properties");
//        pros.load(fis);

        //方式二（用类的加载器）：
        //使用ClassLoader，配置文件默认识别为：当前module的src下。
        ClassLoader classLoader=ClassLoaderTest.class.getClassLoader();
        InputStream is=classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);


        String user=pros.getProperty("user");
        String password=pros.getProperty("password");
        System.out.println("user="+user+",password="+password);




    }
}
