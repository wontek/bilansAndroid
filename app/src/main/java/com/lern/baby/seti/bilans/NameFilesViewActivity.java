package com.lern.baby.seti.bilans;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class NameFilesViewActivity extends AppCompatActivity {
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_files_view);
        layout = (LinearLayout) findViewById(R.id.layout);

        repaintButtons();
    }

    public void repaintButtons() {


        layout.removeAllViews();

        for (final File file : FileManager.instance.getFilesFromFolder()) {
            Button newButton = new Button(this);
            newButton.setText(file.getName());

            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NameFilesViewActivity.this, ViewFolderActivity.class);
                    intent.putExtra("filename", file.getAbsolutePath());
                    startActivity(intent);
                }
            });
            newButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String filelocation = file.getAbsolutePath();

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    String message = "File to be shared is " + file.getName() + ".";
                    intent.putExtra(Intent.EXTRA_SUBJECT, "bilans gazów " + file.getName());
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filelocation));
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setData(Uri.parse("mailto:wojciechpytlak@wp.pl"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "No e-mail client ", Toast.LENGTH_LONG).show();
                    }


                    return true;
                }
            });

            layout.addView(newButton);
        }

    }
}
