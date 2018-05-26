package com.lern.baby.seti.bilans;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lern.baby.seti.bilans.DataModel.DataObject;
import com.lern.baby.seti.bilans.DataModel.StorageListData;

public class MainActivity extends AppCompatActivity {
    private EditText etQuadNumber;
    private EditText etGasKind;
    private EditText etQuadCapacity;
    private EditText etQuadPressure;
    private EditText etNumberOfTanks;
    private Button btnSaveToFile;
    private Button btnAddToList;
    private EditText etSaveTimeMessageInput;
    private Button btnStartViewActivity;

    DataObject dataObject;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSaveTimeMessageInput = (EditText) findViewById(R.id.ed_save_time_input);
        btnStartViewActivity = (Button) findViewById(R.id.btn_start_names_files_view_activity);
        etGasKind = (EditText) findViewById(R.id.et_kind_of_gas);
        etQuadCapacity = (EditText) findViewById(R.id.et_capacity_of_quad);
        etQuadNumber = (EditText) findViewById(R.id.et_number_of_quad);
        etQuadPressure = (EditText) findViewById(R.id.et_pressure_of_quad);
        btnAddToList = (Button) findViewById(R.id.btn_add_to_list);
        btnSaveToFile = (Button) findViewById(R.id.btn_save_to_file);
     //   etNumberOfTanks = (EditText) findViewById(R.id.et_number_of_tanks);

        btnStartViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NameFilesViewActivity.class);
                startActivity(intent
                );
            }
        });


        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToBilansList();
                clearEditTexts();
            }
        });

        btnSaveToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isStoragePermissionGranted()) {

                    FileManager.instance.openOutputStream(etSaveTimeMessageInput.getText().toString());
                    FileManager.instance.saveToFileTxt(StorageListData.dataObjectList);


                    StorageListData.dataObjectList.clear();
                    etSaveTimeMessageInput.setText("");
                    Toast.makeText(MainActivity.this, R.string.saving_to_file, Toast.LENGTH_SHORT).show();


                }
            }
        });

        //  Toast.makeText(getApplicationContext(), "" + Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();

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

    private void clearEditTexts() {
        etQuadNumber.setText("");
        etQuadCapacity.setText("");
        etGasKind.setText("");
        etQuadPressure.setText("");
    }

    private void addToBilansList() {
        dataObject = new DataObject(etQuadNumber.getText().toString()
                , etGasKind.getText().toString()
                , etQuadCapacity.getText().toString()
                , etQuadPressure.getText().toString());
        StorageListData.dataObjectList.add(dataObject);
        Toast.makeText(this, R.string.adding_to_list, Toast.LENGTH_SHORT).show();
    }


}
