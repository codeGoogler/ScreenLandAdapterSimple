
####限定符适配手机与平板之不同尺寸屏幕(持续更新中...)《五》
##### 使用限定符来适配平板和手机


#### 一、背景

　　Android的开源使厂商无需自行研发OS，大大降低了研发、生产的成本，使得Android平板品牌如雨后春笋般爆发，山寨机厂商们似乎又找到了一丝希望。与此同时带来的是广大开发者的苦不堪言，各种神奇的小板儿考验着app的兼容性，各种定制的rom不经意间就让app崩溃，光是界面上的调整就已经够你喝一壶了，是不？




效果图：

###### 平板
![一套APP适配平板和手机](http://upload-images.jianshu.io/upload_images/4614633-f6decfc5978cde5a.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



###### 手机

![一套APP适配平板和手机](http://upload-images.jianshu.io/upload_images/4614633-2f789151d9743ea6.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 什么是限定符：

![什么是限定符](http://upload-images.jianshu.io/upload_images/4614633-2077d329f77c5bdc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 二、适配可行性

　　早在Android设计之初就考虑到了这一点，为了让app适应标准or山寨屏幕，google已经有一套成熟的解决方案。其中，有这么几个指标需要注意：

　　（1）屏幕尺寸：单位inch，指的是屏幕对角线长度。

　　（2）屏幕密度：单位dpi，指的是每inch上可以显示多少像素点即px。

　　（3）屏幕分辨率：单位px * px，指的是一屏显示多少像素点。

　　（4）屏幕无关像素：单位dp/dip，指的是自适应屏幕密度的像素，用于指定控件宽高。

　　（5）刻度无关像素：单位sp，指的是自适应字体的像素，用于指定文字大小。

###### 以我自己的Haier W910超级战舰(宽高比16:9)为例，上述单位的换算如下：

　　已知数据：屏幕尺寸4.5， 分辨率1280 * 720， 屏幕密度320

　　（1）16:9的4.5寸屏幕由勾股定理计算其高约为3.9寸，宽约为2.2寸

　　（2）则竖向dpi为1280 / 3.9 ≈ 328， 横向dpi为720 / 2.2 ≈ 327

　　（3）工业上切割液晶板时取整为320

######  那么既然dpi是自适应屏幕密度的，与px之间又是如何换算呢：
密度|转换
---|---
120dpi(ldpi低密度屏)　　 |      1dp = 0.75px        (由于像素点是物理点，所以用2个像素点来显示3个dp的内容)
160dpi(mdpi中密度屏)| 1dp = 1px
213dpi(tvdpi电视密度屏) | 1dp = 1.33px
240dpi(hdpi高密度屏) | 1dp = 1.5px
320dpi(xhdpi极高密度屏) | 1dp = 2px

　　由上述分析结果可知，控件使用dp，文字使用sp即可满足自适应的需求。

#### 三、适配方案

　　根据目前的调查，在市面上的平板，基本上属于mdpi和hdpi的，少数属于tvdpi(如google出的nexus7)，所以我们选择这三种密度考虑适配；此外手机应用大多数都是竖屏使用，但平板作为娱乐性的一款产品，横竖屏均有使用的时候，所以我们还需要考虑到屏幕状态进行适配；最后考虑到有的rom会将虚拟键计算到屏幕尺寸里，还要考虑到虚拟键所占用的长宽。

　　那么如何根据这三个属性来进行适配呢？Android在资源文件values用文件名的方式提供了限定符可以帮助我们判断上述情况，限定符(mdpi,tvdpi,hdpi)可以帮助我们判断屏幕密度，限定符(land,port)可以帮助我们区分屏幕横竖屏状态，而限定符(1024x600...)可以适配计算虚拟键或者不计算虚拟键的屏幕，限定符的详细说明请参见Android SDK文档中开发者指南的Supporting Multiple Screens话题。

##### 最终适配文件夹如下图所示：

　　

　　注1：分辨率限定符的匹配是向下匹配，如果没有values-land-mdpi-1024x552，比如，分辨率values-land-mdpi-1024x600的屏幕，当rom不把虚拟键计算到屏幕尺寸时，实际显示的屏幕应该是values-land-mdpi-1024x552，无法适配到values-land-mdpi-1024x600，那这样就可能适配到下一级，比如values-land-mdpi-800x480，但是现在的平板已经没有这么低的分辨率了，所以是配到无限定符的values-mdpi里，造成界面显示上的瑕疵。

　　注2：由于分辨率限定符的匹配是向下匹配，所以如果有非主流mdpi屏幕不能精确适配到上述指定值时，values-mdpi至少可以保证app运行时不至于崩溃，同理values可以保证ldpi屏幕的平板不会因生成view而又取不到相应值而崩溃。
　　
　　
　　
##### 使用最小宽度限定符：

1、sw Small Width(设备上最小的一条边)

2、res/layout-sw600dp/???.xml 7寸屏的

3、res/layout-sw720dp/???.xml 10寸屏的

eg:

```
res/layout/main_activity.xml #手机布局
res/layout-sw600dp/main_activity.xml #7寸平板布局
res/layout-sw720dp/main_activity.xml #10寸平板布局
```

好处：这样做的好处是：不需要做两套APP，只需一套APP，当其运行在不一样的设备的时候，或自动的选取不同尺寸相关的文件。就能在不同尺寸的

##### 关于老版本兼容的问题

使用尺寸限定符（3.2版本以前的才用res/layout-large/???.xml）

eg:

![平板尺寸](http://upload-images.jianshu.io/upload_images/4614633-e2894514a1ada099.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### 关于更多

[破解Android版微信跳一跳,一招教你挑战高分](https://mp.weixin.qq.com/s/COKX3fWNEszas6sA0hs3Lg)\

[热修复实战-史上讲解最详细的文章，强烈推荐](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001255&idx=1&sn=6b1674c7578039b61b4c34825619c363&chksm=6b4769795c30e06fa1d02f89e7a3e230c2d9c5761b0256fd1ed33eee899803a95f574a144450#rd)

[NDK项目实战—高仿360手机助手之卸载监听](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247484610&idx=1&sn=d1b0805b95718cdd1dcb4b73d619d269&chksm=eb47685cdc30e14a6edb8a560f2b72bd66b566f2d23b120fa8b0d49607981687776c00cf3dd9&scene=21#wechat_redirect)

[一个强大的AutoLifecycle—让普通 Java 类自动感知 Activity Lifecycle](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001227&idx=1&sn=c3908e6cf25ab3e03f50e2cc7e73dd52&chksm=6b4769555c30e043d4da6697701a5a0f3c513f6a90a8f9dbb99f52a0ade5981f224b206f9c2b#rd)


[腾讯，阿里，美团等技术团队的精品文章推送](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001248&idx=1&sn=4a202b5529bb91da060117ab36cb3f5a&chksm=6b47697e5c30e06880782a1d11e37b318ba09a6ee566a10796eab8ff1f35d5487f4b95710cf5#rd)


[为什么成为一名程序员这么困难？ — 从程序新手到准工程师的必经之路](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001236&idx=1&sn=ad944cd0ea02755f692d6dc83d4932cc&chksm=6b47694a5c30e05c001b67b37937dd1b02e7a7aa841027041912f8d3943041981ce25be6b228#rd)

[给应用开发者编程的十一条个建议](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001232&idx=1&sn=56a863ab9d1bd3c2baadbc04727d9e4b&chksm=6b47694e5c30e058e98ddab65023432c120946d5d10ab42c5cad53f55db1bbb0f2bc042cf234#rd)


[用心选择你想要的福利，终端研发部感谢一路有你](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001226&idx=1&sn=e3106e9699c11cbe3f071699ee10b14e&chksm=6b4769545c30e04211a26993eb0326889447a31613fde0bfeeba374de5f981638a4946c11bda#rd)



[Android 图片选择到裁剪之步步深坑](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247484873&idx=1&sn=ff61bb74db725970d939a7b40ab0e06e&chksm=eb476957dc30e0417f04e9463949482d52ec30e181d38029f0dd18388b58448d067404678839&scene=21#wechat_redirect)

[我的地盘我做主—教你玩转Python函数和变量](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001216&idx=1&sn=f58aca5dc1fe615986ab88007fdd93e8&chksm=6b47695e5c30e048ef78ff9f2f71ad9c9c86c899db59bc7804d6aba6a2162a5ba1798671cc3e#rd)

#### 相信自己，没有做不到的，只有想不到的

如果你觉得此文对您有所帮助， 欢迎加入微信公众号：终端研发部

![技术+职场](http://upload-images.jianshu.io/upload_images/4614633-c7bfc4524230be96?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)