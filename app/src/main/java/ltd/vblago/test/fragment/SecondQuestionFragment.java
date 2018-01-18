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

public class SecondQuestionFragment extends Fragment {

    Unbinder unbinder;

    public static SecondQuestionFragment newInstance() {
        return new SecondQuestionFragment();
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
        goToResultFragment();
    }

    @OnClick(R.id.quality_view)
    public void clickQuality(){
        goToResultFragment();
    }

    @OnClick(R.id.price_view)
    public void clickPrice(){
        goToResultFragment();
    }

    private void goToResultFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ResultFragment.newInstance())
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
