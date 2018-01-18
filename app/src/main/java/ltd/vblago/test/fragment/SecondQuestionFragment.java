package ltd.vblago.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ltd.vblago.test.R;
import ltd.vblago.test.model.ActivityCommunication;

public class SecondQuestionFragment extends Fragment {

    ActivityCommunication activityCommunication;
    Unbinder unbinder;

    public static SecondQuestionFragment newInstance() {
        return new SecondQuestionFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityCommunication) {
            activityCommunication = (ActivityCommunication) context;
        } else {
            throw new RuntimeException(Context.class.getSimpleName() + " must implement ActivityCommunication interface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second_question, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.range_view)
    public void clickRange(){
        activityCommunication.setSecondAnswer("range");
    }

    @OnClick(R.id.quality_view)
    public void clickQuality(){
        activityCommunication.setSecondAnswer("quality");
    }

    @OnClick(R.id.price_view)
    public void clickPrice(){
        activityCommunication.setSecondAnswer("price");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
