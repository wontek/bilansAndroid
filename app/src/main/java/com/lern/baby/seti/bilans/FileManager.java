package com.lern.baby.seti.bilans;


import android.os.Environment;

import com.lern.baby.seti.bilans.DataModel.DataObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
    static final FileManager instance = new FileManager();
    private static final String folderName = "bilans ";
    private PrintWriter writerTxt;
    private String filename;
    private BufferedReader readerTXT;
    private StringBuilder builder;

    private FileManager() {
    }

    public void openOutputStream(String infoInput) {
        filename = folderName + infoInput+ new SimpleDateFormat(" yyyy_MM_dd EEEE HH_mm_ss").format(new Date());
        File containingFolder = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + folderName);
        if (!containingFolder.exists()) {
            containingFolder.mkdir();
        }
        openOutputStreamWriterTxt();
    }

    private void openOutputStreamWriterTxt() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + folderName + "/" + filename + ".txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            writerTxt = new PrintWriter(outputStreamWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    void saveToFileTxt(ArrayList<DataObject> dataObjectList) {
        for (int i = 0; i <= dataObjectList.size() - 1; i++) {
            DataObject dataObject = dataObjectList.get(i);
            builder = new StringBuilder();
            builder.append(dataObject.getQuadNumber())
                    .append(";")
                    .append(dataObject.getGasKind())
                    .append(";")
                    .append(dataObject.getQuadCapacity())
                    .append(";")

                    .append(dataObject.getQuadPressure());

            writerTxt.println(builder.toString()+"\r");

        }
        closeOutputStream();
    }
    public void saveToFileTxt(String data) {

        writerTxt.println(data);
        closeOutputStream();

    }

    private void closeOutputStream() {
        writerTxt.close();
    }


    void openInputStream(String filename) {
        File containingFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        if (!containingFolder.exists()) {
            containingFolder.mkdir();
        }


        openInputStreamReader(filename);
    }


    private void openInputStreamReader(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            readerTXT = new BufferedReader(inputStreamReader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    public String readFile(String fileName) {
        try {

            return readTxt();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error reading file.";
    }

    private String readTxt() throws IOException {
        StringBuilder builder = new StringBuilder();

        String line = readerTXT.readLine();

        while (line != null && !line.isEmpty()) {
            builder.append(line + '\n' );


            line = readerTXT.readLine();
        }

        readerTXT.close();
        return builder.toString();
    }


    public File[] getFilesFromFolder() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        File[] listOfFiles = file.listFiles();
        return listOfFiles;
    }
}

