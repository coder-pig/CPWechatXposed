<p  align="left">
    <a href="https://github.com/coder-pig/"><img alt="logo" width="36" height="36" src="res/head_icon.png" alt="coderpig">
    </a>
</p>

<p  align="center">
<img src="res/project_icon.jpg">
</p>

<p align="center" style="margin: 30px 0 35px;">使用Xposed Hook微信等APP</p>



**`CPWechatXposed`** 是笔者折腾Xposed插件的一些记录，Xposed通过Hook应用相关的方法来为应用添加一些附加的<br>
功能。本项目仅用于技术研究学习之用，请勿用于商业用途，所以项目不会做任何适配！只保证在笔者手机上是可以<br>
正常运行的，代码开源，有问题或者建议欢迎提issues。另外，使用Xposed插件的手机需要root，如果不想root，<br>
可以自行搜索VirtualXposed和EXposed相关的资料。


## Dev Environment

- Java 1.8.0_91
- Android Studio 3.3.2
- Machine：Moto xt1085(Android 5.1)，Meizu M2 E(Android 6.0.1)

## Feature

笔者想做，正在做，已经做完的一些功能（可以提需求，但超懒，随缘实现，莫催，顺序不代表优先级！）

- 1、Hook王者荣耀获取手机系统机型的方法，改为OV系统，以此畅玩高帧率模式（已实现）
- 2、Hook计步传感器，自定义设置倍率1-1000，走1步等于别人走1000步（已实现）
- 3、Hook微信猜拳和投骰子，想出什么，就出什么（已实现）
- 4、Hook下厨房检测手机是否安装了Xposed的方法，以此去掉恶心的重复弹出警告对话框（已实现）
- 5、Hook微信，实现聊天信息防撤回（已实现）
- 6、Hook微信运动，一键点赞所有好友。
- 7、Hook微信，秒抢红包。
- 8、Hook微信，未读信息清0，小红点强迫症患者福音。
- 9、Hook微信朋友圈，实现防删除，评论防删除。
- 10、Hook微信朋友圈，添加特别关注，特别关注的小姐姐发动态时候，秒赞。
- 11、Hook微信朋友圈假点赞，衣食住行，分享票圈享优惠福音。
- 12、Hook微信，添加不常用群聊分组，把不想看，又不能退的群收纳其中，还你一片净土。
- 13、Hook微信，获取某个好友朋友圈所有的数据。
- 14、Hook微信，突破双端登录限制，实现多个手机登录同一个微信号。
- 15、Hook企业微信，秒抢红包

## ScreenShot

插件页面截图如下：

![](http://static.zybuluo.com/coder-pig/5pcx5xzg1t1urbh2qsmf6er5/1.png)

## Article

笔者在折腾Xposed里的一些记录文章，想了解和学习Xposed可以看看研究研究~


- [抱歉，Xposed真的可以为所欲为——1.基础知识储备](https://juejin.im/post/5ad9886df265da0b776f3dcb)
- [抱歉，Xposed真的可以为所欲为——2.改为OV机型流畅玩耍高帧率王者农药](https://juejin.im/post/5adafc1ef265da0b83365204)
- [抱歉，Xposed真的可以为所欲为——3.微信运动占领封面出售广告位](https://juejin.im/post/5adf1d846fb9a07abb234015)
- [抱歉，Xposed真的可以为所欲为——4.猜拳投骰子你能赢算我输](https://juejin.im/post/5ae1fe0e518825672a02a248)
- [抱歉，Xposed真的可以为所欲为——5.我自己刷的Xposed凭什么不给我用](https://juejin.im/post/5b167a665188257d86687b22)
- [抱歉，Xposed真的可以为所欲为——6.你的表白撤不回了](https://juejin.im/post/5b2e2ba451882574e94f05eb)

## Tips

> 
对于一些比较大的apk，直接使用jadx反编译的话，很容易直接卡死。笔者采用的方法：
> 
- 1、把apk后缀名修改为zip。
- 2、解压某个文件夹中。
- 3、多次调用 **jadx -d 输出目录 dex文件** 去反编译每个dex文件。
- 4、把所有反编译后的文件夹汇总到一个目录下，然后Android Studio打开这个文件夹。
> 
> 因为操作比较机械和繁琐，索性用Python写了个批处理脚本，读者只需把工程目录下的<br>**`auto_extract_apk.py`** 文件和 **需要反编译的apk** 拷贝到 **`jadx\build\jadx\bin`** 目录下，<br>
如下图所示：
> 
> ![](http://static.zybuluo.com/coder-pig/z09hudoorfh43vdj6ijjxryc/2.png)
> 
> 接着终端cd到这个目录下，键入：**`python auto_extract_apk.py`** 执行脚本，等待片刻后<br>
> 反编译后的代码会保存到 **output** 目录下。然后使用Android Studio打开此目录即可。
> 
> 因为用到Python，所以需要安装Python环境，关于Python的安装可以移步到：<br>
> [猪行天下之Python基础——1.1 Python开发环境搭建](https://juejin.im/post/5c9f3a04e51d45210203c699)

## Join in

如果你对Android或Python有兴趣，想一起交流学习可以通过「**机器人**」或「**公众号**」进群~


![机器人小号](http://static.zybuluo.com/coder-pig/i0am4as91l5e5h097yc164xg/robot_qr_code.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![公众号](http://static.zybuluo.com/coder-pig/5bqg7c257f7o3ffppq1qb46h/public_qr_code.jpg)


## Donate

如果觉得本项目对你有所帮助，如果经济条件允许的话，可以请小猪喝杯一点点，你的支持是我完善项目的动力~

![波霸](http://static.zybuluo.com/coder-pig/w2zfc3t5hh8soqfzpftz0vgj/lqd.jpg)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![微信支付](http://static.zybuluo.com/coder-pig/0kvkf4b2nnitsiogugdhxmkw/wc_pay.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![支付宝支付](http://static.zybuluo.com/coder-pig/fqkl501p6o8zdnqd09ne8l50/zfb_pay.jpg)


## LICENSE

[GNU General Public License v3.0](http://www.gnu.org/licenses/gpl-3.0.html)


  [1]: http://static.zybuluo.com/coder-pig/z09hudoorfh43vdj6ijjxryc/2.png
  [2]: https://juejin.im/post/5c9f3a04e51d45210203c699
