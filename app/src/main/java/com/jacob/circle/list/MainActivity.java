package com.jacob.circle.list;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private ListView mListView;
    private List<AppBean> mAppBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mListView = (ListView) findViewById(R.id.listView);
        MyListAdapter adapter = new MyListAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < mListView.getChildCount(); i++) {
                    mListView.getChildAt(i).invalidate();
                }
            }
        });
    }

    private void initData() {
        AppBean appBean1 = new AppBean("YouToBe", R.drawable.ic_menu_1);
        AppBean appBean2 = new AppBean("Android", R.drawable.ic_menu_2);
        AppBean appBean3 = new AppBean("Yahoo", R.drawable.ic_menu_3);
        AppBean appBean4 = new AppBean("Twitter", R.drawable.ic_menu_4);
        AppBean appBean5 = new AppBean("TomCat", R.drawable.ic_menu_5);
        AppBean appBean6 = new AppBean("AngryBird", R.drawable.ic_menu_6);
        for (int i = 0; i < 10; i++) {
            mAppBeanList.add(appBean1);
            mAppBeanList.add(appBean2);
            mAppBeanList.add(appBean3);
            mAppBeanList.add(appBean4);
            mAppBeanList.add(appBean5);
            mAppBeanList.add(appBean6);
        }
    }


    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppBeanList.size();
        }

        @Override
        public AppBean getItem(int position) {
            return mAppBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView;
            if (convertView == null) {
                listItemView = new ListItemView(getApplication());
                convertView = listItemView;
            } else {
                listItemView = (ListItemView) convertView;
            }
            listItemView.setParentHeight(mListView.getHeight());
            AppBean appBean = getItem(position);
            listItemView.setAppBean(appBean);
            return convertView;
        }
    }

}
