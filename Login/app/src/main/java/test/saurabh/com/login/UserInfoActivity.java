package test.saurabh.com.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

public class UserInfoActivity extends AppCompatActivity {

        TextView textView;
        LoginButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.get("name").toString();
        String imageUrl = bundle.get("imageurl").toString();

        textView = (TextView)findViewById(R.id.name);
        textView.setText("Welcome " + name);
        System.out.print("I am in OnCreate");
        new DownloadFilesTask((ImageView) findViewById(R.id.profileImage)).execute(imageUrl);

  //      new DownloadFilesTask().execute(imageUrl);
        System.out.print("I am in after OnCreate ");
       /* button = (Button)findViewById(R.id.login_button);*/

    }

    public void logout1(){
  //      button = (Button)findViewById(R.id.logoutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
