### 事件

事件是您在编程时系统内发生的动作或者发生的事情，系统响应事件后，如果需要，您可以某种方式对事件做出回应。

#### a sample

```html
<button>Change color</button>
```
```javascript
const btn = document.querySelector('button');//获取文本中button元素

function random(number) {
  return Math.floor(Math.random()*(number+1));
}

btn.onclick = function() {
  const rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
  document.body.style.backgroundColor = rndCol;
}
```

#### 事件机制处理选择

[参考](https://developer.mozilla.org/zh-CN/docs/Learn/JavaScript/Building_blocks/Events)

* 绝对不应该使用HTML事件处理程序属性

* 事件处理程序属性功能和选项会更少，但是具有更好的跨浏览器兼容性(在Internet Explorer 8的支持下)。

* DOM Level 2 Events (addEventListener(), etc.) 更强大，但也可以变得更加复杂，并且支持不足（只支持到Internet Explorer 9）。
```js
btn.addEventListener('click', bgChange);
btn.removeEventListener('click', bgChange);
```

### Web API
面向对象
#### 文档对象模型（Document Object Model）
<img src="https://mdn.mozillademos.org/files/14557/document-window-navigator.png">

对象|方法
----|--------
`Windows` | `innerWidth` `innerHeight` [event handler](#事件机制处理选择)
`Navigator` | 浏览器存在于web上的状态和标识（即用户代理）
`Document` | `querySelector` `createTextNode` `Node.textContent` `Node.appendChild`

Element 是一个通用性非常强的基类，所有 Document 对象下的对象都继承自它。

DOM模型用一个`逻辑树`来表示一个文档(html-body-div-...)，树的每个分支的终点都是一个节点(node)，每个节点都包含着对象(objects)。
DOM的方法(methods)让你可以用特定方式操作这个树，用这些方法你可以改变文档的结构、样式或者内容。
节点可以关联上`事件处理器`，一旦某一事件被触发了，那些事件处理器就会被执行。

```js
var link = document.querySelector('a');
/*会匹配第一个a,
匹配是使用深度优先先序遍历，从文档标记中的第一个元素开始，并按子节点的顺序依次遍历。 document.querySelectorAll() 选择所有*/
/*document是小写，我也不知道为什么，有的API是大写，有的是小写*/
link.textContent = 'Mozilla Developer Network';//更新链接上的文字
link.href = 'https://developer.mozilla.org';//更新href
var sect = document.querySelector('section');
var para = document.createElement('p');//创建新的节点
para.textContent = 'We hope you enjoyed the ride.';
sect.appendChild(para);//appendChild方法用来在后面增加
var text = document.createTextNode(' — the premier source for web development knowledge.');
var linkPara = document.querySelector('p');
linkPara.appendChild(text);
sect.appendChild(linkPara);//直接将linkpara移到sect下，不保留副本
sect.removeChild(linkPara);//删除节点
linkPara.parentNode.removeChild(linkPara);//无法删除自己

/*** 操作样式 ***/

para.style.color = 'white';
para.style.backgroundColor = 'black';
para.style.padding = '10px';
para.style.width = '250px';
para.style.textAlign = 'center';
/** another way to operate css **/
para.setAttribute('class', 'highlight');
/* highlight is the name of a class whoes style has been defined previously */

```

