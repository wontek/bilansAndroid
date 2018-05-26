package com.lern.baby.seti.bilans;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFolderActivity extends AppCompatActivity {
    private TextView edViewFolder;
    private Button btnEditData;
    private EditText edSaveTimeInpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_folder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edViewFolder = (TextView) findViewById(R.id.et_folder_view);
        btnEditData = (Button) findViewById(R.id.btn_edit_data);
        edSaveTimeInpt = (EditText) findViewById(R.id.ed_save_time_input);

        if (getIntent().hasExtra("filename")) {
            String fileName = getIntent().getStringExtra("filename");

            FileManager.instance.openInputStream(fileName);
            String s = FileManager.instance.readFile(fileName);

            edViewFolder.setText(s);
         //   Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }

        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isStoragePermissionGranted()) {
                    btnEditData.setEnabled(true);

                    FileManager.instance.openOutputStream(edSaveTimeInpt.getText().toString());
                    FileManager.instance.saveToFileTxt(edViewFolder.getText().toString());


                    Toast.makeText(ViewFolderActivity.this, R.string.saving_to_file, Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation

            return true;
        }
    }
}
