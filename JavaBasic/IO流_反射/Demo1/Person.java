package atguigu;

import java.io.Serializable;

/**
 * 用于系列化的类Person
 *
 *
 * Person要满足下面的要求方可序列化：
 * 1、需要实现接口Serializable
 * 2、当前类提供一个全局变量serialVersionUID
 * 3、除了当前Person类需要实现接口Serializable之外，要保证内部所有的属性都是可序列化的。
 * （默认情况下基本类型可以序列化）
 * 4、序列化机制：
 * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流
 * 从而允许把这种二进制流持久地保存在磁盘上
 * 或通过网络将这种二进制流传输到另一个网络节点（json）。
 * 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象
 *

 *
 * 补充
 * ObjectOutputStream和ObjectInputStream不能序列化static和transient修 饰的成员变量
 */

public class Person implements Serializable {

    public static final long serialVersionUID = 4754615250503L;
    //序列版本号
    //自定义异常类型的时候也要定义这个

    private String name;
    private int age;
    private int id;
    private Account acct;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }
}


class Account implements Serializable{

    public static final long serialVersionUID = 4754615250502L;

    private double balance;

    public Account(double balance) {

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}


