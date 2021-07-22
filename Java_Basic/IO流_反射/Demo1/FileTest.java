package atguigu;

/*
 * file类的使用
 *
 * */



import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {
    /*
   1、如何创建file类的实例
   File(String filePath)


   2、
   相对路径：相较于某个路径下，指明的路径
   绝对路径：包含盘符在内的文件或文件目录的路径

   3、路径分隔符号
   win：相反 两条杠
   unix&mac：/

     */

    @Test
    public void test1() {

        //构造器1
        File file1 = new File("hello.txt");  //相对于当前module
        System.out.println(file1);

        //构造器2
        //File file3=new File(目录类型，文件类型);

        //构造器3
        //File file4=new File(file3，"hi.txt");
    }

    @Test
    public void test2(){
        File file=new File("/Users/qubeier/Documents/JavaSenior");

        //String格式
        String[] list=file.list();
        for(String s: list){
            System.out.println(s);
        }

        //File格式
        File[] lists= file.listFiles();
        for(File f: lists){
            System.out.println(f);
        }

    }



    /*
     File类的创建功能
    public boolean createNewFile() :创建文件。若文件存在，则不创建，返回false

    public boolean mkdir() :创建文件目录。如果此文件目录存在，就不创建了。
    如果此文件目录的上层目录不存在，也不创建。

    public boolean mkdirs() :创建文件目录。如果上层文件目录不存在，一并创建
    注意事项:如果你创建文件或者文件目录没有写盘符路径，那么，默认在【项目路径】下。

    - File类的删除功能
    public boolean delete():删除文件或者文件夹

    删除注意事项:
    Java中的删除不走回收站。 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录
     */

    @Test
    public void test6() throws IOException {
        //文件的创建
        File file1=new File("hi.txt");
        if(!file1.exists()){
            file1.createNewFile();
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();
            System.out.println("删除成功");
        }

    }


    @Test
    public void test7(){
        //文件目录的创建
        File file1=new File("/Users/qubeier/Documents/JavaSenior/day4/io1/io3");
        boolean mkdir=file1.mkdir();

        if(mkdir) {
            System.out.println("创建成功1");
        }


        File file2=new File("/Users/qubeier/Documents/JavaSenior/day4/io1/io4");
        boolean mkdir2=file1.mkdirs();

        if(mkdir2) {
            System.out.println("创建成功2");
        }

    }

}
