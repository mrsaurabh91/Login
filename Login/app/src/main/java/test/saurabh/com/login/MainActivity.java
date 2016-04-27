package test.saurabh.com.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private Button button;

    ProfilePictureView profilePictureView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);


        info=(TextView)findViewById(R.id.info);
        profilePictureView = (ProfilePictureView) findViewById(R.id.image);
        button =(Button)findViewById(R.id.next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserInfo.class);
                startActivity(intent);
            }
        });


        loginButton=(LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                displayWelcomeMsg(profile);
                profilePictureView.setProfileId(profile.getId());
                //                    nextActivity(profile);


                //        info.setText("User ID" + loginResult.getAccessToken().getUserId());

             /*
               // nextActivity(profile);
                String name = profile.getName();
                info.setText("Welcome "+profile.getName());*/
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText("Login attempt failed.");
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayWelcomeMsg(currentProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

    }


    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMsg(profile);
//        nextActivity(profile);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

       private void displayWelcomeMsg(Profile profile) {
           if (profile != null) {
                info.setText("Welcome "+profile.getName());
           }
       }

    private void nextActivity(Profile profile){
        if(profile!=null){
            Intent intent=new Intent(MainActivity.this,UserInfo.class);
            intent.putExtra("name",profile.getName());
      //      intent.putExtra("imageurl",profile.getProfilePictureUri(200,200).toString());
            startActivity(intent);
        }
    }

}