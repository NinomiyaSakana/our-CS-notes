import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class TreeNode {
    private File file;
    private int level;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(File file){
        this.file = file;
    }

    public TreeNode(File file, TreeNode parent, int level){
        this.file = file;
        this.parent = parent;
        this.level = level;
    }

    public File getFile(){
        return file;
    }

    public String getName(){
        return file.getName();
    }

    public String getPath(){
        return file.getPath();
    }

    public void setFile(File file){
        this.file = file;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public void removeChild(TreeNode child){
        children.remove(child);
    }
}