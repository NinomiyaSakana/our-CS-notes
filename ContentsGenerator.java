import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;

public class ContentsGenerator {
public static void main(String[] args) throws IOException {
    File root = new File("./");
    File readme = new File("README.md");
    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(readme), "UTF-8");
    String front = String.join("\n",
        "# our-CS-notes",
        "CS notes from :yellow_heart:[Sakana](https://github.com/NinomiyaSakana) & [Kayasu](https://github.com/Li-Huakang) :yellow_heart:",
        "",
        "自学笔记 不定期更新",
        "```bash",
        "# 更新目录添加Actions方法",
        "# 添加Java脚本 - 2021-9-4",
        "# 打开终端",
        "cd /path/of/the/repo",
        "# 更新目录索引README.md",
        "python contents.py",
        "```",
        "",
        "```md",
        "笔记目录索引结构",
        "# our-CS-notes",
        "## second-level directory name",
        "### third-level directory name",
        "...",
        "[markdown file title name](./path/name)",
        "```",
        "",
        ""
        );
    osw.write(front);
    osw.flush();
    TreeNode res = new TreeNode(root);
    traverseAll(root, 1, res);
    writeRes(res, osw);
    osw.close();
    // printRes(res);
}

private static boolean traverseAll(File root, int level, TreeNode node){
    // 返回值 该文件夹及其子目录下有无md文件
    // root 根目录
    // level 目录基于项目目录的分级
    File[] files = root.listFiles();
    if(files.length==0)return false;//没有文件就返回false，该子节点无效
    // Arrays.sort(files);//需要重写，要把md文件排在前面
    // sortFile(files);//讲md文件排在前面，文件夹排在后面
    Arrays.sort(files, (o1,o2)->{
        if(o1.getName().endsWith(".md")&&o2.getName().endsWith(".md"))return o1.getName().compareTo(o2.getName());
        else if(o1.getName().endsWith(".md")&&!o2.getName().endsWith(".md"))return 0;
        else if(o2.getName().endsWith(".md")&&!o1.getName().endsWith(".md"))return 1;
        else return o1.getName().compareTo(o2.getName());
    });
    Boolean flagDir = false;
    Boolean flagFile = false;
    for (File file:files){
        if(file.isDirectory()&&isValidDir(file.getName())){
            TreeNode child = new TreeNode(file, node, level);
            node.addChild(child);
            flagDir = traverseAll(file, level+1, child);
        }else if (file.isFile()&&isValidFile(file.getName())) {
            node.addChild(new TreeNode(file, node, level));
            flagFile = true;
        }
    }
    if(!(flagDir||flagFile)){
        //说明该级目录是无效目录
        node.getParent().removeChild(node);
    }
    return flagDir||flagFile;
}

private static boolean isValidDir(String dir){
    //is in dirs [.git .github figures] or not
    HashSet<String> excludeList = new HashSet<>(Arrays.asList(".git",".github","figures"));//第一级目录要去除的文件夹
    if(excludeList.contains(dir))return false;
    return true;
}
    
private static boolean isValidFile(String file){
    //is markdown file but not README.md
    return file.endsWith(".md")&&(!file.equals("README.md"));
}

private static String prefixOfMd(int n){
    return String.join("",Collections.nCopies(n, "#"));
}

private static void printRes(TreeNode root){
    for (TreeNode child : root.getChildren()){
        int level = child.getLevel();
        System.out.println(prefixOfMd(level+1)+" "+child.getName());
        printRes(child);
    }
}

private static void writeRes(TreeNode root, OutputStreamWriter osw) throws IOException {
    for (TreeNode child : root.getChildren()){
        int level = child.getLevel();
        if(child.getFile().isDirectory()){
            String line = prefixOfMd(level+1)+" "+child.getName() + "\n";
            osw.write(line);
        }
        else if(child.getFile().isFile()){
            String line = "["+child.getName()+"]" + "("+child.getPath().replace(" ","%20")+")" + "\n\n";
            osw.write(line);
        };
        osw.flush();
        writeRes(child, osw);
    } 
}
    
}
