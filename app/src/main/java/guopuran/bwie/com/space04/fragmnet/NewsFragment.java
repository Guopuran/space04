package guopuran.bwie.com.space04.fragmnet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import guopuran.bwie.com.space04.NewsAdapter;
import guopuran.bwie.com.space04.R;
import guopuran.bwie.com.space04.model.NewsBean;
import guopuran.bwie.com.space04.presenter.IpresenterImpl;
import guopuran.bwie.com.space04.view.IView;

public class NewsFragment extends Fragment implements IView {
    private String lujing="http://www.xieast.com/api/news/news.php";
    private ListView listView;
    private IpresenterImpl mIpresenterImpl;
    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newsfrag,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listview);
        mIpresenterImpl=new IpresenterImpl(this);
        mIpresenterImpl.startrequest(lujing,null,NewsBean.class);
        newsAdapter = new NewsAdapter(getActivity());
        listView.setAdapter(newsAdapter);
    }

    @Override
    public void getdata(Object object) {
        NewsBean newsBean= (NewsBean) object;
        newsAdapter.setList(newsBean.getData());

    }
}
