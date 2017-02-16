package com.wangj.fragmentshowhide;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.LinearLayout content;
    private android.widget.ImageView cameraiv;
    private android.widget.TextView cameratv;
    private android.widget.LinearLayout cameralayout;
    private android.widget.ImageView frendiv;
    private android.widget.TextView frendtv;
    private android.widget.LinearLayout frendlayout;
    private FragmentManager fm;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //朋友圈
        this.frendlayout = (LinearLayout) findViewById(R.id.frend_layout);
        this.frendtv = (TextView) findViewById(R.id.frend_tv);
        this.frendiv = (ImageView) findViewById(R.id.frend_iv);
        //相机
        this.cameralayout = (LinearLayout) findViewById(R.id.camera_layout);
        this.cameratv = (TextView) findViewById(R.id.camera_tv);
        this.cameraiv = (ImageView) findViewById(R.id.camera_iv);

        this.content = (LinearLayout) findViewById(R.id.content);

        this.frendlayout.setOnClickListener(this);
        this.cameralayout.setOnClickListener(this);

        initData();
    }

    private void initData() {
        fm = getFragmentManager();
        this.cameraiv.setImageResource(R.drawable.ic_camera_alt_red_24dp);
        this.cameratv.setTextColor(getResources().getColor(R.color.colorAccent));
        showFragment(1);

    }

    @Override
    public void onClick(View view) {
        // 当发生点击时，先清除状态，这里的状态指的是布局里面的图片和文字
        clearState();
        switch (view.getId()) {
            case R.id.camera_layout:
                this.cameraiv.setImageResource(R.drawable.ic_camera_alt_red_24dp);
                this.cameratv.setTextColor(getResources().getColor(R.color.colorAccent));
                showFragment(1);
                break;
            case R.id.frend_layout:
                this.frendiv.setImageResource(R.drawable.ic_frend_red_24dp);
                this.frendtv.setTextColor(getResources().getColor(R.color.colorAccent));
                showFragment(2);
                break;
        }
    }

    private void clearState() {
        this.cameraiv.setImageResource(R.drawable.ic_camera_alt_blue_24dp);
        this.frendiv.setImageResource(R.drawable.ic_frend_blue_24dp);
        this.cameratv.setTextColor(getResources().getColor(R.color.colorPrimary));
        this.frendtv.setTextColor(getResources().getColor(R.color.colorPrimary));

    }

    public void showFragment(int index) {
        FragmentTransaction ft = fm.beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);

        switch (index) {
            case 1:
                // 如果fragment1已经存在则将其显示出来
                if (fragment1 != null)
                    ft.show(fragment1);
                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    fragment1 = new Fragment1();
                    ft.add(R.id.content, fragment1);
                }
                break;
            case 2:
                if (fragment2 != null)
                    ft.show(fragment2);
                else {
                    fragment2 = new Fragment2();
                    ft.add(R.id.content, fragment2);
                }
                break;
        }
        ft.commit();
    }


    // 当fragment已被实例化，相当于发生过切换，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (fragment1 != null)
            ft.hide(fragment1);
        if (fragment2 != null)
            ft.hide(fragment2);
    }
}
