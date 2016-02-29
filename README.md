# Android-Simple-Common-PullToRefreshLayout
This is a light-weight Android PullToRefreshLayout, can be applied to any scrollable view.<br>
这是一个轻量级的Android下拉刷新控件，可以应用在任何可滚动的View 当中。
#Effect：
![](https://github.com/TellH/Android-Simple-Common-PullToRefreshLayout/blob/master/gif/lv1.gif)
# Feature
## Light-weight 轻量级
This Widget only have a class file（PullToRefreshLayout.java）. You can just copy it in to your project! So easy!<br>
这个组件只有一个类（PullToRefreshLayout.java），你可以将这个文件复制到项目当中就可用了。
## Custom refresh view 顶部刷新的View可以自定义
* custom animation 自定义动画<br>
PullToRefreshLayout provides 3 interface method.<br>

```
    public interface OnRefreshListener {
        void onRefresh();
        
        void onFinish();

        void onDragDistanceChange(float distance, float percent, float offset);
    }
```
void onDragDistanceChange(float distance, float percent, float offset);This method can be used to create custom animation with 
your refresh view.</br>
用onDragDistanceChange()该方法可以自定义RefreshView 的动画；
```
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListenerAdapter() {
            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                Log.d("TAG", "onRefresh() called with: " + "");
                imgDone.setVisibility(View.GONE);
                imageView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
//                        progressBar.setVisibility(View.GONE);
                    }
                }, 2000);
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onDragDistanceChange(float distance, float percent, float offset) {
                if (percent >= 1.0f) {
                    textView.setText("松开刷新");
                    imgDone.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setRotation(180);
                } else {
                    textView.setText("下拉刷新");
                    imgDone.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setRotation(0);
                }
            }
            @Override
            public void onFinish() {
                textView.setText("刷新成功");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                imgDone.setVisibility(View.VISIBLE);
            }
        });
```
* custom view with layout xml
just like this：
```
    private View createRefreshView() {
        mPullToRefreshView = (PullToRefreshLayout) rootView.findViewById(R.id.pull_to_refresh);
        View headerView=mPullToRefreshView.setRefreshView(R.layout.layout_head);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.down_arrow);
        imgDone=(ImageView)headerView.findViewById(R.id.img_done);
        imgDone.setImageResource(R.drawable.ic_check_circle_black);
        imgDone.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }
```

## Readable source code 源码易读性高
添加了非常详细的中文注释

## Support control by code to do refresh 支持代码控制自动刷新
![](https://github.com/TellH/Android-Simple-Common-PullToRefreshLayout/blob/master/gif/lv2.gif)

## when finishing refresh, the time to stick the refresh view can be set;
可以控制Refresh view在刷新完成后停留的时间
just like:
```
        mPullToRefreshView.setFinishRefreshToPauseDuration(800);
```
#Usage：
```
    <com.tellh.android_simple_common_pulltorefreshlayout.PullToRefreshLayout
        android:id="@+id/pull_to_refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        <!-- you can put any scrollable view here!-->
        <ListView
            android:id="@+id/list_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:divider="@null"
            android:dividerHeight="0dp"
            android:fadingEdge="none"/>

    </com.tellh.android_simple_common_pulltorefreshlayout.PullToRefreshLayout>
```

Enjoy it！
