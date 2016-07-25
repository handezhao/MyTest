MyTest
====================

平时的一些测试小用例
---------------------
### ListView 中带pading的分割线
    在drawable下新建 listview_divider_inset.xml
    <?xml version="1.0" encoding="utf-8"?>
    <inset xmlns:android="http://schemas.android.com/apk/res/android" 
    android:insetLeft="15dp"
    android:insetRight="15dp"
    android:drawable="@color/listview_divider">
    </inset>
    
    在ListView中引用即可:
    <ListView 
    android:id="@+id/lv_test"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:layout_below="@id/titlebar"
    android:scrollbars="none"
    android:divider="@drawable/listview_divider_inset"
    android:dividerHeight="0.35dp"
    />
    
    效果如下：
![github](https://github.com/handezhao/MyTest.git/picture/divider.png)
