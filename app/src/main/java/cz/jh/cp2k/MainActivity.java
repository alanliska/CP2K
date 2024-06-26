package cz.jh.cp2k;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CREATE_FILE1 = 1;
    private static final int CREATE_FILE2 = 2;
    private static final int CREATE_FILE3 = 3;
    private static final int READ_FILE1 = 4;
    private static final int READ_FILE2 = 5;
    private static final int MY_PERMISSION_REQUEST_STORAGE = 1;
    private Uri documentUri1;
    private Uri documentUri2;
    private Uri documentUri3;
    private Uri documentUri4;
    private Uri documentUri5;
    private TextView outputView;
    private EditText outputView2;
    private EditText localPathEdit;
    private Handler handler = new Handler();
    Button button;
    Button buttonNonaq;
    Button databasebutton;
    Button selectdatabasebutton;
    Button selectdatabasebutton2;
    Button openInputfile;
    Button openInputfile2;
    Button saveInputfile;
    Button saveInputfile2;
    Button saveOutputfile;
    Button saveOutputfile2;
    Button saveDatabasefile;
    Button saveDatabasefile2;
    Button Highlight;
    Button About;
    /**
     * Colorize a specific substring in a string for TextView. Use it like this: <pre>
     * textView.setText(
     *     Strings.colorized("The some words are black some are the default.","black", Color.BLACK),
     *     TextView.BufferType.SPANNABLE
     * );
     * </pre>
     * @param text Text that contains a substring to colorize
     * @param word0 The substring to colorize
     * @param word1 The substring to colorize
     * @param word2 The substring to colorize
     * @param word3 The substring to colorize
     * @param word4 The substring to colorize
     * @param word5 The substring to colorize
     * @param word6 The substring to colorize
     * @param word7 The substring to colorize
     * @param word8 The substring to colorize
     * @param word9 The substring to colorize
     * @param word10 The substring to colorize
     * @param word11 The substring to colorize
     * @param word12 The substring to colorize
     * @param argb The color
     * @return the Spannable for TextView's consumption
     */
    public static Spannable colorized(final String text, final String word0, final String word1, final String word2, final String word3, final String word4, final String word5, final String word6, final String word7, final String word8, final String word9, final String word10, final String word11, final String word12, final int argb) {
        final Spannable spannable = new SpannableString(text);
        int substringStart0=0;
        int substringStart1=0;
        int substringStart2=0;
        int substringStart3=0;
        int substringStart4=0;
        int substringStart5=0;
        int substringStart6=0;
        int substringStart7=0;
        int substringStart8=0;
        int substringStart9=0;
        int substringStart10=0;
        int substringStart11=0;
        int substringStart12=0;
        int start0;
        int start1;
        int start2;
        int start3;
        int start4;
        int start5;
        int start6;
        int start7;
        int start8;
        int start9;
        int start10;
        int start11;
        int start12;
        while((start0=text.indexOf(word0,substringStart0))>=0){
            spannable.setSpan(
                    new ForegroundColorSpan(argb),start0,start0+word0.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            while((start1=text.indexOf(word1,substringStart1))>=0) {
                spannable.setSpan(
                        new ForegroundColorSpan(argb), start1, start1 + word1.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                while((start2=text.indexOf(word2,substringStart2))>=0) {
                    spannable.setSpan(
                            new ForegroundColorSpan(argb), start2, start2 + word2.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                    while((start3=text.indexOf(word3,substringStart3))>=0) {
                        spannable.setSpan(
                                new ForegroundColorSpan(argb), start3, start3 + word3.length(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        );
                        while((start4=text.indexOf(word4,substringStart4))>=0) {
                            spannable.setSpan(
                                    new ForegroundColorSpan(argb), start4, start4 + word4.length(),
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            );
                            while((start5=text.indexOf(word5,substringStart5))>=0) {
                                spannable.setSpan(
                                        new ForegroundColorSpan(argb), start5, start5 + word5.length(),
                                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                );
                                while((start6=text.indexOf(word6,substringStart6))>=0) {
                                    spannable.setSpan(
                                            new ForegroundColorSpan(argb), start6, start6 + word6.length(),
                                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                    );
                                    while((start7=text.indexOf(word7,substringStart7))>=0) {
                                        spannable.setSpan(
                                                new ForegroundColorSpan(argb), start7, start7 + word7.length(),
                                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                        );
                                        while((start8=text.indexOf(word8,substringStart8))>=0) {
                                            spannable.setSpan(
                                                    new ForegroundColorSpan(argb), start8, start8 + word8.length(),
                                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                            );
                                            while((start9=text.indexOf(word9,substringStart9))>=0) {
                                                spannable.setSpan(
                                                        new ForegroundColorSpan(argb), start9, start9 + word9.length(),
                                                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                                );
                                                while((start10=text.indexOf(word10,substringStart10))>=0) {
                                                    spannable.setSpan(
                                                            new ForegroundColorSpan(argb), start10, start10 + word10.length(),
                                                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                                    );
                                                    while((start11=text.indexOf(word11,substringStart11))>=0) {
                                                        spannable.setSpan(
                                                                new ForegroundColorSpan(argb), start11, start11 + word11.length(),
                                                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                                        );
                                                        while((start12=text.indexOf(word12,substringStart12))>=0) {
                                                            spannable.setSpan(
                                                                    new ForegroundColorSpan(argb), start12, start12 + word12.length(),
                                                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                                            );
                                                            substringStart12 = start12 + word12.length();
                                                        }
                                                        substringStart11 = start11 + word11.length();
                                                    }
                                                    substringStart10 = start10 + word10.length();
                                                }
                                                substringStart9 = start9 + word9.length();
                                            }
                                            substringStart8 = start8+word8.length();
                                        }
                                        substringStart7 = start7+word7.length();
                                    }
                                    substringStart6 = start6+word6.length();
                                }
                                substringStart5 = start5+word5.length();
                            }
                            substringStart4 = start4+word4.length();
                        }
                        substringStart3 = start3+word3.length();
                    }
                    substringStart2 = start2+word2.length();
                }
                substringStart1 = start1+word1.length();
            }
            substringStart0 = start0+word0.length();
        }
        return spannable;
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputView = (TextView) findViewById(R.id.outputView);
        outputView2 = (EditText) findViewById(R.id.outputView2);
        localPathEdit = (EditText) findViewById(R.id.localPathEdit);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(onButtonClick);
        saveInputfile = (Button) findViewById(R.id.saveInputfile);
        saveInputfile.setOnClickListener(onsaveInputfileClick);
        saveInputfile2 = (Button) findViewById(R.id.saveInputfile2);
        saveInputfile2.setOnClickListener(onsaveInputfileClick2);
        saveOutputfile = (Button) findViewById(R.id.saveOutputfile);
        saveOutputfile.setOnClickListener(onsaveOutputfileClick);
        saveOutputfile2 = (Button) findViewById(R.id.saveOutputfile2);
        saveOutputfile2.setOnClickListener(onsaveOutputfileClick2);
        Highlight = (Button) findViewById(R.id.Highlight);
        Highlight.setOnClickListener(HighlightClick);
        About = (Button) findViewById(R.id.About);
        About.setOnClickListener(onAboutClick);




        openInputfile = (Button) findViewById(R.id.openInputfile);
        openInputfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectInput.class);
                startActivity(intent);
            }
        });

        openInputfile2 = (Button) findViewById(R.id.openInputfile2);
        openInputfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read2(getApplicationContext());
            }
        });

        copyAsset("Input.txt");copyAsset("About.txt");
        {
            copyAsset3("example");
            copyAsset3("ALL_BASIS_SETS");copyAsset3("ALL_POTENTIALS");
            copyAsset3("BASIS_ADMM");
            copyAsset3("BASIS_ADMM_MOLOPT");
            copyAsset3("BASIS_def2_QZVP_RI_ALL");
            copyAsset3("BASIS_LRIGPW_AUXMOLOPT");
            copyAsset3("BASIS_MOLOPT");
            copyAsset3("BASIS_MOLOPT_UCL");
            copyAsset3("BASIS_pob-TZVP");
            copyAsset3("BASIS_RI_cc-TZ");
            copyAsset3("BASIS_SET");
            copyAsset3("BASIS_ZIJLSTRA");
            copyAsset3("dftd3.dat");
            copyAsset3("ECP_POTENTIALS");
            copyAsset3("EMSL_BASIS_SETS");
            copyAsset3("GTH_BASIS_SETS");
            copyAsset3("GTH_POTENTIALS");
            copyAsset3("HFX_BASIS");
            copyAsset3("HF_POTENTIALS");
            copyAsset3("MM_POTENTIAL");
            copyAsset3("NLCC_POTENTIALS");
            copyAsset3("nm12_parameters.xml");
            copyAsset3("POTENTIAL");
            copyAsset3("README");
            copyAsset3("rVV10_kernel_table.dat");
            copyAsset3("t_c_g.dat");
            copyAsset3("t_sh_p_s_c.dat");
            copyAsset3("vdW_kernel_table.dat");
            copyAsset3("PBE.sec");
            copyAsset3("uff_table");
            copyAsset3("bb");
            copyAsset3("bc");
            copyAsset3("bh");
            copyAsset3("bn");
            copyAsset3("cb");
            copyAsset3("cc");
            copyAsset3("cf");
            copyAsset3("ch");
            copyAsset3("clcl_exp");
            copyAsset3("clk_exp");
            copyAsset3("clli_exp");
            copyAsset3("clna_exp");
            copyAsset3("cn");
            copyAsset3("co");
            copyAsset3("csc");
            copyAsset3("csi");
            copyAsset3("fc");
            copyAsset3("ff");
            copyAsset3("fh");
            copyAsset3("hb");
            copyAsset3("hh");
            copyAsset3("hh0");
            copyAsset3("kcl_exp");
            copyAsset3("kk_exp");
            copyAsset3("licl_exp");
            copyAsset3("lili_exp");
            copyAsset3("momo");
            copyAsset3("mos");
            copyAsset3("nacl_exp");
            copyAsset3("nana_exp");
            copyAsset3("nb");
            copyAsset3("nbnb");
            copyAsset3("nbs");
            copyAsset3("nc");
            copyAsset3("nh");
            copyAsset3("nn");
            copyAsset3("no");
            copyAsset3("nonscc_parameter");
            copyAsset3("nsc");
            copyAsset3("nsi");
            copyAsset3("oc");
            copyAsset3("oh");
            copyAsset3("on");
            copyAsset3("oo");
            copyAsset3("osi");
            copyAsset3("osi-d");
            copyAsset3("pp");
            copyAsset3("scc");
            copyAsset3("scn");
            copyAsset3("scsc");
            copyAsset3("sic");
            copyAsset3("sih");
            copyAsset3("sih-d");
            copyAsset3("sin");
            copyAsset3("sio");
            copyAsset3("sio-d");
            copyAsset3("sisi");
            copyAsset3("sisi-d");
            copyAsset3("smo");
            copyAsset3("snb");
            copyAsset3("ss");
            copyAsset3("cc.spl");
            copyAsset3("ch.spl");
            copyAsset3("cn.spl");
            copyAsset3("co.spl");
            copyAsset3("cp.spl");
            copyAsset3("cs.spl");
            copyAsset3("czn.spl");
            copyAsset3("hc.spl");
            copyAsset3("hh.spl");
            copyAsset3("hn.spl");
            copyAsset3("ho.spl");
            copyAsset3("hp.spl");
            copyAsset3("hs.spl");
            copyAsset3("hzn.spl");
            copyAsset3("nc.spl");
            copyAsset3("nh.spl");
            copyAsset3("nn.spl");
            copyAsset3("no.spl");
            copyAsset3("np.spl");
            copyAsset3("ns.spl");
            copyAsset3("nzn.spl");
            copyAsset3("oc.spl");
            copyAsset3("oh.spl");
            copyAsset3("on.spl");
            copyAsset3("oo.spl");
            copyAsset3("op.spl");
            copyAsset3("os.spl");
            copyAsset3("ozn.spl");
            copyAsset3("pc.spl");
            copyAsset3("ph.spl");
            copyAsset3("pn.spl");
            copyAsset3("po.spl");
            copyAsset3("pp.spl");
            copyAsset3("ps.spl");
            copyAsset3("sc.cpl");
            copyAsset3("scc_parameter.spl");
            copyAsset3("sh.spl");
            copyAsset3("sn.spl");
            copyAsset3("so.spl");
            copyAsset3("sp.spl");
            copyAsset3("ss.spl");
            copyAsset3("szn.spl");
            copyAsset3("uff_table.spl");
            copyAsset3("znc.spl");
            copyAsset3("znh.spl");
            copyAsset3("znn.spl");
            copyAsset3("zno.spl");
            copyAsset3("zns.spl");
            copyAsset3("znzn.spl");
        }
        {
            copyAsset4("doc.zip");
            copyAsset4("LICENSE-LAPACK.txt");copyAsset4("LICENSING_TERMS-BLAS.txt");
            copyAsset4("LICENSING_TERMS-LAPACK.txt");
            copyAsset4("LICENSE-CP2K.txt");
        }


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
    }

    private void copyAsset(String filename) {
        File filePath = new File(getFilesDir()+"");
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"cp2k"+File.separator+"LICENSE-CP2K.txt";
        File check = new File(path);
        if (!check.exists()) {
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
        } else {
            // do nothing
        }
    }

    private void copyAsset2(String filename) {
        String dirPath = getFilesDir()+"/database";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"cp2k"+File.separator+"LICENSE-CP2K.txt";
        File check = new File(path);
        File dir = new File(dirPath);
        if (!check.exists()) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            AssetManager assetManager = getAssets();
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(dirPath, filename);
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
        } else {
            // do nothing
        }
    }

    private void copyAsset3(String filename) {
        String dirPath = getFilesDir()+"/work";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"cp2k"+File.separator+"LICENSE-CP2K.txt";
        File check = new File(path);
        File dir = new File(dirPath);
        if (!check.exists()) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            AssetManager assetManager = getAssets();
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(dirPath, filename);
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
        } else {
            // do nothing
        }
    }

    private void copyAsset4(String filename) {
        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"cp2k");
        String path = filePath+File.separator+"LICENSE-CP2K.txt";
        File check = new File(path);
        if (!check.exists()) {
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
        } else {
            // do nothing
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }

    }

    public void onStart()
    {
        super.onStart();
        exec("chmod 755 "+getFilesDir()+"/Input.txt");
        output3(exec("cat "+getFilesDir()+"/Input.txt"));
    }

    private View.OnClickListener onButtonClick; {

        onButtonClick = new View.OnClickListener() {
            public void onClick(View v) {
                String Inputfile = localPathEdit.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("Input.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Inputfile);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // TODO Auto-generated method stub //
                openprogressdialog();
            }
        };
    }

    private void openprogressdialog() {
        // TODO Auto-generated method stub //
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Calculation is running...");
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
                    exec("chmod 755 -R "+getFilesDir()+"/database");
                    exec("chmod 755 -R "+getFilesDir()+"/doc");
                    exec("chmod 755 -R "+getFilesDir()+"/work");
                    exec("chmod 755 -R "+getFilesDir());
                    exec("chmod 755 -R "+getFilesDir()+"/work");
                    exec("mkdir "+getFilesDir()+"/temporary");

                    exec("cp "+getFilesDir()+"/Input.txt "+getFilesDir()+"/temporary/Input");
                    exec("chmod 755 -R "+getFilesDir()+"/temporary");
                    try {
                        FileOutputStream fileout2 = openFileOutput("OUTPUTFILE", MODE_PRIVATE);
                        OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                        outputWriter2.write(exec(getApplicationInfo().nativeLibraryDir+"/libcp2k.so "+getFilesDir()+"/temporary/Input"));
                        outputWriter2.close();

                        File file_orig = new File(getFilesDir()+"/OUTPUTFILE");
                        File file1 = new File(getFilesDir()+"/temporary/Input-pos-1.xyz");
                        if (file1.exists())  {
                            FileWriter fw = new FileWriter(file_orig, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(" \n");
                            bw.write(">> Reading from file Input-pos-1.xyz: \n");
                            bw.write(" \n");
                            //Closing BufferedWriter Stream
                            bw.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line = br.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line != null)
                            {
                                pw.println(line);
                                line = br.readLine();
                            }
                            br = new BufferedReader(new FileReader(file1));
                            line = br.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line != null)
                            {
                                pw.println(line);
                                line = br.readLine();
                            }
                            pw.flush();
                            // closing resources
                            br.close();
                            pw.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }


                        File file_orig2 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file2 = new File(getFilesDir()+"/temporary/Input-BFGS.Hessian");
                        if (file2.exists())  {
                            FileWriter fw2 = new FileWriter(file_orig2, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw2 = new BufferedWriter(fw2);
                            bw2.write(" \n");
                            bw2.write(">> Reading from file Input-BFGS.Hessian: \n");
                            bw2.write(" \n");
                            //Closing BufferedWriter Stream
                            bw2.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw2 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br2 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line2 = br2.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line2 != null)
                            {
                                pw2.println(line2);
                                line2 = br2.readLine();
                            }
                            br2 = new BufferedReader(new FileReader(file2));
                            line2 = br2.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line2 != null)
                            {
                                pw2.println(line2);
                                line2 = br2.readLine();
                            }
                            pw2.flush();
                            // closing resources
                            br2.close();
                            pw2.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }



                        File file_orig3 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file3 = new File(getFilesDir()+"/temporary/Input-1.restart");
                        if (file3.exists())  {
                            FileWriter fw3 = new FileWriter(file_orig3, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw3 = new BufferedWriter(fw3);
                            bw3.write(" \n");
                            bw3.write(">> Reading from file Input-1.restart: \n");
                            bw3.write(" \n");
                            //Closing BufferedWriter Stream
                            bw3.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw3 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br3 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line3 = br3.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line3 != null)
                            {
                                pw3.println(line3);
                                line3 = br3.readLine();
                            }
                            br3 = new BufferedReader(new FileReader(file3));
                            line3 = br3.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line3 != null)
                            {
                                pw3.println(line3);
                                line3 = br3.readLine();
                            }
                            pw3.flush();
                            // closing resources
                            br3.close();
                            pw3.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig4 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file4 = new File(getFilesDir()+"/temporary/Input-1.restart.bak-1");
                        if (file4.exists())  {
                            FileWriter fw4 = new FileWriter(file_orig4, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw4 = new BufferedWriter(fw4);
                            bw4.write(" \n");
                            bw4.write(">> Reading from file Input-1.restart.bak-1: \n");
                            bw4.write(" \n");
                            //Closing BufferedWriter Stream
                            bw4.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw4 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br4 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line4 = br4.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line4 != null)
                            {
                                pw4.println(line4);
                                line4 = br4.readLine();
                            }
                            br4 = new BufferedReader(new FileReader(file4));
                            line4 = br4.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line4 != null)
                            {
                                pw4.println(line4);
                                line4 = br4.readLine();
                            }
                            pw4.flush();
                            // closing resources
                            br4.close();
                            pw4.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }


                        File file_orig5 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file5 = new File(getFilesDir()+"/temporary/Input-1.restart.bak-2");
                        if (file5.exists())  {
                            FileWriter fw5 = new FileWriter(file_orig5, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw5 = new BufferedWriter(fw5);
                            bw5.write(" \n");
                            bw5.write(">> Reading from file Input-1.restart.bak-2: \n");
                            bw5.write(" \n");
                            //Closing BufferedWriter Stream
                            bw5.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw5 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br5 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line5 = br5.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line5 != null)
                            {
                                pw5.println(line5);
                                line5 = br5.readLine();
                            }
                            br5 = new BufferedReader(new FileReader(file5));
                            line5 = br5.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line5 != null)
                            {
                                pw5.println(line5);
                                line5 = br5.readLine();
                            }
                            pw5.flush();
                            // closing resources
                            br5.close();
                            pw5.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig6 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file6 = new File(getFilesDir()+"/temporary/Input-1.restart.bak-3");
                        if (file6.exists())  {
                            FileWriter fw6 = new FileWriter(file_orig6, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw6 = new BufferedWriter(fw6);
                            bw6.write(" \n");
                            bw6.write(">> Reading from file Input-1.restart.bak-3: \n");
                            bw6.write(" \n");
                            //Closing BufferedWriter Stream
                            bw6.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw6 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br6 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line6 = br6.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line6 != null)
                            {
                                pw6.println(line6);
                                line6 = br6.readLine();
                            }
                            br6 = new BufferedReader(new FileReader(file6));
                            line6 = br6.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line6 != null)
                            {
                                pw6.println(line6);
                                line6 = br6.readLine();
                            }
                            pw6.flush();
                            // closing resources
                            br6.close();
                            pw6.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig7 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file7 = new File(getFilesDir()+"/temporary/Input-RESTART.wfn");
                        if (file7.exists())  {
                            FileWriter fw7 = new FileWriter(file_orig7, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw7 = new BufferedWriter(fw7);
                            bw7.write(" \n");
                            bw7.write(">> Reading from file Input-RESTART.wfn: \n");
                            bw7.write(" \n");
                            //Closing BufferedWriter Stream
                            bw7.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw7 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br7 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line7 = br7.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line7 != null)
                            {
                                pw7.println(line7);
                                line7 = br7.readLine();
                            }
                            br7 = new BufferedReader(new FileReader(file7));
                            line7 = br7.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line7 != null)
                            {
                                pw7.println(line7);
                                line7 = br7.readLine();
                            }
                            pw7.flush();
                            // closing resources
                            br7.close();
                            pw7.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig8 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file8 = new File(getFilesDir()+"/temporary/Input-RESTART.wfn.bak-1");
                        if (file8.exists())  {
                            FileWriter fw8 = new FileWriter(file_orig8, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw8 = new BufferedWriter(fw8);
                            bw8.write(" \n");
                            bw8.write(">> Reading from file Input-RESTART.wfn.bak-1: \n");
                            bw8.write(" \n");
                            //Closing BufferedWriter Stream
                            bw8.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw8 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br8 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line8 = br8.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line8 != null)
                            {
                                pw8.println(line8);
                                line8 = br8.readLine();
                            }
                            br8 = new BufferedReader(new FileReader(file8));
                            line8 = br8.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line8 != null)
                            {
                                pw8.println(line8);
                                line8 = br8.readLine();
                            }
                            pw8.flush();
                            // closing resources
                            br8.close();
                            pw8.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig9 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file9 = new File(getFilesDir()+"/temporary/Input-RESTART.wfn.bak-2");
                        if (file9.exists())  {
                            FileWriter fw9 = new FileWriter(file_orig9, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw9 = new BufferedWriter(fw9);
                            bw9.write(" \n");
                            bw9.write(">> Reading from file Input-RESTART.wfn.bak-2: \n");
                            bw9.write(" \n");
                            //Closing BufferedWriter Stream
                            bw9.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw9 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br9 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line9 = br9.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line9 != null)
                            {
                                pw9.println(line9);
                                line9 = br9.readLine();
                            }
                            br9 = new BufferedReader(new FileReader(file9));
                            line9 = br9.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line9 != null)
                            {
                                pw9.println(line9);
                                line9 = br9.readLine();
                            }
                            pw9.flush();
                            // closing resources
                            br9.close();
                            pw9.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        File file_orig10 = new File(getFilesDir()+"/OUTPUTFILE");
                        File file10 = new File(getFilesDir()+"/temporary/Input-RESTART.wfn.bak-3");
                        if (file10.exists())  {
                            FileWriter fw10 = new FileWriter(file_orig10, true);
                            //BufferedWriter writer give better performance
                            BufferedWriter bw10 = new BufferedWriter(fw10);
                            bw10.write(" \n");
                            bw10.write(">> Reading from file Input-RESTART.wfn.bak-3: \n");
                            bw10.write(" \n");
                            //Closing BufferedWriter Stream
                            bw10.close();

                            // PrintWriter object for file3.txt
                            PrintWriter pw10 = new PrintWriter(getFilesDir()+"/OUTPUTFILE3");
                            // BufferedReader object for file1.txt
                            BufferedReader br10 = new BufferedReader(new FileReader(getFilesDir()+"/OUTPUTFILE"));
                            String line10 = br10.readLine();
                            // loop to copy each line of
                            // file1.txt to  file3.txt
                            while (line10 != null)
                            {
                                pw10.println(line10);
                                line10 = br10.readLine();
                            }
                            br10 = new BufferedReader(new FileReader(file10));
                            line10 = br10.readLine();
                            // loop to copy each line of
                            // file2.txt to  file3.txt
                            while(line10 != null)
                            {
                                pw10.println(line10);
                                line10 = br10.readLine();
                            }
                            pw10.flush();
                            // closing resources
                            br10.close();
                            pw10.close();

                            exec("rm "+getFilesDir()+"/OUTPUTFILE");
                            exec("mv "+getFilesDir()+"/OUTPUTFILE3 "+getFilesDir()+"/OUTPUTFILE");

                        }

                        output2(exec("cat "+getFilesDir()+"/OUTPUTFILE"));
                        output("Staying idle.");
                    } catch (Exception e) {
                    }
                    exec("mv "+getFilesDir()+"/temporary/Input.txt "+getFilesDir()+"/Input.txt");
                    exec("rm -rf "+getFilesDir()+"/temporary");
                } catch (Exception e) {
                }
                onFinish();
            }
            // Executes UNIX command.
            private String exec(String command) {
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(process.getInputStream()));
                    int read;
                    char[] buffer = new char[4096];
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

            // for displaying the output in the second TextView there must be different output2 than output, including the str2/proc2 variables
            public void output2(final String str2) {
                Runnable proc2 = new Runnable() {
                    public void run() {
                        outputView2.setText(str2);
                    }
                };
                handler.post(proc2);
            }

            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }


    private View.OnClickListener onsaveInputfileClick; {

        onsaveInputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertSaveInput();
            }
        };
    }

    public void alertSaveInput(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(MainActivity.this);
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = localPathEdit.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile);
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
        // this part will make the soft keyboard automaticall visible
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

    private View.OnClickListener onsaveInputfileClick2; {

        onsaveInputfileClick2 = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                write1(getApplicationContext());
            }
        };
    }

    private View.OnClickListener onsaveOutputfileClick; {

        onsaveOutputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertSaveOutput();
            }
        };
    }

    public void alertSaveOutput(){
        // creating the EditText widget programatically
        EditText editText15 = new EditText(MainActivity.this);
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("The file will be saved in the folder /work")
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
        // this part will make the soft keyboard automaticall visible
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
                    String Results = outputView2.getText().toString();
                    FileOutputStream fileout = openFileOutput("Output.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Results);
                    outputWriter.close();

                    outputX(exec("cat "+getFilesDir()+"/Output.txt"));
                    output3(exec("cat "+getFilesDir()+"/Input.txt"));
                    Toast.makeText(getApplicationContext(), "Numbers highlighted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }

                onFinish();
            }

            // for displaying the output in the second TextView there must be different output2 than output, including the str2/proc2 variables
            public void outputX(final String strX) {
                Runnable procX = new Runnable() {
                    public void run() {
                        outputView2.setText(colorized(strX, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", Color.RED));
                    }
                };
                handler.post(procX);
            }

            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }


    private View.OnClickListener onsaveOutputfileClick2; {

        onsaveOutputfileClick2 = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                write2(getApplicationContext());
            }
        };
    }

    private void write1(Context context1) {
        Intent intent1 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_TITLE,"MyInput");
        startActivityForResult(intent1, CREATE_FILE1);
    }

    private void write2(Context context2) {
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TITLE,"MyOutput");
        startActivityForResult(intent2, CREATE_FILE2);
    }


    private void read2(Context context5) {
        Intent intent5 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent5.addCategory(Intent.CATEGORY_OPENABLE);
        intent5.setType("text/plain");
        startActivityForResult(intent5, READ_FILE2);
    }

    private View.OnClickListener onAboutClick; {

        onAboutClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertAbout();
            }
        };
    }

    public void alertAbout() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("About the CP2K app")
                .setMessage(exec("cat "+getFilesDir()+"/About.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void output(final String str) {
        Runnable proc = new Runnable() {
            public void run() {
                outputView.setText(str);
            }
        };
        handler.post(proc);
    }

    // for displaying the output in the second TextView there must be different output2 than output, including the str2/proc2 variables
    public void output2(final String str2) {
        Runnable proc2 = new Runnable() {
            public void run() {
                outputView2.setText(str2);
            }
        };
        handler.post(proc2);
    }





    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_FILE1 && data != null) {
            // save input file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri1 = data.getData();
                ParcelFileDescriptor pfd1 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream = new FileOutputStream(pfd1.getFileDescriptor());

                String fileContents = localPathEdit.getText().toString();
                fileOutputStream.write((fileContents + "\n").getBytes());
                fileOutputStream.close();
                pfd1.close();
                FileOutputStream fileout = openFileOutput("Input.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(fileContents + "\n");
                outputWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE2 && data != null) {
            // save output file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri2 = data.getData();
                ParcelFileDescriptor pfd2 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream = new FileOutputStream(pfd2.getFileDescriptor());

                String fileContents = outputView2.getText().toString();
                fileOutputStream.write((fileContents + "\n").getBytes());
                fileOutputStream.close();
                pfd2.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == READ_FILE2 && data != null) {
            // open input file
            try {
                documentUri5 = data.getData();
                String myData = "";
                ParcelFileDescriptor pfd5 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd5.getFileDescriptor());
                DataInputStream inp = new DataInputStream(fileInputStream);
                BufferedReader br = new BufferedReader(new InputStreamReader(inp));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    myData = myData + strLine + "\n";
                }
                inp.close();
                localPathEdit.setText(myData);
                outputView2.setText("");
                FileOutputStream fileout = openFileOutput("Input.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(myData);
                outputWriter.close();
                fileInputStream.close();
                pfd5.close();
                Toast.makeText(getApplicationContext(), "Input file read successfully.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Input file not read", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // for displaying the output in the second TextView there must be different output3 than output, including the str3/proc3 variables
    public void output3(final String str3) {
        Runnable proc3 = new Runnable() {
            public void run() {
                localPathEdit.setText(str3);
            }
        };
        handler.post(proc3);
    }
    // Executes UNIX command.
    private String exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
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
}