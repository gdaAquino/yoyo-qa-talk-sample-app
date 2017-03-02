package me.popslide.training;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Gian Darren Aquino
 */
public class InstallActivity extends AppCompatActivity {

    private EditText editTextPackageName;
    private Button buttonInstall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_activity);
        editTextPackageName = (EditText) findViewById(R.id.input);
        buttonInstall = (Button) findViewById(R.id.install);
        editTextPackageName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isInstalled(editTextPackageName.getText().toString())) {
                    buttonInstall.setVisibility(View.VISIBLE);
                    buttonInstall.setText("Open");
                } else if (editTextPackageName.getText().length() > 0) {
                    buttonInstall.setVisibility(View.VISIBLE);
                    buttonInstall.setText("Install");
                } else {
                    buttonInstall.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        buttonInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = editTextPackageName.getText().toString();
                if (isInstalled(packageName)) {
                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean isInstalled(String packageName) {
        try {
            getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
