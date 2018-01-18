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
import ltd.vblago.test.model.Comment;

public class FirstQuestionFragment extends Fragment {

    ActivityCommunication activityCommunication;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityCommunication) {
            activityCommunication = (ActivityCommunication) context;
        } else {
            throw new RuntimeException(Context.class.getSimpleName() + " must implement ActivityCommunication interface");
        }
    }

    public static FirstQuestionFragment newInstance() {
        return new FirstQuestionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_first_question, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.great_view)
    public void clickGreat(){
        activityCommunication.setFirstAnswer("great");
    }

    @OnClick(R.id.good_view)
    public void clickGood(){
        activityCommunication.setFirstAnswer("good");
    }

    @OnClick(R.id.fine_view)
    public void clickFine(){
        activityCommunication.setFirstAnswer("fine");
    }

    @OnClick(R.id.bad_view)
    public void clickBad(){
        activityCommunication.setFirstAnswer("bad");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
