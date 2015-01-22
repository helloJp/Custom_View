自定义控件
===========

#1、MyTopbar 标题栏(组合控件）<br>
#2、SlidingMenu<br>
#3、imooc_weixin_6_0<br>
>代码来自：http://blog.csdn.net/lmj623565791/article/details/41087219
#4、ListViewLoadDemo（listView分页加载更多）
```
   <me.jp.view.LoadListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        android:cacheColorHint="#00000000"
        android:dividerHeight="5dip" />
```
<br>
```Java
	listview = (LoadListView) findViewById(R.id.listview);
	listview.setOnLoadMoreListener(this);
```	
<br>
```Java
// TODO 获取更多数据
// TODO 更新listview显示；
// 通知listview加载完毕
listview.loadComplete();
```


