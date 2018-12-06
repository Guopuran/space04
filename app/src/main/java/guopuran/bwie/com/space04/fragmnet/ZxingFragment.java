package guopuran.bwie.com.space04.fragmnet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import guopuran.bwie.com.space04.LoginActivity;
import guopuran.bwie.com.space04.MainActivity;
import guopuran.bwie.com.space04.R;

public class ZxingFragment extends Fragment {

    private Button button;
    private ImageView imageView;
    private String cc;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zxingfrag,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.back);
        imageView = view.findViewById(R.id.imageview);
        sharedPreferences=getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        shengcheng();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.clear();
                edit.commit();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
    //生成二维码
    private void shengcheng() {
        String cc = ((LoginActivity) getActivity()).cc();
        QRTask qrTask = new QRTask(getActivity(), imageView, cc);
        qrTask.execute(cc);

    }
    static class QRTask extends AsyncTask<String,Void,Bitmap>{
        private WeakReference<Context> mcontext;
        private WeakReference<ImageView> mimageview;

        public QRTask(Context context, ImageView imageView, String name) {
           mcontext=new WeakReference<>(context);
           mimageview=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String str=strings[0];
            if (TextUtils.isEmpty(str)){
                return null;
            }
            int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.cc);
            return QRCodeEncoder.syncEncodeQRCode(str,size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
           if (bitmap!=null){
               mimageview.get().setImageBitmap(bitmap);
           }else{
               Toast.makeText(mcontext.get(), "生成失败", Toast.LENGTH_SHORT).show();

           }

        }
    }
}
