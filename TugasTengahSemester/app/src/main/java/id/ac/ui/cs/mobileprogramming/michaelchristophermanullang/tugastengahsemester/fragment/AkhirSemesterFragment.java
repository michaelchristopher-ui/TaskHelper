package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.MainActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.NetworkConnectivityActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.OpenGLActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities.PermissionActivity;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel.AkhirSemesterViewModel;


public class AkhirSemesterFragment extends Fragment {
    private int STORAGE_PERMISSION_CODE = 1;
    AkhirSemesterViewModel akhirSemesterViewModel;
    EditText editText;
    TextView staticText;
    Button openGLButton;
    Button timerButton;
    Button seriousButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_akhir_semester, container, false);
        //Here lies the representation of the elements that appear on the layout

        akhirSemesterViewModel = new AkhirSemesterViewModel();

        Button buttonRequest = v.findViewById(R.id.requestPermission);
        buttonRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PermissionActivity.class));
            }
        });

        editText = v.findViewById(R.id.editText);
        staticText = v.findViewById(R.id.staticText);

        Button buttonGenerate = v.findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString() + " with length " +
                        fetchTxtLen(editText.getText().toString());


                akhirSemesterViewModel.setText(text);
            }
        });

        timerButton = v.findViewById(R.id.timerButton);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(7);
            }
        });
        seriousButton = v.findViewById(R.id.seriousButton);
        seriousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NetworkConnectivityActivity.class));
            }
        });
        openGLButton = v.findViewById(R.id.relaxButton);
        openGLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OpenGLActivity.class));
            }
        });

        return v;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions((MainActivity) getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText((MainActivity) getActivity(), R.string.permissionGranted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText((MainActivity) getActivity(), R.string.permissionNotGranted, Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).replaceFragment(8);
            }
        } else {
            Log.d("Permissions", String.valueOf(requestCode));
        }
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        akhirSemesterViewModel = ViewModelProviders.of(getActivity()).get(AkhirSemesterViewModel.class);
        akhirSemesterViewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                staticText.setText(charSequence);
            }
        });
    }

    public native int fetchTxtLen(String txt);

}
