package com.tellh.android_simple_common_pulltorefreshlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    public PullToRefreshLayout mPullToRefreshView;
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    private ImageView imgDone;
    private List<String> mList;
//    private PullToRefreshView mPullToRefreshView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);

        mList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(String.valueOf(i));
        }
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(new SampleAdapter(getActivity(), R.layout.list_item, mList));
        mPullToRefreshView = (PullToRefreshLayout) rootView.findViewById(R.id.pull_to_refresh);
        createRefreshView();
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

        mPullToRefreshView.setFinishRefreshToPauseDuration(800);
        return rootView;
    }

    private View createRefreshView() {
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

    class SampleAdapter extends ArrayAdapter<String> {

        private final LayoutInflater mInflater;
        private final List<String> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<String> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, parent, false);
                viewHolder.tvNum= (TextView) convertView.findViewById(R.id.tv_num);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvNum.setText(mData.get(position));
            return convertView;
        }

        class ViewHolder {
            TextView tvNum;
        }

    }

}
