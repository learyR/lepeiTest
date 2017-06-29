package com.example.lr.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lr.test.adapter.MyPagerAdapter;
import com.example.lr.test.fragment.HomePageFragment;
import com.example.lr.test.fragment.PersonalFragment;
import com.example.lr.test.fragment.RechargeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    Fragment[] mFragment;
    @BindView(R.id.rg)
    RadioGroup radioGroup;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment> list;
    FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        HomePageFragment homePageFragment = new HomePageFragment();
        PersonalFragment personalFragment = new PersonalFragment();
        RechargeFragment rechargeFragment = new RechargeFragment();
        mFragment = new Fragment[3];
        mFragment[0] = homePageFragment;
        mFragment[1] = rechargeFragment;
        mFragment[2] = personalFragment;

        list = new ArrayList<>();
        list.add(homePageFragment);
        list.add(personalFragment);
        list.add(rechargeFragment);
        supportFragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyPagerAdapter(supportFragmentManager,this,list));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(this);

    }


    @OnClick({R.id.button, R.id.btn_personal, R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, ConversionActivity.class));
                break;
            case R.id.btn_personal:
                startActivity(new Intent(this, PersonalActivity.class));
                break;
            case R.id.btn1:
                 supportFragmentManager.beginTransaction()
                        .show(mFragment[0])
                        .commit();
                break;
            case R.id.btn2:
                supportFragmentManager.beginTransaction()
                        .show(mFragment[1])
                        .commit();
                break;
            case R.id.btn3:
                supportFragmentManager.beginTransaction()
                        .show(mFragment[2])
                        .commit();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                btn1.setChecked(true);
                break;
            case 1:
                btn2.setChecked(true);
                break;
            case 2:
                btn3.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
