package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.service.MyService;

public class StopwatchFragment extends Fragment {
    private TextView textView;
    private EditText editText;
    private Button startButton;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        textView = v.findViewById(R.id.textView);
        editText = v.findViewById(R.id.editText);
        startButton = v.findViewById(R.id.button);
        textView.setText("TaskTimer");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "stART");
                startButton();
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Counter");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer integerTime = intent.getIntExtra("TimeRemaining", 0);
                textView.setText(integerTime.toString());
            }
        };
        requireActivity().registerReceiver(broadcastReceiver, intentFilter);

        return v;
    }


    public void startButton(){
        Intent intentService = new Intent(getActivity(), MyService.class);
        Integer integerTimeSet = Integer.parseInt(editText.getText().toString());
        intentService.putExtra("TimeValue", integerTimeSet);
        getActivity().startService(intentService);
    }
}
