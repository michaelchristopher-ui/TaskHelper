package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;

import static android.Manifest.permission.FOREGROUND_SERVICE;

public class PermissionActivity extends AppCompatActivity {
    Button permissionButton;
    private int PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_permission);

        permissionButton = findViewById(R.id.buttonPermission);
        permissionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PermissionActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
            }
        });

    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(PermissionActivity.this, R.string.permissionGranted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PermissionActivity.this, R.string.permissionNotGranted, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, WhyActivity.class));
            }
        } else {
            Log.d("Permissions", String.valueOf(requestCode));
        }
    }
}
