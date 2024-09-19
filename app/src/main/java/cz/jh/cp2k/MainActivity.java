package cz.jh.cp2k;

import static cz.jh.cp2k.Spannables.colorized_bash;
import static cz.jh.cp2k.Spannables.colorized_numbers;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureConfig;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.util.Log;

import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private TextView ContentLabel;
    private EditText Content;
    private TextView CommandLabel;
    private EditText Command;
    Button openCommandfile;
    Button openIntCommandfile;
    Button saveCommandfile;
    Button saveExtCommandfile;
    private TextView InLabel;
    private EditText InFile;
    Button openInfile;
    Button openIntInfile;
    Button saveInfile;
    Button saveExtInfile;
    private TextView InputLabel;
    private EditText InputFile;
    Button openInputfile;
    Button openIntInputfile;
    Button saveInputfile;
    Button saveExtInputfile;
    Button generateXYZ;
    Button opsinXYZ;
    Button RunProgram;
    Button saveOutputfile;
    Button saveExtOutputfile;
    Button Highlight;
    Button Graph;
    Button About;
    Button License;
    private TextView textViewX;
    private TextView outputView;
    private EditText outputView2;
    private static final int READ_FILE6 = 6;
    private Uri documentUri6;
    private static final int READ_FILE26 = 26;
    private Uri documentUri26;
    private static final int CREATE_FILE01 = 1;
    private Uri documentUri0;
    private static final int CREATE_FILE20 = 20;
    private Uri documentUri20;
    private static final int CREATE_FILE21 = 21;
    private Uri documentUri21;
    private static final int READ_FILE60 = 60;
    private Uri documentUri60;
    private static final int CREATE_FILE200 = 200;
    private Uri documentUri200;
    Button recipes_cp2k;
    Button manual_openbabel;
    private static final int MY_PERMISSION_REQUEST_STORAGE = 1;
    Button change_size;
    Button PrivacyPolicy;
    Button inToCanvas;
    Button outToCanvas;
    Button start_shelltools;

    static final String LOGTAG = "";
    static final int BUFSIZE = 5192;
    static final String ZIP_FILTER = "assets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentLabel = (TextView) findViewById(R.id.ContentLabel);
        Content = (EditText) findViewById(R.id.Content);
        // prevent crash in the beginning, when the file is not already unzipped
        File OutputTextSize = new File(getFilesDir()+"/OutputTextSize.txt");
        if (OutputTextSize.exists()) {
            Content.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        } else {
            Content.setTextSize(12);
        }
        // disable - otherwise the text could not be selected
//        Content.setMovementMethod(new ScrollingMovementMethod());
        Content.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                Content.removeTextChangedListener(this);
                String text = Content.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Content.getText().clear();
                Content.append(colorized_numbers(text));
                // place the cursor at the original position
                Content.setSelection(startChanged+countChanged);
                Content.addTextChangedListener(this);
            }
        });
        CommandLabel = (TextView) findViewById(R.id.CommandLabel);
        Command = (EditText) findViewById(R.id.Command);
        // prevent crash in the beginning, when the file is not already unzipped
        File InputTextSize = new File(getFilesDir()+"/InputTextSize.txt");
        if (InputTextSize.exists()) {
            Command.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        } else {
            Command.setTextSize(16);
        }
        // disable - otherwise the text could not be selected
//        Command.setMovementMethod(new ScrollingMovementMethod());
        Command.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                Command.removeTextChangedListener(this);
                String text = Command.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Command.getText().clear();
                Command.append(colorized_bash(text));
                // place the cursor at the original position
                Command.setSelection(startChanged+countChanged);
                Command.addTextChangedListener(this);
            }
        });
        openCommandfile = (Button) findViewById(R.id.openCommandfile);
        openCommandfile.setOnClickListener(openCommandfileClick);
        openIntCommandfile = (Button) findViewById(R.id.openIntCommandfile);
        saveCommandfile = (Button) findViewById(R.id.saveCommandfile);
        saveCommandfile.setOnClickListener(saveCommandfileClick);
        saveExtCommandfile = (Button) findViewById(R.id.saveExtCommandfile);
        saveExtCommandfile.setOnClickListener(saveExtCommandfileClick);
        InLabel = (TextView) findViewById(R.id.InLabel);
        InFile = (EditText) findViewById(R.id.InFile);
        if (InputTextSize.exists()) {
            InFile.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        } else {
            InFile.setTextSize(16);
        }
        // disable - otherwise the text could not be selected
//        InFile.setMovementMethod(new ScrollingMovementMethod());
        InFile.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                InFile.removeTextChangedListener(this);
                String text = InFile.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                InFile.getText().clear();
                InFile.append(colorized_numbers(text));
                // place the cursor at the original position
                InFile.setSelection(startChanged+countChanged);
                InFile.addTextChangedListener(this);
            }
        });
        openInfile = (Button) findViewById(R.id.openInfile);
        openInfile.setOnClickListener(openInfileClick);
        openIntInfile = (Button) findViewById(R.id.openIntInfile);
        saveInfile = (Button) findViewById(R.id.saveInfile);
        saveInfile.setOnClickListener(saveInfileClick);
        saveExtInfile = (Button) findViewById(R.id.saveExtInfile);
        saveExtInfile.setOnClickListener(saveExtInfileClick);
        InputLabel = (TextView) findViewById(R.id.InputLabel);
        InputFile = (EditText) findViewById(R.id.InputFile);
        if (InputTextSize.exists()) {
            InputFile.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        } else {
            InputFile.setTextSize(16);
        }
        // disable - otherwise the text could not be selected
//        InputFile.setMovementMethod(new ScrollingMovementMethod());
        InputFile.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                InputFile.removeTextChangedListener(this);
                String text = InputFile.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                InputFile.getText().clear();
                InputFile.append(colorized_numbers(text));
                // place the cursor at the original position
                InputFile.setSelection(startChanged+countChanged);
                InputFile.addTextChangedListener(this);
            }
        });
        openInputfile = (Button) findViewById(R.id.openInputfile);
        openInputfile.setOnClickListener(openInputfileClick);
        openIntInputfile = (Button) findViewById(R.id.openIntInputfile);
        saveInputfile = (Button) findViewById(R.id.saveInputfile);
        saveInputfile.setOnClickListener(saveInputfileClick);
        saveExtInputfile = (Button) findViewById(R.id.saveExtInputfile);
        saveExtInputfile.setOnClickListener(saveExtInputfileClick);
        generateXYZ = (Button) findViewById(R.id.generateXYZ);
        generateXYZ.setOnClickListener(GenerateXYZClick);
        opsinXYZ = (Button) findViewById(R.id.opsinXYZ);
        opsinXYZ.setOnClickListener(opsinXYZClick);
        RunProgram = (Button) findViewById(R.id.RunProgram);
        RunProgram.setOnClickListener(RunProgramClick);
        saveOutputfile = (Button) findViewById(R.id.saveOutputfile);
        saveOutputfile.setOnClickListener(saveOutputfileClick);
        saveExtOutputfile = (Button) findViewById(R.id.saveExtOutputfile);
        saveExtOutputfile.setOnClickListener(saveExtOutputfileClick);
        Highlight = (Button) findViewById(R.id.Highlight);
        Highlight.setOnClickListener(HighlightClick);
        About = (Button) findViewById(R.id.About);
        About.setOnClickListener(AboutClick);
        start_shelltools = (Button) findViewById(R.id.start_shelltools);
        textViewX = (TextView) findViewById(R.id.textViewX);
        outputView = (TextView) findViewById(R.id.outputView);
        outputView2 = (EditText) findViewById(R.id.outputView2);
        // prevent crash in the beginning, when the file is not already unzipped
        if (OutputTextSize.exists()) {
        outputView2.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        } else {
        outputView2.setTextSize(12);
        }
        // disable - otherwise the text could not be selected
//        outputView2.setMovementMethod(new ScrollingMovementMethod());
//        outputView2.addTextChangedListener(new TextWatcher() {
//            int startChanged,beforeChanged,countChanged;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                startChanged = start;
//                beforeChanged = before;
//                countChanged = count;
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                outputView2.removeTextChangedListener(this);
//                String text = outputView2.getText().toString();
//                // important - not setText() - otherwise the keyboard would be reset after each type
//                outputView2.getText().clear();
//                outputView2.append(text);
//                // place the cursor at the original position
//                outputView2.setSelection(startChanged+countChanged);
//                outputView2.addTextChangedListener(this);
//            }
//        });



        Graph = (Button) findViewById(R.id.Graph);
        Graph.setOnClickListener(GraphClick);

        PrivacyPolicy = (Button) findViewById(R.id.PrivacyPolicy);
        PrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        change_size = (Button) findViewById(R.id.change_size);
        change_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeSize.class);
                startActivity(intent);
            }
        });

        License = (Button) findViewById(R.id.License);
        License.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Licenses.class);
                startActivity(intent);
            }
        });

        manual_openbabel = (Button) findViewById(R.id.manual_openbabel);
        manual_openbabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ManualOpenbabel.class);
                startActivity(intent);
            }
        });

        recipes_cp2k = (Button) findViewById(R.id.recipes_cp2k);
        recipes_cp2k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RecipesCp2k.class);
                startActivity(intent);
            }
        });

        openIntInputfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cp2kWork.class);
                startActivity(intent);
            }
        });

        openIntInfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cp2kWork1.class);
                startActivity(intent);
            }
        });

        openIntCommandfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cp2kCommand.class);
                startActivity(intent);
            }
        });

        start_shelltools = (Button) findViewById(R.id.start_shelltools);
        start_shelltools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShellTools.class);
                startActivity(intent);
            }
        });

        inToCanvas = (Button) findViewById(R.id.inToCanvas);
        inToCanvas.setOnClickListener(inToCanvasClick);

        outToCanvas = (Button) findViewById(R.id.outToCanvas);
        outToCanvas.setOnClickListener(outToCanvasClick);



        // give the app permissions to access the storage
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);
                }
                ;
            } else {
                // do nothing
            }
            ;
        }

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        SharedPreferences.Editor editor = wmbPreference.edit();

        if (isFirstRun){

            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Installing CP2K. It may take a while.");
            progressDialog.show();
            new Thread() {
                public void run() {


                    copyFromAssetsToInternalStorage("assets.zip");
                    copyFromAssetsToInternalStorage("Command.txt");
                    copyFromAssetsToInternalStorage("Input-cp2k.xyz");
                    copyFromAssetsToInternalStorage("Input-cp2k.txt");
                    copyFromAssetsToInternalStorage("InputTextSize.txt");
                    copyFromAssetsToInternalStorage("OutputTextSize.txt");
//                    copyFromAssetsToInternalStorage("TextSize.txt");
//                    String zipFilePath = getFilesDir()+"/assets.zip";
                    String zipFilePath = getFilesDir()+File.separator+"assets.zip";
//                    String destDir = getFilesDir()+"/" ;
//                    unzipfile( zipFilePath, destDir ) ;
                    try {
//                        unzip(new File(zipFilePath),destDir);
                        unzip(new File(zipFilePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exec("rm "+getFilesDir()+File.separator+"assets.zip");
//                    unZipFile(getFilesDir()+"/assets.zip");

//                    unzipAssets(MainActivity.this);
//                    copyAsset("assets.zip");
                    exec("mkdir "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/cp2k");
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(ErgoInput.getWindowToken(), 0);
//                String command = ErgoInput.getText().toString();
//                    String command = "export HOME="+getFilesDir()+"/ ; cd $HOME ; unzip assets.zip ; rm assets.zip ; chmod -R 755 *";
//                    new RunCommandTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, command);
                    exec("chmod -R 755 "+getFilesDir()+"/");
                    output(exec("ls -la "+getFilesDir()+"/"));
                    output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                    output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                    output5(exec("cat "+getFilesDir()+"/Command.txt"));

                    onFinish();
                }
                public void onFinish(){
                    progressDialog.dismiss();
                }
            }.start();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }
    }

    private void copyAsset(String filename) {
        File filePath = new File(getFilesDir()+"");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            File outFile = new File(filePath, filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[65536];
        int read;
        while ((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public void onStart()
    {
        super.onStart();

        output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
        output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
        output5(exec("cat "+getFilesDir()+"/Command.txt"));
        output(exec("ls -la "+getFilesDir()+"/"));
    }

    private View.OnClickListener inToCanvasClick; {

        inToCanvasClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                int ColorAtomBorder = Integer.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ColorAtomBorder.tmp"));

                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Exporting the structure...");
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                progressDialog.show();

                new Thread() {
                    public void run() {
                        try {
                            FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile);
                            outputWriter.close();
                            FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                            outputWriter2.write(Arguments);
                            outputWriter2.close();
                            FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(Infile);
                            outputWriter3.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String XYZfile = exec("cat "+getFilesDir()+"/Input-cp2k.xyz");
                        try {
                            while (XYZfile.contains("\t")){  //2 spaces
                                XYZfile = XYZfile.replace("\t", " "); //(2 spaces, 1 space)
                            }
                            while (XYZfile.contains("  ")){  //2 spaces
                                XYZfile = XYZfile.replace("  ", " "); //(2 spaces, 1 space)
                            }
                            while (XYZfile.contains("\n ")){  //2 spaces
                                XYZfile = XYZfile.replace("\n ", "\n"); //(2 spaces, 1 space)
                            }

                            FileOutputStream fileout = openFileOutput("Coordinates.xyz.tmp", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(XYZfile);
                            outputWriter.close();


                            exec("rm "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                            exec("touch "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                            exec("rm "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");
                            exec("touch "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");

                            // in Angstroms, in 0;0, without zoom
                            exec("mv "+getFilesDir()+"/Coordinates.xyz.tmp "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                            exec("sed -i 1,2d "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                            try {
                                Scanner scan = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp"));
                                double radius = 0;
                                int atom_color = 0;
                                int text_color = 0;
                                int atom_number = 0;
                                int atomNumber = 0;
                                // now in Angstroms
                                double radius_Ang = 0;

                                while (scan.hasNext()) {
                                    atomNumber++;

                                    String curLine = scan.nextLine();
                                    String[] splitted = curLine.split(" ");
                                    String atom = splitted[0].trim();
                                    String x_coord = splitted[1].trim();
                                    String y_coord = splitted[2].trim();
                                    String z_coord = splitted[3].trim();

                                    atom_number = atomNumber;

//                        Log.println(Log.INFO, "atom = ", atom);

                                    try {
                                        Scanner scanElmnt = new Scanner(new File(getFilesDir()+"/canvas3d/Elmnts.dat"));
                                        while (scanElmnt.hasNext()) {
                                            String curLineElmnt = scanElmnt.nextLine();
                                            String[] splittedElmnt = curLineElmnt.split(" ");
                                            String elementElmnt = splittedElmnt[0].trim();
                                            String radiusElmnt = splittedElmnt[1].trim();
                                            String atom_colorElmnt = splittedElmnt[2].trim();
                                            String text_colorElmnt = splittedElmnt[3].trim();

                                            radius = Double.valueOf(radiusElmnt);
                                            atom_color = Integer.valueOf(atom_colorElmnt);
                                            text_color = Integer.valueOf(text_colorElmnt);
                                            radius_Ang = radius/100;

                                            if (atom.equals(elementElmnt)) {

                                                // write in Angstroms, in 0;0, without zoom
                                                FileOutputStream fileout3 = openFileOutput("Coordinates.x.tmp", MODE_APPEND);
                                                OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                                                outputWriter3.write(elementElmnt +"\t"+x_coord+"\t"+y_coord+"\t"+z_coord+"\t"+radius_Ang+"\t"+atom_color+"\t"+text_color+"\t"+atom_number+"\t"+ColorAtomBorder+"\t"+"0"+"\n");
                                                outputWriter3.close();
                                            }
                                        }
                                        scanElmnt.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                                // až tady: (za smyčkou)
                                scan.close();
                                exec("rm "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                                exec("mv "+getFilesDir()+"/Coordinates.x.tmp "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                exec("rm "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                exec("touch "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                double BondScale = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/BondScale.tmp"));
                                double ForegroundShiftBonds = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ForegroundShiftBonds.tmp"));
                                double ForegroundShiftText = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ForegroundShiftText.tmp"));
                                int ColorAtomBorder = Integer.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ColorAtomBorder.tmp"));
                                double h_bond_limit_HN = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHN.tmp"));
                                double h_bond_limit_HO = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHO.tmp"));
                                double h_bond_limit_HF = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHF.tmp"));
                                double h_bond_limit_HCl = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHCl.tmp"));
                                try {
                                    Scanner scanX = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates.x.tmp"));
                                    int lineNo1 = 0;
                                    while (scanX.hasNext()) {
                                        lineNo1++;
                                        String curLineX = scanX.nextLine();
                                        String[] splittedX = curLineX.split("\\s");
                                        String atomX = splittedX[0].trim();
                                        String x_coordX = splittedX[1].trim();
                                        String y_coordX = splittedX[2].trim();
                                        String z_coordX = splittedX[3].trim();
                                        String radiusX = splittedX[4].trim();
                                        String atom_colorX = splittedX[5].trim();
                                        String text_colorX = splittedX[6].trim();
                                        String atom_numberX = splittedX[7].trim();
                                        String col_at_borderX = splittedX[8].trim();
                                        String touch_timeX = splittedX[9].trim();
                                        int radius_pixX = (int) (Double.valueOf(radiusX)*100);
                                        // project 3D geometry to z = 0
                                        double A = 0;
                                        double B = 0;
                                        double C = 1;
                                        double D = 0;
                                        double x_projX = Double.valueOf(x_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        double y_projX = Double.valueOf(y_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        double z_projX = Double.valueOf(z_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        // because of canvas - input variables x&y have to be integers, not doubles
                                        int x_projection = (int) (x_projX*100);
                                        int y_projection = (int) (y_projX*100);
                                        int z_projection = (int) (z_projX*100);
                                        // text in front of circles = with less negative z coord
//                        double z_text = 100*(Double.valueOf(z_coord)+0.01);
                                        double z_textX = Double.valueOf(z_coordX)+ForegroundShiftText;
                                        // write the file
                                        FileOutputStream fileout_atoms = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                        OutputStreamWriter outputWriter_atoms = new OutputStreamWriter(fileout_atoms);
                                        outputWriter_atoms.write(atomX+"\t"+col_at_borderX+"\t"+x_projection+"\t"+y_projection+"\t"+touch_timeX+"\t"+"0"+"\t"+z_coordX+"\t"+radius_pixX+"\t"+atom_colorX+"\t"+atom_numberX+"\t"+"C"+"\n");
                                        outputWriter_atoms.write(atomX+"\t"+"0"+"\t"+x_projection+"\t"+y_projection+"\t"+"0"+"\t"+"0"+"\t"+z_textX+"\t"+"0"+"\t"+text_colorX+"\t"+atom_numberX+"\t"+"T"+"\n");
                                        outputWriter_atoms.close();

                                        // second loop - to reveal the bonds
                                        Scanner scan2 = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates.x.tmp"));
                                        int lineNo2 = 0;
                                        while (scan2.hasNext()) {
                                            lineNo2++;
                                            String curLine2 = scan2.nextLine();
                                            String[] splitted2 = curLine2.split("\\s");
                                            String atom2 = splitted2[0].trim();
                                            String x_coord2 = splitted2[1].trim();
                                            String y_coord2 = splitted2[2].trim();
                                            String z_coord2 = splitted2[3].trim();
                                            String radius2 = splitted2[4].trim();
                                            String atom_color2 = splitted2[5].trim();
                                            String text_color2 = splitted2[6].trim();
                                            String atom_number2 = splitted2[7].trim();
                                            String col_at_border2 = splitted2[8].trim();
                                            String touch_time2 = splitted2[9].trim();

                                            if (lineNo2 >= lineNo1) {
                                                // investigate all distances
                                                double dist_scan1_scan2 = Math.sqrt(Math.pow((Double.valueOf(x_coordX) - Double.valueOf(x_coord2)), 2) + Math.pow((Double.valueOf(y_coordX) - Double.valueOf(y_coord2)), 2) + Math.pow((Double.valueOf(z_coordX) - Double.valueOf(z_coord2)), 2));
                                                double BondingDistance = BondScale * (Double.valueOf(radiusX) + Double.valueOf(radius2));
                                                if ((dist_scan1_scan2 < BondingDistance) && (dist_scan1_scan2 > 0)) {

                                                    double A2 = 0;
                                                    double B2 = 0;
                                                    double C2 = 1;
                                                    double D2 = 0;
                                                    double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                    double x_bond1 = 100 * x_proj1;
                                                    double y_bond1 = 100 * y_proj1;
                                                    double x_bond2 = 100 * x_proj2;
                                                    double y_bond2 = 100 * y_proj2;

//                                int bond_color = Color.GRAY;
                                                    int bond_color1 = Integer.valueOf(atom_colorX);
                                                    int bond_color2 = Integer.valueOf(atom_color2);

                                                    // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                    double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                    // write the file
                                                    FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                    OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                    outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "L" + "\n");
                                                    outputWriter_bonds.close();
                                                } else if ((dist_scan1_scan2 >= BondingDistance) && (atomX.equals("H") || atom2.equals("H"))) {
                                                    if (((atomX.equals("H") && atom2.equals("N")) || ((atomX.equals("N") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HN)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("O")) || ((atomX.equals("O") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HO)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("F")) || ((atomX.equals("F") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HF)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("Cl")) || ((atomX.equals("Cl") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HCl)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    }
                                                }
                                            }
                                        }
                                        scan2.close();
                                    }
                                    scanX.close();
                                    exec("mv "+getFilesDir()+"/Coordinates.tmp "+getFilesDir()+"/canvas3d/");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // at the moment, the file Coordinates.tmp has to be sorted by the z_coord value:
                                try {
                                    String Z_sort = exec("sort -g -k7 "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                    FileOutputStream fileout_sort = openFileOutput("Coordinates.tmp_", MODE_PRIVATE);
                                    OutputStreamWriter outputWriter_sort = new OutputStreamWriter(fileout_sort);
                                    outputWriter_sort.write(Z_sort);
                                    outputWriter_sort.close();
                                    exec("mv "+getFilesDir()+"/Coordinates.tmp_ "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(MainActivity.this, Canvas3d_main.class);
                        startActivity(intent);

//                        molCanvasView.setMoleculeRenderer(Canvas3d_CanvasView.TRUE);
                        onFinish();
                    }
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                }.start();

            }
        };
    }

    private View.OnClickListener outToCanvasClick; {

        outToCanvasClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                int ColorAtomBorder = Integer.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ColorAtomBorder.tmp"));

                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Exporting the structure...");
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                progressDialog.show();

                new Thread() {
                    public void run() {
                        try {
                            FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile);
                            outputWriter.close();
                            FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                            outputWriter2.write(Arguments);
                            outputWriter2.close();
                            FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(Infile);
                            outputWriter3.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        com.jrummyapps.android.shell.Shell.SH.run("cd "+getFilesDir()+"/cp2k_calc ; tac Input-cp2k-pos-1.xyz | sed '/i =/q' | tac > File2.txt ; sed -n '1p' Input-cp2k-pos-1.xyz > File1.txt ; cat File1.txt File2.txt > ../Output-cp2k.xyz ; rm File1.txt ; rm File2.txt");
                        String XYZfile = exec("cat "+getFilesDir()+"/Output-cp2k.xyz");
                        try {
                            while (XYZfile.contains("\t")){  //2 spaces
                                XYZfile = XYZfile.replace("\t", " "); //(2 spaces, 1 space)
                            }
                            while (XYZfile.contains("  ")){  //2 spaces
                                XYZfile = XYZfile.replace("  ", " "); //(2 spaces, 1 space)
                            }
                            while (XYZfile.contains("\n ")){  //2 spaces
                                XYZfile = XYZfile.replace("\n ", "\n"); //(2 spaces, 1 space)
                            }

                            FileOutputStream fileout = openFileOutput("Coordinates.xyz.tmp", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(XYZfile);
                            outputWriter.close();


                            exec("rm "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                            exec("touch "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                            exec("rm "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");
                            exec("touch "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");

                            // in Angstroms, in 0;0, without zoom
                            exec("mv "+getFilesDir()+"/Coordinates.xyz.tmp "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                            exec("sed -i 1,2d "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                            try {
                                Scanner scan = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp"));
                                double radius = 0;
                                int atom_color = 0;
                                int text_color = 0;
                                int atom_number = 0;
                                int atomNumber = 0;
                                // now in Angstroms
                                double radius_Ang = 0;

                                while (scan.hasNext()) {
                                    atomNumber++;

                                    String curLine = scan.nextLine();
                                    String[] splitted = curLine.split(" ");
                                    String atom = splitted[0].trim();
                                    String x_coord = splitted[1].trim();
                                    String y_coord = splitted[2].trim();
                                    String z_coord = splitted[3].trim();

                                    atom_number = atomNumber;

//                        Log.println(Log.INFO, "atom = ", atom);

                                    try {
                                        Scanner scanElmnt = new Scanner(new File(getFilesDir()+"/canvas3d/Elmnts.dat"));
                                        while (scanElmnt.hasNext()) {
                                            String curLineElmnt = scanElmnt.nextLine();
                                            String[] splittedElmnt = curLineElmnt.split(" ");
                                            String elementElmnt = splittedElmnt[0].trim();
                                            String radiusElmnt = splittedElmnt[1].trim();
                                            String atom_colorElmnt = splittedElmnt[2].trim();
                                            String text_colorElmnt = splittedElmnt[3].trim();

                                            radius = Double.valueOf(radiusElmnt);
                                            atom_color = Integer.valueOf(atom_colorElmnt);
                                            text_color = Integer.valueOf(text_colorElmnt);
                                            radius_Ang = radius/100;

                                            if (atom.equals(elementElmnt)) {

                                                // write in Angstroms, in 0;0, without zoom
                                                FileOutputStream fileout3 = openFileOutput("Coordinates.x.tmp", MODE_APPEND);
                                                OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                                                outputWriter3.write(elementElmnt +"\t"+x_coord+"\t"+y_coord+"\t"+z_coord+"\t"+radius_Ang+"\t"+atom_color+"\t"+text_color+"\t"+atom_number+"\t"+ColorAtomBorder+"\t"+"0"+"\n");
                                                outputWriter3.close();
                                            }
                                        }
                                        scanElmnt.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                                // až tady: (za smyčkou)
                                scan.close();
                                exec("rm "+getFilesDir()+"/canvas3d/Coordinates_headless.xyz.tmp");
                                exec("mv "+getFilesDir()+"/Coordinates.x.tmp "+getFilesDir()+"/canvas3d/Coordinates.x.tmp");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                exec("rm "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                exec("touch "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                double BondScale = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/BondScale.tmp"));
                                double ForegroundShiftBonds = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ForegroundShiftBonds.tmp"));
                                double ForegroundShiftText = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ForegroundShiftText.tmp"));
                                int ColorAtomBorder = Integer.valueOf(exec("cat "+getFilesDir()+"/canvas3d/ColorAtomBorder.tmp"));
                                double h_bond_limit_HN = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHN.tmp"));
                                double h_bond_limit_HO = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHO.tmp"));
                                double h_bond_limit_HF = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHF.tmp"));
                                double h_bond_limit_HCl = Double.valueOf(exec("cat "+getFilesDir()+"/canvas3d/HBondHCl.tmp"));
                                try {
                                    Scanner scanX = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates.x.tmp"));
                                    int lineNo1 = 0;
                                    while (scanX.hasNext()) {
                                        lineNo1++;
                                        String curLineX = scanX.nextLine();
                                        String[] splittedX = curLineX.split("\\s");
                                        String atomX = splittedX[0].trim();
                                        String x_coordX = splittedX[1].trim();
                                        String y_coordX = splittedX[2].trim();
                                        String z_coordX = splittedX[3].trim();
                                        String radiusX = splittedX[4].trim();
                                        String atom_colorX = splittedX[5].trim();
                                        String text_colorX = splittedX[6].trim();
                                        String atom_numberX = splittedX[7].trim();
                                        String col_at_borderX = splittedX[8].trim();
                                        String touch_timeX = splittedX[9].trim();
                                        int radius_pixX = (int) (Double.valueOf(radiusX)*100);
                                        // project 3D geometry to z = 0
                                        double A = 0;
                                        double B = 0;
                                        double C = 1;
                                        double D = 0;
                                        double x_projX = Double.valueOf(x_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        double y_projX = Double.valueOf(y_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        double z_projX = Double.valueOf(z_coordX) - A*(Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C)/(Math.pow(A, 2)+Math.pow(B, 2)+Math.pow(C, 2));
                                        // because of canvas - input variables x&y have to be integers, not doubles
                                        int x_projection = (int) (x_projX*100);
                                        int y_projection = (int) (y_projX*100);
                                        int z_projection = (int) (z_projX*100);
                                        // text in front of circles = with less negative z coord
//                        double z_text = 100*(Double.valueOf(z_coord)+0.01);
                                        double z_textX = Double.valueOf(z_coordX)+ForegroundShiftText;
                                        // write the file
                                        FileOutputStream fileout_atoms = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                        OutputStreamWriter outputWriter_atoms = new OutputStreamWriter(fileout_atoms);
                                        outputWriter_atoms.write(atomX+"\t"+col_at_borderX+"\t"+x_projection+"\t"+y_projection+"\t"+touch_timeX+"\t"+"0"+"\t"+z_coordX+"\t"+radius_pixX+"\t"+atom_colorX+"\t"+atom_numberX+"\t"+"C"+"\n");
                                        outputWriter_atoms.write(atomX+"\t"+"0"+"\t"+x_projection+"\t"+y_projection+"\t"+"0"+"\t"+"0"+"\t"+z_textX+"\t"+"0"+"\t"+text_colorX+"\t"+atom_numberX+"\t"+"T"+"\n");
                                        outputWriter_atoms.close();

                                        // second loop - to reveal the bonds
                                        Scanner scan2 = new Scanner(new File(getFilesDir()+"/canvas3d/Coordinates.x.tmp"));
                                        int lineNo2 = 0;
                                        while (scan2.hasNext()) {
                                            lineNo2++;
                                            String curLine2 = scan2.nextLine();
                                            String[] splitted2 = curLine2.split("\\s");
                                            String atom2 = splitted2[0].trim();
                                            String x_coord2 = splitted2[1].trim();
                                            String y_coord2 = splitted2[2].trim();
                                            String z_coord2 = splitted2[3].trim();
                                            String radius2 = splitted2[4].trim();
                                            String atom_color2 = splitted2[5].trim();
                                            String text_color2 = splitted2[6].trim();
                                            String atom_number2 = splitted2[7].trim();
                                            String col_at_border2 = splitted2[8].trim();
                                            String touch_time2 = splitted2[9].trim();

                                            if (lineNo2 >= lineNo1) {
                                                // investigate all distances
                                                double dist_scan1_scan2 = Math.sqrt(Math.pow((Double.valueOf(x_coordX) - Double.valueOf(x_coord2)), 2) + Math.pow((Double.valueOf(y_coordX) - Double.valueOf(y_coord2)), 2) + Math.pow((Double.valueOf(z_coordX) - Double.valueOf(z_coord2)), 2));
                                                double BondingDistance = BondScale * (Double.valueOf(radiusX) + Double.valueOf(radius2));
                                                if ((dist_scan1_scan2 < BondingDistance) && (dist_scan1_scan2 > 0)) {

                                                    double A2 = 0;
                                                    double B2 = 0;
                                                    double C2 = 1;
                                                    double D2 = 0;
                                                    double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                    double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                    double x_bond1 = 100 * x_proj1;
                                                    double y_bond1 = 100 * y_proj1;
                                                    double x_bond2 = 100 * x_proj2;
                                                    double y_bond2 = 100 * y_proj2;

//                                int bond_color = Color.GRAY;
                                                    int bond_color1 = Integer.valueOf(atom_colorX);
                                                    int bond_color2 = Integer.valueOf(atom_color2);

                                                    // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                    double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                    // write the file
                                                    FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                    OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                    outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "L" + "\n");
                                                    outputWriter_bonds.close();
                                                } else if ((dist_scan1_scan2 >= BondingDistance) && (atomX.equals("H") || atom2.equals("H"))) {
                                                    if (((atomX.equals("H") && atom2.equals("N")) || ((atomX.equals("N") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HN)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("O")) || ((atomX.equals("O") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HO)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("F")) || ((atomX.equals("F") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HF)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    } else if (((atomX.equals("H") && atom2.equals("Cl")) || ((atomX.equals("Cl") && atom2.equals("H")))) && (dist_scan1_scan2 <= h_bond_limit_HCl)) {
                                                        double A2 = 0;
                                                        double B2 = 0;
                                                        double C2 = 1;
                                                        double D2 = 0;
                                                        double x_proj1 = Double.valueOf(x_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj1 = Double.valueOf(y_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj1 = Double.valueOf(z_coordX) - A * (Double.valueOf(x_coordX) * A + Double.valueOf(y_coordX) * B + Double.valueOf(z_coordX) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double x_proj2 = Double.valueOf(x_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double y_proj2 = Double.valueOf(y_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));
                                                        double z_proj2 = Double.valueOf(z_coord2) - A * (Double.valueOf(x_coord2) * A + Double.valueOf(y_coord2) * B + Double.valueOf(z_coord2) * C) / (Math.pow(A, 2) + Math.pow(B, 2) + Math.pow(C, 2));

                                                        double x_bond1 = 100 * x_proj1;
                                                        double y_bond1 = 100 * y_proj1;
                                                        double x_bond2 = 100 * x_proj2;
                                                        double y_bond2 = 100 * y_proj2;

                                                        int bond_color1 = Integer.valueOf(atom_color);
                                                        int bond_color2 = Integer.valueOf(atom_color2);

                                                        // find out the "middle" z-coordinate for the bond, elucidate the case when all atoms are in plane (bonds are hidden)

                                                        double z_bond_average = 0.5 * (Double.valueOf(z_coordX) + Double.valueOf(z_coord2)) + ForegroundShiftBonds;

                                                        // write the file
                                                        FileOutputStream fileout_bonds = openFileOutput("Coordinates.tmp", MODE_APPEND);
                                                        OutputStreamWriter outputWriter_bonds = new OutputStreamWriter(fileout_bonds);
                                                        outputWriter_bonds.write(atomX + "\t" + atom2 + "\t" + x_bond1 + "\t" + y_bond1 + "\t" + x_bond2 + "\t" + y_bond2 + "\t" + z_bond_average + "\t" + bond_color1 + "\t" + bond_color2 + "\t" + "0" + "\t" + "H" + "\n");
                                                        outputWriter_bonds.close();
                                                    }
                                                }
                                            }
                                        }
                                        scan2.close();
                                    }
                                    scanX.close();
                                    exec("mv "+getFilesDir()+"/Coordinates.tmp "+getFilesDir()+"/canvas3d/");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // at the moment, the file Coordinates.tmp has to be sorted by the z_coord value:
                                try {
                                    String Z_sort = exec("sort -g -k7 "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                    FileOutputStream fileout_sort = openFileOutput("Coordinates.tmp_", MODE_PRIVATE);
                                    OutputStreamWriter outputWriter_sort = new OutputStreamWriter(fileout_sort);
                                    outputWriter_sort.write(Z_sort);
                                    outputWriter_sort.close();
                                    exec("mv "+getFilesDir()+"/Coordinates.tmp_ "+getFilesDir()+"/canvas3d/Coordinates.tmp");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(MainActivity.this, Canvas3d_main.class);
                        startActivity(intent);

//                        molCanvasView.setMoleculeRenderer(Canvas3d_CanvasView.TRUE);
                        onFinish();
                    }
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                }.start();

            }
        };
    }

    private View.OnClickListener GenerateXYZClick; {

        GenerateXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertGenerateXYZ();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }


    public void alertGenerateXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(MainActivity.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Please write the SMILES string to be converted to XYZ. ")
                .setTitle("OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
//                        String InputFile = MopacInput.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(SmilesString);
                            outputWriter.close();

//                            String ObabelOutput = exec(getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi "+getFilesDir()+"/temp.smi -oxyz --gen3d");
//
//                            FileOutputStream fileout3 = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
//                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
//                            outputWriter3.write(ObabelOutput);
//                            outputWriter3.close();
                            // replacement:
                            String command_smiles = "export HOME=/data/data/cz.jh.cp2k/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > Input-cp2k.xyz";
                            new RunConversion().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, command_smiles);

//                            String SedXyz = exec("sed -e 1,2d "+getFilesDir()+"/temp.xyz");

//                            FileOutputStream fileout4 = openFileOutput("Input-cp2k.xyz", MODE_APPEND);
//                            OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
////                            outputWriter4.write(InputFile);
//                            outputWriter4.write("\n");
//                            outputWriter4.write(SedXyz);
//                            outputWriter4.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        exec("rm "+getFilesDir()+"/temp.xyz");
                        // do not remove - when using AndroidShell
//                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    // Ignore the bad AsyncTask usage.
    final class RunConversion extends AsyncTask<String, Void, CommandResult> {

        private ProgressDialog dialog;

        @Override protected void onPreExecute() {

            // this is cancellable progress dialog
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Conversion is in progress.");
            dialog.setCancelable(false);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog2, int which) {
                    dialog2.dismiss();
                }
            });
            dialog.show();

            // this was the original non-cancellable progress dialog
//            dialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Calculation is in progress.");
//            dialog.setCancelable(false);
        }

        @Override protected CommandResult doInBackground(String... commands) {
            return com.jrummyapps.android.shell.Shell.SH.run(commands);
        }

        @Override protected void onPostExecute(CommandResult result) {
            if (!isFinishing()) {
                dialog.dismiss();
//                outputView2.setText(resultToHtml(result));
                String OutputofExecution = resultToHtml(result).toString();
                try {
                    FileOutputStream fileout = openFileOutput("LastExecutionOutput.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(OutputofExecution);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                outputView2.setText(colorized(OutputofExecution, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", Color.RED));
                outputView2.setText(OutputofExecution);
                output(exec("ls -la "+getFilesDir()+"/"));
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));}
        }

        private Spanned resultToHtml(CommandResult result) {
            StringBuilder html = new StringBuilder();
            // exit status
            html.append("<p><strong>Exit Code:</strong> ");
            if (result.isSuccessful()) {
                html.append("<font color='green'>").append(result.exitCode).append("</font>");
            } else {
                html.append("<font color='red'>").append(result.exitCode).append("</font>");
            }
            html.append("</p>");
            // stdout
            if (result.stdout.size() > 0) {
                html.append("<p><strong>STDOUT:</strong></p><p>")
                        .append(result.getStdout().replaceAll("\n", "<br>"))
                        .append("</p>");
            }
            // stderr
            if (result.stderr.size() > 0) {
                html.append("<p><strong>STDERR:</strong></p><p><font color='red'>")
                        .append(result.getStderr().replaceAll("\n", "<br>"))
                        .append("</font></p>");
            }
            return Html.fromHtml(html.toString());
        }

    }

    private View.OnClickListener opsinXYZClick; {

        opsinXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertOpsinXYZ();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }


    public void alertOpsinXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(MainActivity.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Please write the chemical name according to IUPAC to XYZ conversion. The result will be appended to the actual input file.")
                .setTitle("OPSIN+OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
//                        String InputFile = MopacInput.getText().toString();
                        try {
                            ////////////////////////////////////
                            NameToStructure nts = NameToStructure.getInstance();
                            NameToStructureConfig ntsconfig = new NameToStructureConfig();
//a new NameToStructureConfig starts as a copy of OPSIN's default configuration
                            ntsconfig.setAllowRadicals(true);
//                OpsinResult result = nts.parseChemicalName("acetamide", ntsconfig);
                            OpsinResult result = nts.parseChemicalName(SmilesString+"", ntsconfig);
                            String smiles = result.getSmiles();
                            /////////////////////////////////////
                            FileOutputStream fileout2 = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                            outputWriter2.write(smiles);
                            outputWriter2.close();

//                            String ObabelOutput = exec(getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi "+getFilesDir()+"/temp.smi -oxyz --gen3d");
//
//                            FileOutputStream fileout3 = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
//                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
//                            outputWriter3.write(ObabelOutput);
//                            outputWriter3.close();

                            String command_smiles = "export HOME=/data/data/cz.jh.cp2k/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > Input-cp2k.xyz";
                            new RunConversion().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, command_smiles);

//                            String SedXyz = exec("sed -e 1,2d "+getFilesDir()+"/temp.xyz");
//
//                            FileOutputStream fileout4 = openFileOutput("Input-cp2k.xyz", MODE_APPEND);
//                            OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
////                            outputWriter4.write(InputFile);
//                            outputWriter4.write("\n");
//                            outputWriter4.write(SedXyz);
//                            outputWriter4.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        exec("rm "+getFilesDir()+"/temp.xyz");
                        // do not remove - when using AndroidShell
//                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                        output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                        output5(exec("cat "+getFilesDir()+"/Command.txt"));
                        output(exec("ls -la "+getFilesDir()+"/"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener GraphClick; {

        GraphClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, Graph.class);
                startActivity(intent);
            }
        };
    }


    private View.OnClickListener openInputfileClick; {

        openInputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                read6(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener openInfileClick; {

        openInfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                read26(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener openCommandfileClick; {

        openCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                read60(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener saveExtInputfileClick; {

        saveExtInputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                write1(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener saveExtInfileClick; {

        saveExtInfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                write01(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener saveExtCommandfileClick; {

        saveExtCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                write10(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private View.OnClickListener saveExtOutputfileClick; {

        saveExtOutputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                write2(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }

    private void read6(Context context6) {
        Intent intent6 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent6.addCategory(Intent.CATEGORY_OPENABLE);
        intent6.setType("text/plain");
        startActivityForResult(intent6, READ_FILE6);
    }

    private void read26(Context context26) {
        Intent intent26 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent26.addCategory(Intent.CATEGORY_OPENABLE);
        intent26.setType("text/plain");
        startActivityForResult(intent26, READ_FILE26);
    }

    private void write1(Context context1) {
        Intent intent1 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_TITLE,"MyInputFile");
        startActivityForResult(intent1, CREATE_FILE20);
    }

    private void write01(Context context01) {
        Intent intent01 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent01.addCategory(Intent.CATEGORY_OPENABLE);
        intent01.setType("text/plain");
        intent01.putExtra(Intent.EXTRA_TITLE,"MyInputFile");
        startActivityForResult(intent01, CREATE_FILE01);
    }

    private void write2(Context context2) {
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TITLE,"MyOutputFile");
        startActivityForResult(intent2, CREATE_FILE21);
    }

    private void read60(Context context60) {
        Intent intent60 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent60.addCategory(Intent.CATEGORY_OPENABLE);
        intent60.setType("text/plain");
        startActivityForResult(intent60, READ_FILE60);
    }

    private void write10(Context context10) {
        Intent intent10 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent10.addCategory(Intent.CATEGORY_OPENABLE);
        intent10.setType("text/plain");
        intent10.putExtra(Intent.EXTRA_TITLE,"MyCommand");
        startActivityForResult(intent10, CREATE_FILE200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_FILE6 && data != null) {
            try {
                documentUri6 = data.getData();
                String myData6 = "";
                ParcelFileDescriptor pfd6 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd6.getFileDescriptor());
                DataInputStream inp6 = new DataInputStream(fileInputStream);
                BufferedReader br6 = new BufferedReader(new InputStreamReader(inp6));
                String strLine6;
                while ((strLine6 = br6.readLine()) != null) {
                    myData6 = myData6 + strLine6 + "\n";
                }
                inp6.close();

                FileOutputStream fileout6 = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                outputWriter6.write(myData6);
                outputWriter6.close();
                fileInputStream.close();
                pfd6.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == READ_FILE26 && data != null) {
            try {
                documentUri26 = data.getData();
                String myData26 = "";
                ParcelFileDescriptor pfd26 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd26.getFileDescriptor());
                DataInputStream inp26 = new DataInputStream(fileInputStream);
                BufferedReader br26 = new BufferedReader(new InputStreamReader(inp26));
                String strLine26;
                while ((strLine26 = br26.readLine()) != null) {
                    myData26 = myData26 + strLine26 + "\n";
                }
                inp26.close();

                FileOutputStream fileout26 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter26 = new OutputStreamWriter(fileout26);
                outputWriter26.write(myData26);
                outputWriter26.close();
                fileInputStream.close();
                pfd26.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE20 && data != null) {
            // save input file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                String fileContents20X = InputFile.getText().toString();
                FileOutputStream fileout20 = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter20 = new OutputStreamWriter(fileout20);
                outputWriter20.write(fileContents20X + "\n");
                outputWriter20.close();

                documentUri20 = data.getData();
                ParcelFileDescriptor pfd20 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream20 = new FileOutputStream(pfd20.getFileDescriptor());
//                String fileContents20 = InputFile.getText().toString();
                String fileContents20 = exec("cat "+getFilesDir()+"/Input-cp2k.xyz");
                fileOutputStream20.write((fileContents20 + "\n").getBytes());
                fileOutputStream20.close();
                pfd20.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE01 && data != null) {
            // save input file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                String fileContents0X = InFile.getText().toString();
                FileOutputStream fileout0 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter0 = new OutputStreamWriter(fileout0);
                outputWriter0.write(fileContents0X + "\n");
                outputWriter0.close();

                documentUri0 = data.getData();
                ParcelFileDescriptor pfd0 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream0 = new FileOutputStream(pfd0.getFileDescriptor());
//                String fileContents20 = InputFile.getText().toString();
                String fileContents0 = exec("cat "+getFilesDir()+"/Input-cp2k.txt");
                fileOutputStream0.write((fileContents0 + "\n").getBytes());
                fileOutputStream0.close();
                pfd0.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE21 && data != null) {
            // save output file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri21 = data.getData();
                ParcelFileDescriptor pfd21 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream21 = new FileOutputStream(pfd21.getFileDescriptor());
                String fileContents21 = outputView2.getText().toString();
                fileOutputStream21.write((fileContents21 + "\n").getBytes());
                fileOutputStream21.close();
                pfd21.close();
                FileOutputStream fileout21 = openFileOutput("Input.out", MODE_PRIVATE);
                OutputStreamWriter outputWriter21 = new OutputStreamWriter(fileout21);
                outputWriter21.write(fileContents21 + "\n");
                outputWriter21.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == READ_FILE60 && data != null) {
            try {
                documentUri60 = data.getData();
                String myData60 = "";
                ParcelFileDescriptor pfd60 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream60 = new FileInputStream(pfd60.getFileDescriptor());
                DataInputStream inp60 = new DataInputStream(fileInputStream60);
                BufferedReader br60 = new BufferedReader(new InputStreamReader(inp60));
                String strLine60;
                while ((strLine60 = br60.readLine()) != null) {
                    myData60 = myData60 + strLine60 + "\n";
                }
                inp60.close();

                FileOutputStream fileout60 = openFileOutput("Command.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter60 = new OutputStreamWriter(fileout60);
                outputWriter60.write(myData60);
                outputWriter60.close();
                fileInputStream60.close();
                pfd60.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE200 && data != null) {
            // save command file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri200 = data.getData();
                ParcelFileDescriptor pfd200 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream200 = new FileOutputStream(pfd200.getFileDescriptor());
                String fileContents200 = Command.getText().toString();
                fileOutputStream200.write((fileContents200 + "\n").getBytes());
                fileOutputStream200.close();
                pfd200.close();
                FileOutputStream fileout200 = openFileOutput("Command.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter200 = new OutputStreamWriter(fileout200);
                outputWriter200.write(fileContents200);
                outputWriter200.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private View.OnClickListener saveInputfileClick; {

        saveInputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertSaveInput();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }


    public void alertSaveInput(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(MainActivity.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.cp2k/files/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = InputFile.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile+"\n");
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveInputName+" "+getFilesDir()+"/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener saveInfileClick; {

        saveInfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertSaveIn();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }


    public void alertSaveIn(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(MainActivity.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.cp2k/files/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = InFile.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile+"\n");
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveInputName+" "+getFilesDir()+"/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }


    private View.OnClickListener saveCommandfileClick; {

        saveCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertSaveCommand();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }


    public void alertSaveCommand(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(MainActivity.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.cp2k/files/commands")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = Command.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile);
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveInputName+" "+getFilesDir()+"/commands");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }


    private View.OnClickListener RunProgramClick; {

        RunProgramClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Inputfile = InputFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Infile = InFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input-cp2k.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("Command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("Input-cp2k.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Infile);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Arguments = Arguments.replace(" obabel ", " "+getApplicationInfo().nativeLibraryDir+"/libobabel.so ");
                Arguments = Arguments.replace(" cp2k ", " "+getApplicationInfo().nativeLibraryDir+"/libcp2k.so ");
                Arguments = Arguments.replace(" dbm_miniapp ", " "+getApplicationInfo().nativeLibraryDir+"/libdbm_miniapp.so ");
                Arguments = Arguments.replace(" dumpdcd ", " "+getApplicationInfo().nativeLibraryDir+"/libdumpdcd.so ");
                Arguments = Arguments.replace(" graph ", " "+getApplicationInfo().nativeLibraryDir+"/libgraph.so ");
                Arguments = Arguments.replace(" grid_miniapp ", " "+getApplicationInfo().nativeLibraryDir+"/libgrid_miniapp.so ");
                Arguments = Arguments.replace(" xbbc ", " "+getApplicationInfo().nativeLibraryDir+"/libxbbc.so ");
                Arguments = Arguments.replace(" xbvm ", " "+getApplicationInfo().nativeLibraryDir+"/libxbvm.so ");
                Arguments = Arguments.replace(" xyz2dcd ", " "+getApplicationInfo().nativeLibraryDir+"/libxyz2dcd.so ");

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(InputFile.getWindowToken(), 0);
//                String command = ErgoInput.getText().toString();
//                String LibPath = "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:"+getApplicationInfo().nativeLibraryDir+" ; ";

                String command = "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:"+getApplicationInfo().nativeLibraryDir+":"+getFilesDir()+" ; "+Arguments;

//                String command = LibPath.concat(Arguments);
//                String command = " export HOME="+getFilesDir()+"/ ; cd $HOME ; "+getApplicationInfo().nativeLibraryDir+"/"+NameOfProgram+" "+Arguments;

                new RunCommandTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, command);

                // TODO Auto-generated method stub //
//                openprogressdialog();
            }
        };
    }

    // Ignore the bad AsyncTask usage.
    final class RunCommandTask extends AsyncTask<String, Void, CommandResult> {

        private ProgressDialog dialog;

        @Override protected void onPreExecute() {

            // this is cancellable progress dialog
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Calculation is in progress.");
            dialog.setCancelable(false);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog2, int which) {
                    dialog2.dismiss();
                }
            });
            dialog.show();

            // this was the original non-cancellable progress dialog
//            dialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Calculation is in progress.");
//            dialog.setCancelable(false);
        }

        @Override protected CommandResult doInBackground(String... commands) {
            return com.jrummyapps.android.shell.Shell.SH.run(commands);
        }

        @Override protected void onPostExecute(CommandResult result) {
            if (!isFinishing()) {
                dialog.dismiss();
//                outputView2.setText(resultToHtml(result));
                String OutputofExecution = resultToHtml(result).toString();
                try {
                    FileOutputStream fileout = openFileOutput("LastExecutionOutput.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(OutputofExecution);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                outputView2.setText(colorized(OutputofExecution, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", Color.RED));
                outputView2.setText(OutputofExecution);
                output(exec("ls -la "+getFilesDir()+"/"));
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));}
        }

        private Spanned resultToHtml(CommandResult result) {
            StringBuilder html = new StringBuilder();
            // exit status
            html.append("<p><strong>Exit Code:</strong> ");
            if (result.isSuccessful()) {
                html.append("<font color='green'>").append(result.exitCode).append("</font>");
            } else {
                html.append("<font color='red'>").append(result.exitCode).append("</font>");
            }
            html.append("</p>");
            // stdout
            if (result.stdout.size() > 0) {
                html.append("<p><strong>STDOUT:</strong></p><p>")
                        .append(result.getStdout().replaceAll("\n", "<br>"))
                        .append("</p>");
            }
            // stderr
            if (result.stderr.size() > 0) {
                html.append("<p><strong>STDERR:</strong></p><p><font color='red'>")
                        .append(result.getStderr().replaceAll("\n", "<br>"))
                        .append("</font></p>");
            }
            return Html.fromHtml(html.toString());
        }

    }


    public void output2(final String str2) {
        Runnable proc2 = new Runnable() {
            public void run() {
                outputView2.setText(str2);
            }
        };
        handler.post(proc2);
    }















    private View.OnClickListener saveOutputfileClick; {

        saveOutputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertSaveOutput();
                output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                output5(exec("cat "+getFilesDir()+"/Command.txt"));
                output(exec("ls -la "+getFilesDir()+"/"));
            }
        };
    }





    public void alertSaveOutput(){
        // creating the EditText widget programatically
        EditText editText15 = new EditText(MainActivity.this);
        editText15.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText15.setTypeface(Typeface.MONOSPACE);
        editText15.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                editText15.removeTextChangedListener(this);
                String text = editText15.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText15.getText().clear();
                editText15.append(colorized_numbers(text));
                // place the cursor at the original position
                editText15.setSelection(startChanged+countChanged);
                editText15.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.cp2k/files/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText15)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String OutputProtocol = outputView2.getText().toString();
                        String SaveOutputName = editText15.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveOutputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(OutputProtocol);
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveOutputName+" "+getFilesDir()+"/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText15.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }















    private View.OnClickListener HighlightClick; {

        HighlightClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                openhighlightdialog();
            }
        };
    }


    private void openhighlightdialog() {
        // TODO Auto-generated method stub //
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Highlighting numbers is in progress...");
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressDialog.show();

        new Thread() {
            public void run() {
                try {
                    outputX(exec("cat "+getFilesDir()+"/LastExecutionOutput.txt"));
                    output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
                    output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
                    output5(exec("cat "+getFilesDir()+"/Command.txt"));
                    output(exec("ls -la "+getFilesDir()+"/"));
                    Toast.makeText(getApplicationContext(), "Numbers highlighted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }

                onFinish();
            }

            // for displaying the output in the second TextView there must be different output2 than output, including the str2/proc2 variables
            public void outputX(final String strX) {
                Runnable procX = new Runnable() {
                    public void run() {
                        outputView2.setText(colorized_numbers(strX));
                    }
                };
                handler.post(procX);
            }

            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }


    private View.OnClickListener AboutClick; {

        AboutClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertAbout();
            }
        };
    }

    public void alertAbout() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("About the cp2k app")
                .setMessage(exec("cat "+getFilesDir()+"/About.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        output3(exec("cat "+getFilesDir()+"/Input-cp2k.xyz"));
        output4(exec("cat "+getFilesDir()+"/Input-cp2k.txt"));
        output5(exec("cat "+getFilesDir()+"/Command.txt"));
        output(exec("ls -la "+getFilesDir()+"/"));
    }

    // for displaying the output in the second TextView there must be different output3 than output, including the str3/proc3 variables
    public void output3(final String str3) {
        Runnable proc3 = new Runnable() {
            public void run() {
                InputFile.setText(str3);
            }
        };
        handler.post(proc3);
    }
    public void output4(final String str4) {
        Runnable proc4 = new Runnable() {
            public void run() {
                InFile.setText(str4);
            }
        };
        handler.post(proc4);
    }
    public void output5(final String str5) {
        Runnable proc5 = new Runnable() {
            public void run() {
                Command.setText(str5);
            }
        };
        handler.post(proc5);
    }
    public void output(final String str) {
        Runnable proc = new Runnable() {
            public void run() {
                Content.setText(colorized_numbers(str));
            }
        };
        handler.post(proc);
    }

    // Executes UNIX command.
    private String exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[65536];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    protected void copyFromAssetsToInternalStorage(String filename){
        AssetManager assetManager = getAssets();

        try {
            InputStream input = assetManager.open(filename);
            OutputStream output = openFileOutput(filename, Context.MODE_PRIVATE);

            copyFile2(input, output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile2(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[65536];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

//    public static void unzip(File source, String out) throws IOException {
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {
//
//            ZipEntry entry = zis.getNextEntry();
//
//            while (entry != null) {
//
//                File file = new File(out, entry.getName());
//
//                if (entry.isDirectory()) {
//                    file.mkdirs();
//                } else {
//                    File parent = file.getParentFile();
//
//                    if (!parent.exists()) {
//                        parent.mkdirs();
//                    }
//
//                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
//
//                        int bufferSize = Math.toIntExact(entry.getSize());
//                        byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1];
//                        int location;
//
//                        while ((location = zis.read(buffer)) != -1) {
//                            bos.write(buffer, 0, location);
//                        }
//                    }
//                }
//                entry = zis.getNextEntry();
//            }
//        }
//    }

    public void unzip(File source) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {

            ZipEntry entry = zis.getNextEntry();
            File outPath = new File(getFilesDir()+"");

            while (entry != null) {
                File file = new File(outPath, entry.getName());
                String canonicalPath = file.getCanonicalPath();
                if (canonicalPath.startsWith(outPath.getCanonicalPath() + File.separator)) {
//                    File verifiedFile = new File(canonicalPath,entry.getName());
//                    continue;
                    if (entry.isDirectory()) {
                        file.mkdirs();
                    } else {
                        File parent = file.getParentFile();

                        if (!parent.exists()) {
                            parent.mkdirs();
                        }

                        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

                            int bufferSize = Math.toIntExact(entry.getSize());
                            byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1];
                            int location;

                            while ((location = zis.read(buffer)) != -1) {
                                bos.write(buffer, 0, location);
                            }
                        }
                    }
                    entry = zis.getNextEntry();
                } else {
                    throw new IllegalStateException("Unreachable location");
                }
            }
        }
    }

}