# SpringMVC简单笔记

全套笔记：

https://blog.csdn.net/weixin_44822455/article/details/109124359

下面是补充的内容：

MVC三层构架mybatis+spring+springMVC

## 1、回顾MVC

MVC：模型（dao，service），视图（jsp），控制器（servlet）

jsp本质是一个servlet。

### 1.4 回顾Servlet

**MVC框架要做哪些事情**

1. 将url映射到java类或java类的方法 .
2. 封装用户提交的数据 .
3. 处理请求--调用相关的业务处理--封装响应数据 .
4. 将响应的数据进行渲染 . jsp / html 等表示层数据 .

## 2、什么是SpringMVC

正因为SpringMVC好 , 简单 , 便捷 , 易学 , 天生和Spring无缝集成(使用SpringIoC和Aop) , 使用约定优于配置 . 能够进行简单的junit测试 . 支持Restful风格 .异常处理 , 本地化 , 国际化 , 数据验证 , 类型转换 , 拦截器 等等......所以我们要学习。

### 2.2 中心控制器

Spring的web框架围绕DispatcherServlet设计。DispatcherServlet的作用是将请求分发到不同的处理器。从Spring 2.5开始，使用Java 5或者以上版本的用户可以采用基于注解的controller声明方式。

![640](640.webp)



/会匹配所有页面

/*会匹配所有页面加上jsp文件

实现步骤：

1. 新建一个web项目
2. 导入相关jar包
3. 编写web.xml , 注册DispatcherServlet
4. 编写springmvc配置文件
5. 接下来就是去创建对应的控制类 , controller ，return是返回到想要的视图里去。
6. 最后完善前端视图和controller之间的对应
7. 测试运行调试.

### Controller类

controller是控制器，提供访问程序的行为，通过接口定义和注解定义两种方法实现。

只要实现了controller类，那就是一个控制器，会返回modelandview，所以要先new一个modelandview对象。然后addObject。但是一个控制器只能实现一个方法，所以用注解的@Controller。

tips：如果类上标注@Controller切方法上没有标注@ResponseBody的话，return的值会被返回到视图解析器中处理；如果类上标注了@RestController的话，或者方法上标注了ResponseBody的话，就不会被返回到视图解析器中。

@RequestMapping设定映射地址。

具体代码。

https://mp.weixin.qq.com/s/8ddT6FD0Y4f3XdbEz0aqpQ

几个controller可以返回同一个页面，return一样就行，同一个页面被复用了。

### RequestMapping详解

在类上面加@RequestMapping的话整个类都是用那个映射地址，方法上面加@RequestMapping那么就在类的地址后面继续增加地址。

```java
@Controller
@RequestMapping("/admin")
public class TestController{
  @RequestMapping("/h1")
  public String test(){
    return "test";
  }
}
```

访问地址: http://localhost:8080/项目名/admin/h1

只注解了方法，那么直接访问那个地址，在上面直接写死比较好，在类上写比较麻烦。

### RestFul风格

**功能**

资源：互联网所有的事物都可以被抽象为资源

资源操作：使用POST、DELETE、PUT、GET，使用不同方法对资源进行操作。

分别对应 添加、 删除、修改、查询。

**使用RESTful操作资源** ：可以通过不同的请求方式来实现不同的效果！如下：请求地址一样，但是功能可以不同！

http://127.0.0.1/item/1 查询,GET

http://127.0.0.1/item 新增,POST

原来是这样：

http://127.0.0.1/item/queryItem.action?id=1 查询,GET

http://127.0.0.1/item/saveItem.action 新增,POST

```java
  @RequestMapping("/commit/{p1}/{p2}")
```

**使用method属性指定请求类型**

```JAVA
@RequestMapping(value = "/hello",method = {RequestMethod.POST})
```

## 重定向一级转发

//非正常方式

如果不用return的话。

转发

### ServletAPI

```java
//1
rsp.sendRedirect("/index.jsp");

//2
req.setAttribute("msg","/result/t3");
req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,rsp);
//3

```

重定向

都可以用return，超级方便。



**正常方法！！**

**通过SpringMVC来实现转发和重定向 - 无需视图解析器；**

测试前，需要将视图解析器注释掉

```java
@Controller
public class ResultSpringMVC {
   @RequestMapping("/rsm/t1")
   public String test1(){
       //转发
       return "/index.jsp";
  }

   @RequestMapping("/rsm/t2")
   public String test2(){
       //转发二
       return "forward:/index.jsp";
  }

   @RequestMapping("/rsm/t3")
   public String test3(){
       //重定向
       return "redirect:/index.jsp";
  }
}
```

**通过SpringMVC来实现转发和重定向 - 有视图解析器；**

重定向 , 不需要视图解析器 , 本质就是重新请求一个新地方嘛 , 所以注意路径问题.

可以重定向到另外一个请求实现 .

```java
@Controller
public class ResultSpringMVC2 {
   @RequestMapping("/rsm2/t1")
   public String test1(){
       //转发
       return "test";
  }

   @RequestMapping("/rsm2/t2")
   public String test2(){
       //重定向
       return "redirect:/index.jsp";
       //return "redirect:hello.do"; //hello.do为另一个请求/
  }

}
```

## 数据处理

### 处理提交数据

https://mp.weixin.qq.com/s/1d_PAk2IIp-WWX2eBbU3aw

### 什么是json

前后端分离时代。

后端部署后端，提供接口。

用json来进行数据交换（String类型）。

前端独立部署，负责渲染后端的数据。

json和javascript互相转换

### fastjson

json解析的一种包

maven中导入jackson包



## Ajax

忽略

## 处理器拦截器

**过滤器与拦截器的区别：**拦截器是AOP思想的具体应用。

**过滤器**

- servlet规范中的一部分，任何java web工程都可以使用
- 在url-pattern中配置了/*之后，可以对所有要访问的资源进行拦截

**拦截器** 

- 拦截器是SpringMVC框架自己的，只有使用了SpringMVC框架的工程才能使用
- 拦截器只会拦截访问的控制器方法， 如果访问的是jsp/html/css/image/js是不会进行拦截的