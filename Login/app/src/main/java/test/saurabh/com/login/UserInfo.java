package test.saurabh.com.login;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Don't Mess With Me on 4/25/2016.
 */
public class UserInfo extends AppCompatActivity {

    private ShareDialog shareDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.user_info);
/*
        Bundle inBunldle=getIntent().getExtras();
        String name =inBunldle.get("name").toString();
        String imageUrl = inBunldle.get("imageurl").toString();

        TextView nameView = (TextView)findViewById(R.id.name);
        nameView.setText(""+name);
*//*
        shareDialog = new ShareDialog(this);
        *//**//*new DownloadManager.Request();
        new DownloadImage((ImageView) findViewById(R.id.profileImage)).execute(imageUrl);*//*

    }

    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(UserInfo.this, MainActivity.class);
        startActivity(login);
        finish();
   */ }
}
