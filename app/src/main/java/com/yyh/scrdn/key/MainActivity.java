package com.yyh.scrdn.key;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类功能描述：</br>
 * 限定符适配手机与平板之不同尺寸屏幕《五》
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */

public class MainActivity extends AppCompatActivity {
    FragmentManager fm =  getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_onclick01,R.id.btn_onclick02,R.id.btn_onclick03,R.id.btn_onclick04,R.id.btn_onclick05})
    public void setMyONClicl(View v){
        switch (v.getId()){
            case R.id.btn_onclick01:
                fm.beginTransaction()
                        .replace(R.id.realtabcontent,new FIrstFragment())
                        .commit();
                break;
            case R.id.btn_onclick02:
                fm.beginTransaction()
                        .replace(R.id.realtabcontent,new SecondFragment())
                        .commit();
                break;
            case R.id.btn_onclick03:
                fm.beginTransaction()
                        .replace(R.id.realtabcontent,new ThirdFragment())
                        .commit();
                break;
            case R.id.btn_onclick04:
                fm.beginTransaction()
                        .replace(R.id.realtabcontent,new FourFragment())
                        .commit();
                break;
            case R.id.btn_onclick05:
                fm.beginTransaction()
                        .replace(R.id.realtabcontent,new FiveFragment())
                        .commit();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
