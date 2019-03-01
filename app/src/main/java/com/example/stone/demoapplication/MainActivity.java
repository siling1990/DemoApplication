package com.example.stone.demoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.stone.demoapplication.adapter.HomeMainAdapter;
import com.example.stone.demoapplication.entity.HomeItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recMain;
    private HomeMainAdapter homeMainAdapter;
    private List<HomeItemBean> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        setContentView(R.layout.activity_main);
        initData();

        initView();
    }

    private void initView(){
        recMain = findViewById(R.id.rec_main);
        //recMain.setLayoutManager(new LinearLayoutManager(this));
        recMain.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recMain.setItemAnimator(new DefaultItemAnimator());

        homeMainAdapter = new HomeMainAdapter(dataList,this);
        recMain.setAdapter(homeMainAdapter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    private void initData(){
        dataList=new ArrayList<>();
        HomeItemBean homeItemBean;

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);

        homeItemBean = new HomeItemBean("View滑动",ViewScrollActivity.class);
        dataList.add(homeItemBean);
    }
}
