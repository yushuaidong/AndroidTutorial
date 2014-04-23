package com.chen.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * Time: 0:22
 * Info:
 *                                      Service“入门到精通”
 *
 * =============================================================================================
 * 一、什么是Service？
 *      Service是Android的四大组件之一，主要用于在后台处理一些耗时的逻辑，或者去执行某些需要长期运行的任务。
 *  必要的时候我们甚至可以在程序退出的情况下，让Service在后台继续保持运行状态。
 *
 *      注意：项目中的每一个Service都必须在AndroidManifest.xml中注册，
 *            注册形式：<service android:name=""...></service>
 *            节点属性说明：
 *            android:name      ---服务类名
 *            android:label     ---服务的名字，如果此项不设置，那么默认显示的服务名则为类名
 *            android:icon      ---服务的图标
 *            android:permission---申明此服务的权限，这意味着只有提供了该权限的应用才能控制或连接此服务
 *            android:process   ---表示该服务是否运行在另外一个进程，如果设置了此项，那么将会在包名后面加上这段字符串表示另一进程的名字
 *            android:enabled   ---如果此项设置为 true，那么Service将会默认被系统启动,不设置默认此项为false
 *            android:exported  ---表示该服务是否能够被其他应用程序所控制或连接，不设置默认此项为 false
 *
 * =============================================================================================
 *
 * 二、Service的分类：
 *
 *  1、按执行地方可以分为：本地服务（Local Service）和远程服务(Remote Service)
 *
 *     本地服务：该服务依附在主进程上，主进程被Kill后，服务便会终止
 *     远程服务：该服务是独立的进程，会占用一定资源，并且使用AIDL(Android接口定义语言)进行IPC(跨进程通信)稍微麻烦些
 *
 *     #其实remote服务还是很少见的，并且一般都是系统服务#
 *  -------------------------------------------------------------------------------------------
 *  2、按运行类型分类: 后台服务和前台服务
 *
 *     后台服务：默认的服务即为后台服务，即不会在通知一栏显示正在执行的服务
 *     前台服务：会在通知一栏显示 ONGOING 的 Notification
 *
 * --------------------------------------------------------------------------------------------
 *  3、按使用方式分类
 *
 *     启动服务(startService): 主要用于启动一个服务执行后台任务，不进行通信。停止服务使用stopService
 *     绑定服务(bindService)：该方法启动的服务要进行通信。停止服务使用unbindService
 *     启动服务和绑定服务同时使用：停止服务应同时使用stepService与unbindService
 *
 * ==========================================================================================
 *
 * 三、Service的生命周期
 *
 *  1、startService:
 *
 *      onCreate() -> onStartCommand() -> |Service running | -> |Service stop| -> onDestroy()
 *
 *  2、bindService:
 *
 *      onCreate() -> onBind() -> |Service running| -> |all client unbind| -> onUnbind() -> onDestroy()
 *
 *  ==========================================================================================
 *
 * 四、Service和Activity的通信
 *
 *    startService开启的Service和Activity的关系其实并不大，只是Activity通知了Service一下可以启动了，
 *    如果要让它们有关联，那么onBind()就是用于Service和Activity建立关联的，
 *    具体做法：
 *    1、重写onBind()
 *    2、创建一个类继承Binder，添加目标代码（如下载音乐等等）
 *    3、在Activity创建创建了一个ServiceConnection的匿名类，
 *       在里面重写了onServiceConnected()方法和onServiceDisconnected()方法，
 *       这两个方法分别会在Activity与Service建立关联和解除关联的时候调用
 *    4、调用bindService(bindIntent, connection, BIND_AUTO_CREATE)
 *       注：第三个参数是一个标志位，这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
 *       这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行。
 *    5、unbindService(connection);
 *
 *  注：另外需要注意，任何一个Service在整个应用程序范围内都是通用的，即MyService不仅可以和MainActivity建立关联，
 *      还可以和任何一个Activity建立关联，而且在建立关联时它们都可以获取到相同的MyBinder实例
 *
 *  ==========================================================================================
 *
 * 五、Service和Thread得区别
 *
 *    一句话：Service和Thread之间没有任何关系！！！！！！！
 *
 *    说明：之所以有不少人会把它们联系起来，主要就是因为Service的后台概念。Thread我们大家都知道，
 *         是用于开启一个子线程，在这里去执行一些耗时操作就不会阻塞主线程的运行。而Service我们最初理解的时候，
 *         总会觉得它是用来处理一些后台任务的，一些比较耗时的操作也可以放在这里运行，这就会让人产生混淆了。
 *         但是Service其实是运行在主线程里的。不要把后台和子线程联系在一起！！！
 *
 *  ==========================================================================================
 *
 *  六、未完待续...
 *
 *  ==========================================================================================
 */
public class Service01 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
