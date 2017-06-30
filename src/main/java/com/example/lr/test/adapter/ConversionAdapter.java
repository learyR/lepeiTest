package com.example.lr.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lr.test.R;
import com.example.lr.test.entity.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lr on 2017/6/19.
 */

public class ConversionAdapter extends RecyclerView.Adapter {
    ArrayList<Message> conversion;
    Context context;
    RecyclerView parent;

    public ConversionAdapter(ArrayList<Message> conversion, Context context) {
        this.conversion = conversion;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = (RecyclerView) parent;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            view = inflater.inflate(R.layout.item_conversion2, parent, false);
            holder = new ConversionRightViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_conversion, parent, false);
            holder = new ConversionViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = conversion.get(position);
        if (message.isSend()) {
            ConversionRightViewHolder conversionViewHolder = (ConversionRightViewHolder) holder;
            fromRight(position, message, conversionViewHolder);
        } else {
            ConversionViewHolder conversionViewHolder = (ConversionViewHolder) holder;
            fromLeft(position, message, conversionViewHolder);
        }
    }

    /**
     * 对方
     * @param position
     * @param message
     * @param conversionViewHolder
     */
    private void fromLeft(int position, Message message, ConversionViewHolder conversionViewHolder) {
        conversionViewHolder.text.setText(message.getMsg());
        Glide.with(context).load(message.getUserAvatorUrl()).into(conversionViewHolder.avatar);
        conversionViewHolder.userNick.setText("jack");
        conversionViewHolder.ll.setVisibility(View.VISIBLE);
        if (position != 0) {
            String showTime = getTime(message.getTime(), conversion.get(position - 1)
                    .getTime());
            if (showTime != null) {
                conversionViewHolder.time.setVisibility(View.VISIBLE);
                conversionViewHolder.time.setText(showTime);
            } else {
                conversionViewHolder.time.setVisibility(View.GONE);
            }
        } else {
            String showTime = getTime(message.getTime(), null);
            conversionViewHolder.time.setVisibility(View.VISIBLE);
            conversionViewHolder.time.setText(showTime);
        }
    }

    /**
     * 自己
     * @param position
     * @param message
     * @param conversionViewHolder
     */
    private void fromRight(int position, Message message, ConversionRightViewHolder conversionViewHolder) {
        conversionViewHolder.text.setText(message.getMsg());
        Glide.with(context).load(message.getUserAvatorUrl()).into(conversionViewHolder.avatar);
        conversionViewHolder.userNick.setText("leary");
        conversionViewHolder.ll.setVisibility(View.VISIBLE);
        if (position != 0) {
            String showTime = getTime(message.getTime(), conversion.get(position - 1)
                    .getTime());
            if (showTime != null) {
                conversionViewHolder.time.setVisibility(View.VISIBLE);
                conversionViewHolder.time.setText(showTime);
            } else {
                conversionViewHolder.time.setVisibility(View.GONE);
            }
        } else {
            String showTime = getTime(message.getTime(), null);
            conversionViewHolder.time.setVisibility(View.VISIBLE);
            conversionViewHolder.time.setText(showTime);
        }
    }

    @Override
    public int getItemCount() {
        if (conversion != null) {
            return conversion.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = conversion.get(position);
        if (msg.isSend()) {
            return 1;
        }
        return 2;
    }

    public void addData(Message text) {
        this.conversion.add(text);
        notifyDataSetChanged();
    }

    public void addDataList(List<Message> messageList) {
        this.conversion.clear();
        this.conversion.addAll(messageList);
        notifyDataSetChanged();
    }

    static class ConversionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.userNick)
        TextView userNick;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.ll)
        RelativeLayout ll;
        @BindView(R.id.time)
        TextView time;

        ConversionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ConversionRightViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nick)
        TextView userNick;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.ll)
        RelativeLayout ll;
        @BindView(R.id.time)
        TextView time;

        ConversionRightViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public String getTime(String time, String before) {
        String show_time = null;
        if (before != null) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
                java.util.Date now = df.parse(time);
                java.util.Date date = df.parse(before);
                long l = now.getTime() - date.getTime();
                long day = l / (24 * 60 * 60 * 1000);
                long hour = (l / (60 * 60 * 1000) - day * 24);
                long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
                if (min >= 1) {
                    show_time = time.substring(11);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            show_time = time.substring(11);
        }
        String getDay = getDay(time);
        if (show_time != null && getDay != null)
            show_time = getDay + " " + show_time;
        return show_time;
    }
    public String getDay(String time) {
        String showDay = null;
        String nowTime = returnTime();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
            java.util.Date now = df.parse(nowTime);
            java.util.Date date = df.parse(time);
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            if (day >= 365) {
                showDay = time.substring(0, 10);
            } else if (day >= 1 && day < 365) {
                showDay = time.substring(5, 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showDay;
    }
    public static String returnTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
