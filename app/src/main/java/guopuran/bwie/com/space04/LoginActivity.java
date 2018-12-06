package guopuran.bwie.com.space04;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }

    private void initview() {
        TabLayout tabLayout=findViewById(R.id.tab);
        ViewPager viewPager=findViewById(R.id.viewpager);
        FragAdapter fragAdapter=new FragAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent=getIntent();
        name = intent.getStringExtra("name");

    }

    public String cc() {
        return name;
    }
}
