#    2018冰岩作坊移动组夏令营

## 前期准备

* 学习使用git

  * 要求：自己整理git常用命令和基本操作流程，以PR的模式提交代码，每天晚上10：30前提交merge request

  * 参考资料

    [learnGitBranching](https://github.com/pcottle/learnGitBranching)

    [git-tutorial](https://git-scm.com/docs/gittutorial)

    [git教程](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

  * 建议时长：0.5 day

* 学习Markdown语法

  * 要求：熟悉使用md语法，完成每日日志，写在README.md文件中

  * 参考资料

    [markdown gitbook](http://xianbai.me/learn-md/article/syntax/paragraphs-and-line-breaks.html)

  * 建议时长：0.5 day

* Android开发环境搭建

  * IDE：Android Studio
  * JDK安装
  * SDK安装
  * 建议时长：0.5 day
  
* iOS开发环境

  * Xcode
  * Postman
  * CocoaPods
  * 建议时长:   0.5 day




## 中期准备

* Java基本语法
  * [菜鸟教程](http://www.runoob.com/java/java-basic-syntax.html)
  * 建议时长1 day

* Android相关
  * 《第一行代码》 （必读，对安卓有大致了解）
  * [学习资料](https://github.com/open-android/Android)
  * 建议时长：2 day
* 了解MVP架构
  - 要求：有基本了解，后期在写项目时有意识地模仿使用
  - 参考资料
    [MVP模式](http://www.jcodecraeer.com/a/anzhuokaifa/2017/1020/8625.html?1508484926)
  - 建议时长：1 day

* Swift / Objective-C 语法
* [UIKit](https://itisjoe.gitbooks.io/swiftgo/content/)
* [代码规范](https://www.jianshu.com/p/c818c00e0690)
* MVC & MVVN 设计模式
  * 要求 尽可能写出 可读性好 耦合度低的代码

## 实战阶段

* 一个github treding客户端

  * 功能

    * Tab1： trending，可以按照语言选择，选择时间周期，并通过webview显示仓库内容

      !![trending.png](https://i.loli.net/2018/07/04/5b3ba1f7ce8cd.png)![webview.png](https://i.loli.net/2018/07/04/5b3ba1f7dd0d3.png)

    * Tab2： popular， 可以按照语言选择，时间周期的选择，同时有搜索仓库的功能

      ![popular.png](https://i.loli.net/2018/07/04/5b3ba1f6afc74.png)![search.png](https://i.loli.net/2018/07/04/5b3ba1f736f2b.png)

    * Tag3： favorite， 展示自己收藏的仓库

      ![favorite](https://i.loli.net/2018/07/04/5b3ba324388d4.png)

    * Tab4： settiing， 管理trending和popular界面所展示的子tag，已及切换主题

      !![language.png](https://i.loli.net/2018/07/04/5b3ba1f61a0f1.png)![setting.png](https://i.loli.net/2018/07/04/5b3ba1f736e04.png)![theme.png](https://i.loli.net/2018/07/04/5b3ba1f73994f.png)

* 接口 

  API_URL：https://github.com/

  * trending

    /trending?since=daily  

    * 该接口只能获得trending的html页面，需要自己解析页面，获得*开源项目名*，*项目描述*

  API_URL: https://api.github.com/

  * popular

    /search/repositories

    * 参数说明
      * 查询所有的:q=stars:>1&sort=stars
      * 分类查询：q=ios&sort=stars 

* 可选项：

  * github API文档

    https://developer.github.com/v3/

    登陆获取个人信息，新增个人中心界面，展示用户头像等信息，可star　trending和popular中的仓库



---------深入学习资料待更新---------

## 数据结构

* 链表

  * 增删改查

  * 单向链表
  * 双向链表

* 队列

  * 入队出队

  * 简单队列
  * 单调队列

* 栈

  * 进栈出栈
  * 栈的应用

* 堆

  * 增删改查

  * 大根堆
  * 小根堆

* 树

  * 二叉树
  * AVL树
  * 红黑树

## 算法

* Hash
  * hash冲突解决

* kmp，字符串匹配

* 动态规划

* 深搜、广搜、记忆化搜索

* 分治

    [Learn-Alogrithms](https://github.com/nonstriater/Learn-Algorithms)

    [awesome-algorithms](https://github.com/tayllan/awesome-algorithms)

## 计算机网络

* http

  推荐书籍：http权威指南

* https

* tcp/ip 三次握手，四次挥手，常见的拥塞控制机制

  推荐书籍：《TCP/IP详解 卷一》

* 大致了解下其他协议

## 操作系统

* 建议以学习Linux为主

* 清楚开机过程是如何将电脑的控制权交给操作系统

* 操作系统逻辑结构

* 进程管理
  * 进程线程的概念
  * 进程间的同步与互斥的方式，如P-V操作
  * 中断机制

* 储存管理
  * 地址映射
  * 虚拟映射
  * 页式存储管理原理

* Linux的设备管理

* Linux的文件系统

* 推荐书籍

  《Operating System Concepts》 