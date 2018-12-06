package guopuran.bwie.com.space04;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import guopuran.bwie.com.space04.fragmnet.NewsFragment;
import guopuran.bwie.com.space04.fragmnet.ZxingFragment;

public class FragAdapter extends FragmentPagerAdapter {
    private String[] string=new String[]{
            "我的数据","我的名片"
    };
    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new NewsFragment();
            case 1:
                return new ZxingFragment();
                default:
                    break;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return string[position];
    }

    @Override
    public int getCount() {
        return string.length;
    }
}
