package ltd.vblago.test.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ltd.vblago.test.R;
import ltd.vblago.test.model.ActivityCommunication;

public class SettingsFragment extends Fragment {

    ActivityCommunication activityCommunication;
    Unbinder unbinder;

    @BindView(R.id.insert_point_view)
    EditText insertPointView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityCommunication) {
            activityCommunication = (ActivityCommunication) context;
        } else {
            throw new RuntimeException(Context.class.getSimpleName() + " must implement ActivityCommunication interface");
        }
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.save_settings_btn)
    public void saveSettings(){
        String point = insertPointView.getText().toString();
        if (!point.equals("")){
            activityCommunication.setSettings(point);
        }else {
            Toast.makeText(getContext(), "Введите номер точки", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
