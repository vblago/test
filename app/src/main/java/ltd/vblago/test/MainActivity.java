package ltd.vblago.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideUI();

        unbinder = ButterKnife.bind(this);
    }

    @OnClick({ R.id.great_view, R.id.good_view})
    public void clickGood(){
        Toast.makeText(getApplicationContext(), "Переход на страничку: Спасибо за ваш отзыв", Toast.LENGTH_SHORT).show();
    }

    @OnClick({ R.id.fine_view, R.id.bad_view})
    public void clickBad(){
        Toast.makeText(getApplicationContext(), "Переход на страничку: Что Вам не понравилось", Toast.LENGTH_SHORT).show();
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
}
