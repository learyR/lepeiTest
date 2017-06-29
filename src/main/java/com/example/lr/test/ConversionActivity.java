package com.example.lr.test;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lr.test.adapter.ConversionAdapter;
import com.example.lr.test.bean.Message;
import com.example.lr.test.util.ScreenUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionActivity extends AppCompatActivity {

    protected InputMethodManager inputMethodManager;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.etText)
    EditText etText;
    ArrayList<Message> mConversionList;
    ConversionAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    Message message;
    String url;
    boolean isSend;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.MyConversition)
    LinearLayout myConversionsition;

    public int bottomStatusHeight = 0;
    public int listSlideHeight = 0;//滑动距离


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        ButterKnife.bind(this);
        initView();
    }

    private void initData() {
        message.setMsg(etText.getText().toString());
        if (message != null) {
            isSend = !isSend;
            message.setSend(isSend);
        }
        if (message.isSend()) {
            url = "http://img.qqzhi.com/upload/img_4_1272425814D4002644426_23.jpg";
            message.setUserNick("leary");
            message.setUserAvatorUrl(url);
        } else {
            url = "http://img.qqzhi.com/upload/img_5_2722884193D327798118_23.jpg";
            message.setUserAvatorUrl(url);
            message.setUserNick("jack");
        }
        message.setTime(returnTime());
        mAdapter.addData(message);
        if (mConversionList.size() >= 0) {
            list.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.GONE);
        }
    }

    private void initView() {
        inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        mConversionList = new ArrayList<>();
        mAdapter = new ConversionAdapter(mConversionList, this);
        mLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(mLayoutManager);
        list.setAdapter(mAdapter);
        bottomStatusHeight = ScreenUtil.getBottomStatusHeight(this);
    }

    @OnClick(R.id.send)
    public void onViewClicked() {
        message = new Message();
        initData();
        etText.getText().clear();
        etText.setHint("please write here");
        list.scrollToPosition(mConversionList.size() - 1);
        hideSoftKeyboard();
        controlKeyboardLayout(myConversionsition,srl);
    }

    /**
     * @param root             最外层布局
     * @param needToScrollView 要滚动的布局,就是说在键盘弹出的时候,你需要试图滚动上去的View,在键盘隐藏的时候,他又会滚动到原来的位置的布局
     */
    private void controlKeyboardLayout(final View root, final View needToScrollView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private Rect r = new Rect();
            @Override
            public void onGlobalLayout() {
                //获取当前界面可视部分
                ConversionActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = ConversionActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                int recyclerHeight = 0;
                if (mLayoutManager != null) {
                    recyclerHeight = mLayoutManager.getHeight();
                }
                if (heightDifference == bottomStatusHeight) {
                    needToScrollView.scrollTo(0, 0);
                } else {
                    if (heightDifference < recyclerHeight) {
                        int contentHeight = mLayoutManager == null ? 0 : mLayoutManager.getHeight();
                        if (recyclerHeight < contentHeight) {
                            listSlideHeight = heightDifference - (contentHeight - recyclerHeight);
                            needToScrollView.scrollTo(0, listSlideHeight);
                        } else {
                            listSlideHeight = heightDifference;
                            needToScrollView.scrollTo(0, listSlideHeight);
                        }
                    } else {
                        listSlideHeight = 0;
                    }
                }
            }
        });

    }


    private void hideSoftKeyboard() {
        if (this.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (this.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public String returnTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sDateFormat.format(new Date());
        return date;
    }

}