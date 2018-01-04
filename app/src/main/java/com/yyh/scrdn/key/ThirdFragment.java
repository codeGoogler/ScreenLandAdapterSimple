package com.yyh.scrdn.key;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * 类功能描述：</br>
 * 限定符适配手机与平板之不同尺寸屏幕《五》
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class ThirdFragment extends Fragment{
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, null);
        btn = (Button)view.findViewById(R.id.button);
        btn.setText("ThirdFragment");
        return view;
    }
}
