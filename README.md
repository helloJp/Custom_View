自定义控件
===========

#1、MyTopbar 标题栏(组合控件）<br>
#2、SlidingMenu<br>
#3、imooc_weixin_6_0<br>
代码来自：http://blog.csdn.net/lmj623565791/article/details/41087219<br>
设置Paint的Xfermode为：Mode.DST_IN。通过Canvas绘图
![](https://github.com/HubDroid/Custom_View/blob/master/art%2Fimooc_weixin6_0_pic1.gif)


#4、ListViewLoadDemo（listView分页加载更多）
###1、布局文件使用自定义listView
```
   <me.jp.view.LoadListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        android:cacheColorHint="#00000000"
        android:dividerHeight="5dip" />
```
###2、为listView设置监听
```Java
	listview = (LoadListView) findViewById(R.id.listview);
	listview.setOnLoadMoreListener(this);
```	
###3、实现OnLoadMoreListener方法
```Java
@Override
public void onLoad() {
	// TODO 获取更多数据
	// TODO 更新listview显示；
	// 通知listview加载完毕
	listview.loadComplete();
}
```


