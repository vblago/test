package ltd.vblago.test.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ltd.vblago.test.R;

public class ResultFragment extends Fragment {

    public static final int START = 1;

    @SuppressLint("HandlerLeak")
    Handler h = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (msg.what == START) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, FirstQuestionFragment.newInstance())
                        .commit();
            }
        }
    };

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        h.sendEmptyMessageDelayed(START, 20_000);
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

}
