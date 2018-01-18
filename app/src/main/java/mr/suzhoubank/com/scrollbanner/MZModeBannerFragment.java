package mr.suzhoubank.com.scrollbanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import mr.suzhoubank.com.scrollbanner.holder.MZHolderCreator;
import mr.suzhoubank.com.scrollbanner.holder.MZViewHolder;

import static android.R.id.list;

/**
 * Created by zhouwei on 17/5/31.
 */

public class MZModeBannerFragment extends Fragment {
    public static final String TAG = "MZModeBannerFragment";
    private static ArrayList<String> bankDatas;
    private MZBannerView mMZBanner;


    public static MZModeBannerFragment newInstance(ArrayList<String> datas){
        bankDatas=datas;
        return new MZModeBannerFragment();
    }

    private void initView(View view) {

        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(),"click page:"+position, Toast.LENGTH_LONG).show();
            }
        });
        mMZBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG,"----->addPageChangeLisnter:"+position + "positionOffset:"+positionOffset+ "positionOffsetPixels:"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG,"addPageChangeLisnter:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        List<String> bannerList = new ArrayList<>();
        for(int i=0;i<bankDatas.size();i++){
            bannerList.add(bankDatas.get(i));
        }
        mMZBanner.setIndicatorVisible(false);//是否显示圆点
        mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });



    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private TextView tv_bank_name;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.item_cash_card_list,null);
            tv_bank_name=view.findViewById(R.id.tv_bank_name);
            return view;
        }

        @Override
        public void onBind(Context context, int position, List<String> data) {
            //绑定数据
            tv_bank_name.setText(data.get(position));
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,null);
        initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
    }
}
