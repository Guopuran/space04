package guopuran.bwie.com.space04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import guopuran.bwie.com.space04.model.NewsBean;

public class NewsAdapter extends BaseAdapter {
    private List<NewsBean.Data> list;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<NewsBean.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewsBean.Data getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.newslist,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.getdata(getItem(position));
        return convertView;
    }
    class ViewHolder{
        private TextView title;
        private ImageView imageView;

        public ViewHolder(View convertView) {
            title=convertView.findViewById(R.id.title);
            imageView=convertView.findViewById(R.id.image);
            convertView.setTag(this);
        }

        public void getdata(NewsBean.Data item) {
            title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),imageView);
        }
    }
}
