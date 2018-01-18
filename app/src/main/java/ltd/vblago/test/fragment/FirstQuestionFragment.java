package ltd.vblago.test.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ltd.vblago.test.R;

public class FirstQuestionFragment extends Fragment {

    Unbinder unbinder;

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

    @OnClick({ R.id.great_view, R.id.good_view})
    public void clickGood(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ResultFragment.newInstance())
                .commit();
    }

    @OnClick({ R.id.fine_view, R.id.bad_view})
    public void clickBad(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SecondQuestionFragment.newInstance())
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
