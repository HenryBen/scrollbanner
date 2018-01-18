package mr.suzhoubank.com.scrollbanner;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ArrayList<String> bankDatas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        bankDatas.add("工商银行");
        bankDatas.add("农业银行");
        bankDatas.add("招商银行");
        bankDatas.add("交通银行");
        bankDatas.add("浦发银行");

        //设置沉浸式标题栏
//        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary),0);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.inflateMenu(R.menu.setting);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switchBannerMode();
                return true;
            }
        });

        Fragment fragment = MZModeBannerFragment.newInstance(bankDatas);
        getSupportFragmentManager().beginTransaction().add(R.id.home_container,fragment).commit();

        mToolbar.setVisibility(View.GONE);
    }

    /**
     * banner模式
     */
    public void switchBannerMode(){
        Fragment fragment = MZModeBannerFragment.newInstance(bankDatas);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
    }
}
