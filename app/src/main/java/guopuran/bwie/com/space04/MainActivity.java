package guopuran.bwie.com.space04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import guopuran.bwie.com.space04.model.SubmitBean;
import guopuran.bwie.com.space04.presenter.IpresenterImpl;
import guopuran.bwie.com.space04.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,IView {
    private String lujing="http://www.xieast.com/api/user/login.php?username=%s&password=%s";
    private EditText phone;
    private EditText pass;
    private Button button;
    private IpresenterImpl mIpresenterImpl;
    private String name;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private CheckBox jizhu;
    private CheckBox zidong;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initView();

    }
    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    private void initView() {
        //获取资源ID
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        jizhu = findViewById(R.id.jizhu);
        zidong = findViewById(R.id.zidong);
        sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        //得到记住密码的状态值
        boolean jizhu_tai = sharedPreferences.getBoolean("jizhu_tai", false);
        if (jizhu_tai){
            String nname = sharedPreferences.getString("name", null);
            String ppass = sharedPreferences.getString("word", null);
            phone.setText(nname);
            pass.setText(ppass);
            jizhu.setChecked(true);
        }
        //得到自动登录的状态值
        boolean zidong_tai = sharedPreferences.getBoolean("zidong_tai", false);
        if (zidong_tai){
            //获得输入框的值
            String username = phone.getText().toString();
            String password = pass.getText().toString();
            mIpresenterImpl.startrequest( String.format(lujing,username,password), null,SubmitBean.class);
        }
        zidong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    jizhu.setChecked(true);
                }else{
                    edit.clear();
                    edit.commit();
                }
            }
        });
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIpresenterImpl.deatch();
    }
    //点击登录
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button:
                //获得输入框的值
                username = phone.getText().toString();
                password = pass.getText().toString();
                //判断记住密码是否勾选
                if (jizhu.isChecked()){
                    edit.putString("name", username);
                    edit.putString("word", password);
                    edit.putBoolean("jizhu_tai",true);
                    edit.commit();
                }else{
                    edit.clear();
                    edit.commit();
                }
                //判断自动登录是否勾选
                if (zidong.isChecked()){
                    edit.putBoolean("zidong_tai",true);
                    edit.commit();
                }
                mIpresenterImpl.startrequest( String.format(lujing, username, password), null,SubmitBean.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void getdata(Object data) {
        SubmitBean submitBean = (SubmitBean)data;
        String msg = submitBean.getMsg();
        name = submitBean.getData().getName();
        if (submitBean.getCode()==100){
            Toast.makeText(this,msg , Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,msg , Toast.LENGTH_SHORT).show();
        }
    }


}
