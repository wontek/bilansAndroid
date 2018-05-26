package com.lern.baby.seti.bilans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lern.baby.seti.bilans.DataModel.DataObject;
import com.lern.baby.seti.bilans.DataModel.StorageListData;

import java.util.ArrayList;

//*only for testing purpose*/
public class ShowActivity extends AppCompatActivity {
    ArrayList<DataObject> dataObjectList = StorageListData.dataObjectList;

    private TextView textView;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        textView = (TextView) findViewById(R.id.tv_show);

        showInTextView();


    }

    private void showInTextView() {
        for (int i = 0; i <= dataObjectList.size() - 1; i++) {


            stringBuilder.append(dataObjectList.get(i).getQuadNumber() + ","
                    + dataObjectList.get(i).getGasKind() + ","
                    + dataObjectList.get(i).getQuadCapacity() + ","
                    + dataObjectList.get(i).getQuadPressure() + "," + "\n");

        }
        textView.setText(stringBuilder.toString());
    }
}
