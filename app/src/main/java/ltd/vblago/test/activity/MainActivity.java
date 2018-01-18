package ltd.vblago.test.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ltd.vblago.test.R;
import ltd.vblago.test.fragment.FirstQuestionFragment;
import ltd.vblago.test.fragment.ResultFragment;
import ltd.vblago.test.fragment.SecondQuestionFragment;
import ltd.vblago.test.model.ActivityCommunication;
import ltd.vblago.test.model.Comment;
import ltd.vblago.test.util.SendRequest;

public class MainActivity extends AppCompatActivity implements ActivityCommunication {

    public Comment comment;
    Unbinder unbinder;

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_POINT = "point";
    SharedPreferences sPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideUI();
        unbinder = ButterKnife.bind(this);

        comment = new Comment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, FirstQuestionFragment.newInstance())
                .commit();

        sPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void hideUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    public void setFirstAnswer(String answer) {
        switch (answer) {
            case "great":
                comment.great = "1";
                goToResultFragment();
                break;
            case "good":
                comment.good = "1";
                goToResultFragment();
                break;
            case "fine":
                comment.fine = "1";
                goToSecondQuestionFragment();
                break;
            case "bad":
                comment.bad = "1";
                goToSecondQuestionFragment();
                break;
        }
    }

    @Override
    public void setSecondAnswer(String answer) {
        switch (answer) {
            case "range":
                comment.range = "1";
                break;
            case "quality":
                comment.quality = "1";
                break;
            case "price":
                comment.price = "1";
                break;
        }
        goToResultFragment();
    }

    @Override
    public void setSettings(String point) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(APP_PREFERENCES_POINT, point);
        editor.apply();
        goToFirstQuestionFragment();
    }

    @Override
    public boolean setPoint(){
        if(sPreferences.contains(APP_PREFERENCES_POINT)) {
            comment.point = sPreferences.getString(APP_PREFERENCES_POINT, "");
            return true;
        }
        Toast.makeText(getApplicationContext(), "Выберите номер точки", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void goToFirstQuestionFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, FirstQuestionFragment.newInstance())
                .commit();
    }

    private void goToSecondQuestionFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SecondQuestionFragment.newInstance())
                .commit();
    }

    private void goToResultFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ResultFragment.newInstance())
                .commit();
        sendInfo();
    }

    public void sendInfo() {
        new SendRequest(comment).execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hideUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
