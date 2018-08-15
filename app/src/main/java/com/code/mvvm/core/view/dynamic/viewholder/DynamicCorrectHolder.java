package com.code.mvvm.core.view.dynamic.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.util.ViewUtils;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseViewHolder;

/**
 * @author：zhangtianqiu on 18/7/4 15:35
 */
public class DynamicCorrectHolder extends AbsViewHolder<DynamicInfoVo, DynamicCorrectHolder.ViewHolder> {
    private int contentWidth;


    public DynamicCorrectHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(App.Instance()) - DisplayUtil.dp2px(App.Instance(), 15 + 40 + 10 + 30);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_correct;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext).load(item.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.ivUserPic);
        holder.mUserTag.removeAllViews();
        if (!TextUtils.isEmpty(item.userinfo.province) && !TextUtils.equals("false", item.userinfo.province)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext,item.userinfo.province));
        }
        if (!TextUtils.isEmpty(item.userinfo.profession)
                && !TextUtils.equals("false", item.userinfo.profession)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext,item.userinfo.profession));
        }
        holder.userType.setText(item.title);
        holder.lookNum.setText("");
        holder.dynamicTitle.setText(item.correct_info.content);
        int mWidth = (contentWidth - DisplayUtil.dp2px(App.Instance(), 14 * 2)) * 2 / 3;
        //图片url
        String pic_url;
        if (item.correct_info.status.equals("0")) {
            pic_url = item.correct_info.source_pic.img.l.url;
            float dv = (float) item.correct_info.source_pic.img.l.h / (float) item.correct_info.source_pic.img.l.w;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, (int) (dv * mWidth));
            holder.dynamicPic.setLayoutParams(params);
        } else {
            if (item.correct_info.correct_pic.img != null) {
                pic_url = item.correct_info.correct_pic.img.l.url;
                float dv = (float) item.correct_info.correct_pic.img.l.h / (float) item.correct_info.correct_pic.img.l.w;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, (int) (dv * mWidth));
                holder.dynamicPic.setLayoutParams(params);
            } else {
                pic_url = item.correct_info.source_pic.img.l.url;
                float dv = (float) item.correct_info.source_pic.img.l.h / (float) item.correct_info.source_pic.img.l.w;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, (int) (dv * mWidth));
                holder.dynamicPic.setLayoutParams(params);
            }
        }
        Glide.with(mContext).load(pic_url).placeholder(R.color.black_e8e8e8).into(holder.dynamicPic);
    }


    public static class ViewHolder extends BaseViewHolder {
        public TextView tvUserName, userType, dynamicTitle, publich_time, lookNum;
        public ImageView ivUserPic, dynamicPic;
        public LinearLayout mUserTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ivUserPic = getViewById(R.id.iv_user_pic);
            dynamicPic = getViewById(R.id.iv_dynamic_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            publich_time = getViewById(R.id.tv_publich_time);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }
}