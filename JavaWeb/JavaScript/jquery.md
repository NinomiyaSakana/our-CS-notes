### sample coding
``` js
<head>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){//入口函数，或者简写$(function(){smoe code;});
  $("p").click(function(){
    $(this).hide();
  });
});
</script>
</head>
```
### 常见DOM事件
|    鼠标事件   |  键盘事件  | 表单事件 | 文档/窗口事件 |
|---------------|------------|----------|---------------|
| `click`       | `keypress` | `submit` | `load`        |
| `dblclick`    | `keydown`  | `change` | `resize`      |
| `mouseeenter` | `keyup`    | `focus`  | `scroll`      |
| `mouseleave`  |            | `blur`   | `unload`      |
| `hover`       |            |          |               |

#### some explanation
当点击事件在某个 <p> 元素上触发时，隐藏当前的 <p> 元素
```js
$("p").click((function){
    $(this).hide();
});
```

双击 `dblclick`
```js
$("p").dblclick(function(){
    $(this).hide();
});
```

当鼠标指针穿过元素时，会发生 mouseenter 事件。

当鼠标指针离开元素时，会发生 mouseleave 事件。

当鼠标指针移动到元素上方，并按下鼠标按键时，会发生 mousedown 事件。

当在元素上松开鼠标按钮时，会发生 mouseup 事件。

hover()方法用于模拟光标悬停事件。

当元素获得焦点时，发生 focus 事件。
```js
$("input").focus(function(){
    $(this).css("background-color","#cccccc");
});
```

### jQuery效果
|    效果   |                                    方法                                    |
|-----------|----------------------------------------------------------------------------|
| 隐藏/显示 | `hide(300)` `show()` `toggle()`                                            |
| 淡入淡出  | `fadeIn("slow")` `fadeOut` `fadeToggle` `fadeTo(speed, opacity, callback)` |
| 滑动      | `sildeDown()` `slideUp` `slideToggle`                                      |
| 动画      | `animate({params},speed,callback)` params 为CSS属性                        |
| 停止动画  | `stop`                                                                           |
#### jQuery 链
Chaining 允许我们在一条语句中运行多个 jQuery 方法（在相同的元素上）。

demo:
```js
$("#p1").css("color","red").slideUp(2000).slideDown(2000);
/* spaces will be ignored */
$("#p1").css("color","red")
  .slideUp(2000)
  .slideDown(2000);
```

### jQuery HTML
jQuery 拥有可操作 HTML 元素和属性的强大方法。

#### 获得内容：
* text() - 设置或返回所选元素的文本内容
* html() - 设置或返回所选元素的内容（包括 HTML 标记）
* val() - 设置或返回表单字段的值
* attr() 方法用于获取属性值。比如href属性

text()、html() 以及 val()，同样拥有回调函数。回调函数有两个参数：被选元素列表中当前元素的下标，以及原始（旧的）值。然后以函数新值返回您希望使用的字符串。
```js
/* 回调自己? */ 
$("#btn1").click(function(){
    $("#test1").text(function(i,origText){
        return "旧文本: " + origText + " 新文本: Hello world! (index: " + i + ")"; 
    });
});
```

#### 添加元素：
* append() - 在被选元素的结尾插入内容
* prepend() - 在被选元素的开头插入内容
* after() - 在被选元素之后插入内容
* before() - 在被选元素之前插入内容

#### 删除元素：
* remove() - 删除被选元素（及其子元素）
* empty() - 从被选元素中删除子元素
jQuery remove() 方法也可接受一个参数，允许您对被删元素进行过滤。
```js
$("p").remove(".italic");
```

#### 获取并设置CSS类
* addClass() - 向被选元素添加一个或多个类
* removeClass() - 从被选元素删除一个或多个类
* toggleClass() - 对被选元素进行添加/删除类的切换操作
* css() - 设置或返回样式属性
```js
/* class不需要写. */
$("button").click(function(){
  $("body div:first").addClass("important blue");
});
```
css()方法
```js
/* 返回 */
$("p").css("background-color");
/* 设置 */
$("p").css("background-color","yellow");
/* 设置多个属性 */
$("p").css({"background-color":"yellow","font-size":"200%"});
```

#### 尺寸
![尺寸](https://www.runoob.com/images/img_jquerydim.gif)

### 遍历
#### 向上遍历 DOM 树:
* parent()
* parents()，返回被选元素的所有祖先元素，它一路向上直到文档的根元素 (<html>)。
* parentsUntil()，返回介于两个给定元素之间的所有祖先元素。

#### 向下遍历 DOM 树
* children()，返回被选元素的所有直接子元素。
* find()，返回被选元素的后代元素，一路向下直到最后一个后代。

```js
/* 返回类名为 "1" 的所有 <p> 元素，并且它们是 <div> 的直接子元素 */
/* parents, children, siblings也可以使用相似的方法过滤 */
$(document).ready(function(){
  $("div").children("p.1");
});
```

#### 在 DOM 树中水平遍历
* siblings()，返回被选元素的所有同胞元素。
* next()，返回被选元素的下一个同胞元素。
* nextAll()，返回被选元素的所有跟随的同胞元素。
* nextUntil()，返回介于两个给定参数之间的所有跟随的同胞元素。
* prev(), prevAll() 以及 prevUntil() 类似。

#### 过滤
```js
/* 选取首个 <div> 元素内部的第一个 <p> 元素 */
$(document).ready(function(){
  $("div p").first();
});
/* 选择最后一个 <div> 元素中的最后一个 <p> 元素 */
$(document).ready(function(){
  $("div p").last();
});
/* 返回带有类名 "url" 的所有 <p> 元素 */
$(document).ready(function(){
  $("p").filter(".url");
});
/* 与filter相反 */
$(document).ready(function(){
  $("p").not(".url");
});
```

### AJAX
AJAX = 异步 JavaScript 和 XML（Asynchronous JavaScript and XML）。

简短地说，在不重载整个网页的情况下，AJAX 通过后台加载数据，并在网页上进行显示。

#### load()
基本语法
```js
$(selector).load(URL,data,callback);
```
把 "demo_test.txt" 文件中 id="p1" 的元素的内容，加载到指定的 <div> 元素中
```js
$("#div1").load("demo_test.txt #p1");
```

回调函数
* responseTxt - 包含调用成功时的结果内容
* statusTXT - 包含调用的状态
* xhr - 包含 XMLHttpRequest 对象

在 load() 方法完成后显示一个提示框。如果 load() 方法已成功，则显示"外部内容加载成功！"，而如果失败，则显示错误消息
```js
$("button").click(function(){
  $("#div1").load("demo_test.txt",function(responseTxt,statusTxt,xhr){
    if(statusTxt=="success")
      alert("外部内容加载成功!");
    if(statusTxt=="error")
      alert("Error: "+xhr.status+": "+xhr.statusText);
  });
});
```

#### get()&post()
` $.get() `方法从服务器上的一个文件中取回数据
```js
$("button").click(function(){
  $.get("demo_test.php",function(data,status){
    alert("数据: " + data + "\n状态: " + status);
  });
});
```

`$.post() `方法通过 HTTP POST 请求向服务器提交数据。
```js
$("button").click(function(){
    $.post("/try/ajax/demo_test_post.php",
    {
        name:"菜鸟教程",
        url:"http://www.runoob.com"
    },
    function(data,status){
        alert("数据: \n" + data + "\n状态: " + status);
    });
});
```

### In Addition
`noConflict() `方法会释放对 $ 标识符的控制，这样其他脚本就可以使用它了。



