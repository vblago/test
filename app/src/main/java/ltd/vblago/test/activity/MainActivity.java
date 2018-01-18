package ltd.vblago.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    protected void onRestart() {
        super.onRestart();
        hideUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void setFirstAnswer(String answer) {
        if (answer.equals("great")){
            comment.great = "1";
            goToResultFragment();
        } else if (answer.equals("good")){
            comment.good = "1";
            goToResultFragment();
        } else if (answer.equals("fine")){
            comment.fine = "1";
            goToSecondQuestionFragment();
        } else if (answer.equals("bad")){
            comment.bad = "1";
            goToSecondQuestionFragment();
        }
    }

    @Override
    public void setSecondAnswer(String answer) {
        if (answer.equals("range")){
            comment.range = "1";
        } else if (answer.equals("quality")){
            comment.quality = "1";
        } else if (answer.equals("price")){
            comment.price = "1";
        }
        goToResultFragment();
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
}
