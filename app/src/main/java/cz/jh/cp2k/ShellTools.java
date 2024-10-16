package cz.jh.cp2k;

import static cz.jh.cp2k.Spannables.colorized_bash;
import static cz.jh.cp2k.Spannables.colorized_numbers;
import static cz.jh.cp2k.Spannables.colorized_x11basic;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class ShellTools extends MainActivity {

    private TextView RunX11Label;
    private EditText RunX11;
    private TextView X11Content;
    private EditText X11Name;
    private Button RunX11Button;
    private TextView RunX11OutputLabel;
    private EditText RunX11Output;
    private TextView CatOutputLabel;
    private EditText CatOutput;
    private Button CatOutputButton;
    private TextView CatResponseLabel;
    private EditText CatResponse;
    private TextView DeleteLabel;
    private EditText Delete;
    private Button DeleteButton;
    private TextView Shell0Label;
    private EditText Shell0;
    private Button Shell0Button;
    private TextView ShellLabel;
    private EditText Shell;
    private Button ShellButton;
    private TextView ExecuteOutputLabel;
    private EditText ExecuteOutput;
    private Button Quit;
    Button manual_x11basic;
    private TextView NativeLibLabel;
    private EditText NativeLibPath;
    //important:
    private Handler handler = new Handler();
    //not so:!!!
    // private Handler handler;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.shelltools);

        RunX11Label = (TextView) findViewById(R.id.RunX11Label);
        RunX11 = (EditText) findViewById(R.id.RunX11);
        RunX11.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        RunX11.addTextChangedListener(new TextWatcher() {
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
                RunX11.removeTextChangedListener(this);
                String text = RunX11.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                RunX11.getText().clear();
                RunX11.append(colorized_x11basic(text));
                // place the cursor at the original position
                RunX11.setSelection(startChanged+countChanged);
                RunX11.addTextChangedListener(this);
            }
        });
        X11Content = (TextView) findViewById(R.id.X11Content);
        X11Name = (EditText) findViewById(R.id.X11Name);
        X11Name.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        X11Name.addTextChangedListener(new TextWatcher() {
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
                X11Name.removeTextChangedListener(this);
                String text = X11Name.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                X11Name.getText().clear();
                X11Name.append(colorized_numbers(text));
                // place the cursor at the original position
                X11Name.setSelection(startChanged+countChanged);
                X11Name.addTextChangedListener(this);
            }
        });
        RunX11Button = (Button) findViewById(R.id.RunX11Button);
        RunX11Button.setOnClickListener(RunX11ButtonClick);
        RunX11OutputLabel = (TextView) findViewById(R.id.RunX11OutputLabel);
        RunX11Output = (EditText) findViewById(R.id.RunX11Output);
        RunX11Output.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        // disable - otherwise the text could not be selected
//        RunX11Output.setMovementMethod(new ScrollingMovementMethod());
        RunX11Output.addTextChangedListener(new TextWatcher() {
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
                RunX11Output.removeTextChangedListener(this);
                String text = RunX11Output.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                RunX11Output.getText().clear();
                RunX11Output.append(colorized_numbers(text));
                // place the cursor at the original position
                RunX11Output.setSelection(startChanged+countChanged);
                RunX11Output.addTextChangedListener(this);
            }
        });
        CatOutputLabel = (TextView) findViewById(R.id.CatOutputLabel);
        CatOutput = (EditText) findViewById(R.id.CatOutput);
        CatOutput.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        CatOutput.addTextChangedListener(new TextWatcher() {
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
                CatOutput.removeTextChangedListener(this);
                String text = CatOutput.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                CatOutput.getText().clear();
                CatOutput.append(colorized_numbers(text));
                // place the cursor at the original position
                CatOutput.setSelection(startChanged+countChanged);
                CatOutput.addTextChangedListener(this);
            }
        });
        CatOutputButton = (Button) findViewById(R.id.CatOutputButton);
        CatOutputButton.setOnClickListener(CatButtonClick);
        CatResponseLabel = (TextView) findViewById(R.id.CatResponseLabel);
        CatResponse = (EditText) findViewById(R.id.CatResponse);
        CatResponse.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        CatResponse.addTextChangedListener(new TextWatcher() {
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
                CatResponse.removeTextChangedListener(this);
                String text = CatResponse.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                CatResponse.getText().clear();
                CatResponse.append(colorized_numbers(text));
                // place the cursor at the original position
                CatResponse.setSelection(startChanged+countChanged);
                CatResponse.addTextChangedListener(this);
            }
        });
        DeleteLabel = (TextView) findViewById(R.id.DeleteLabel);
        Delete = (EditText) findViewById(R.id.Delete);
        Delete.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Delete.addTextChangedListener(new TextWatcher() {
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
                Delete.removeTextChangedListener(this);
                String text = Delete.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Delete.getText().clear();
                Delete.append(colorized_numbers(text));
                // place the cursor at the original position
                Delete.setSelection(startChanged+countChanged);
                Delete.addTextChangedListener(this);
            }
        });
        DeleteButton = (Button) findViewById(R.id.DeleteButton);
        DeleteButton.setOnClickListener(DeleteButtonClick);
        Shell0Label = (TextView) findViewById(R.id.Shell0Label);
        Shell0 = (EditText) findViewById(R.id.Shell0);
        Shell0.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Shell0.addTextChangedListener(new TextWatcher() {
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
                Shell0.removeTextChangedListener(this);
                String text = Shell0.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Shell0.getText().clear();
                Shell0.append(colorized_bash(text));
                // place the cursor at the original position
                Shell0.setSelection(startChanged+countChanged);
                Shell0.addTextChangedListener(this);
            }
        });
        Shell0Button = (Button) findViewById(R.id.Shell0Button);
        Shell0Button.setOnClickListener(Shell0ButtonClick);
        ShellLabel = (TextView) findViewById(R.id.ShellLabel);
        Shell = (EditText) findViewById(R.id.Shell);
        Shell.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Shell.addTextChangedListener(new TextWatcher() {
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
                Shell.removeTextChangedListener(this);
                String text = Shell.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Shell.getText().clear();
                Shell.append(colorized_bash(text));
                // place the cursor at the original position
                Shell.setSelection(startChanged+countChanged);
                Shell.addTextChangedListener(this);
            }
        });
        ShellButton = (Button) findViewById(R.id.ShellButton);
        ShellButton.setOnClickListener(ShellButtonClick);
        ExecuteOutputLabel = (TextView) findViewById(R.id.ExecuteOutputLabel);
        ExecuteOutput = (EditText) findViewById(R.id.ExecuteOutput);
        ExecuteOutput.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        // disable - otherwise the text could not be selected
//        ExecuteOutput.setMovementMethod(new ScrollingMovementMethod());
        ExecuteOutput.addTextChangedListener(new TextWatcher() {
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
                ExecuteOutput.removeTextChangedListener(this);
                String text = ExecuteOutput.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                ExecuteOutput.getText().clear();
                ExecuteOutput.append(colorized_numbers(text));
                // place the cursor at the original position
                ExecuteOutput.setSelection(startChanged+countChanged);
                ExecuteOutput.addTextChangedListener(this);
            }
        });
        Quit = (Button) findViewById(R.id.Quit);
        Quit.setOnClickListener(QuitClick);
        NativeLibLabel = (TextView) findViewById(R.id.NativeLibLabel);
        NativeLibPath = (EditText) findViewById(R.id.NativeLibPath);
        NativeLibPath.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        NativeLibPath.addTextChangedListener(new TextWatcher() {
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
                NativeLibPath.removeTextChangedListener(this);
                String text = NativeLibPath.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                NativeLibPath.getText().clear();
                NativeLibPath.append(colorized_numbers(text));
                // place the cursor at the original position
                NativeLibPath.setSelection(startChanged+countChanged);
                NativeLibPath.addTextChangedListener(this);
            }
        });
        manual_x11basic = (Button) findViewById(R.id.manual_x11basic);
        manual_x11basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShellTools.this, ManualX11Basic.class);
                startActivity(intent);
            }
        });

    }

    public void onStart()
    {
        super.onStart();
        String Filename = X11Name.getText().toString();
        RunX11Display(exec("cat "+getFilesDir()+"/"+Filename+".bas"));
        LibDisplay(getApplicationInfo().nativeLibraryDir);




        X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
        X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
        CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
        DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
        Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
        ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
    }


    private View.OnClickListener RunX11ButtonClick; {
        RunX11ButtonClick = new View.OnClickListener() {
            public void onClick(View v) {

                String ShellTX11Name = X11Name.getText().toString();
                String ShellTX11Content = RunX11.getText().toString();
                String ShellTCat1 = CatOutput.getText().toString();
                String ShellTDelete = Delete.getText().toString();
                String ShellTShell0 = Shell0.getText().toString();
                String ShellTShell = Shell.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("ShellToolsX11Name.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(ShellTX11Name);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("ShellToolsX11Content.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(ShellTX11Content);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("ShellToolsCat1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(ShellTCat1);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("ShellToolsDelete.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(ShellTDelete);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("ShellToolsShell0.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ShellTShell0);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("ShellToolsShell.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(ShellTShell);
                    outputWriter6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String X11File = RunX11.getText().toString();
                String Filename = X11Name.getText().toString();
                // TODO Auto-generated method stub //
                try {
                    exec("chmod -R 755 "+getFilesDir());
                    FileOutputStream fileout = openFileOutput(Filename+".bas", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(X11File);
                    outputWriter.close();
                    try {

                        exec(getApplicationInfo().nativeLibraryDir+"/libxbbc.so -o "+getFilesDir()+"/"+Filename+".b "+getFilesDir()+"/"+Filename+".bas");
                        X11Display(exec(getApplicationInfo().nativeLibraryDir+"/libxbvm.so "+getFilesDir()+"/"+Filename+".b"));
                        RunX11Display(exec("cat "+getFilesDir()+"/"+Filename+".bas"));
                        LibDisplay(getApplicationInfo().nativeLibraryDir);
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                }
                X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
                X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
                CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
                DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
                Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
                ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
            }
        };
    }

    private View.OnClickListener CatButtonClick; {
        CatButtonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String ShellTX11Name = X11Name.getText().toString();
                String ShellTX11Content = RunX11.getText().toString();
                String ShellTCat1 = CatOutput.getText().toString();
                String ShellTDelete = Delete.getText().toString();
                String ShellTShell0 = Shell0.getText().toString();
                String ShellTShell = Shell.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("ShellToolsX11Name.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(ShellTX11Name);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("ShellToolsX11Content.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(ShellTX11Content);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("ShellToolsCat1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(ShellTCat1);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("ShellToolsDelete.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(ShellTDelete);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("ShellToolsShell0.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ShellTShell0);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("ShellToolsShell.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(ShellTShell);
                    outputWriter6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    exec("chmod -R 755 "+getFilesDir());
                    try {
                        CatDisplay(exec("cat "+getFilesDir()+"/"+CatOutput.getText().toString()));
                        String Filename = X11Name.getText().toString();
                        RunX11Display(exec("cat "+getFilesDir()+"/"+Filename+".bas"));
                        LibDisplay(getApplicationInfo().nativeLibraryDir);
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                }
                X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
                X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
                CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
                DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
                Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
                ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
            }
        };
    }

    private View.OnClickListener DeleteButtonClick; {
        DeleteButtonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String ShellTX11Name = X11Name.getText().toString();
                String ShellTX11Content = RunX11.getText().toString();
                String ShellTCat1 = CatOutput.getText().toString();
                String ShellTDelete = Delete.getText().toString();
                String ShellTShell0 = Shell0.getText().toString();
                String ShellTShell = Shell.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("ShellToolsX11Name.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(ShellTX11Name);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("ShellToolsX11Content.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(ShellTX11Content);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("ShellToolsCat1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(ShellTCat1);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("ShellToolsDelete.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(ShellTDelete);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("ShellToolsShell0.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ShellTShell0);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("ShellToolsShell.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(ShellTShell);
                    outputWriter6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    exec("chmod -R 755 "+getFilesDir());
                    exec("rm -rf "+getFilesDir()+"/"+Delete.getText().toString());
                    try {
                        RunX11Display(exec("cat "+getFilesDir()+"/test.bas"));
                        LibDisplay(getApplicationInfo().nativeLibraryDir);
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                }
                X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
                X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
                CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
                DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
                Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
                ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
            }
        };
    }

    // original simple code
    private View.OnClickListener Shell0ButtonClick; {
        Shell0ButtonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String ShellTX11Name = X11Name.getText().toString();
                String ShellTX11Content = RunX11.getText().toString();
                String ShellTCat1 = CatOutput.getText().toString();
                String ShellTDelete = Delete.getText().toString();
                String ShellTShell0 = Shell0.getText().toString();
                String ShellTShell = Shell.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("ShellToolsX11Name.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(ShellTX11Name);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("ShellToolsX11Content.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(ShellTX11Content);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("ShellToolsCat1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(ShellTCat1);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("ShellToolsDelete.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(ShellTDelete);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("ShellToolsShell0.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ShellTShell0);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("ShellToolsShell.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(ShellTShell);
                    outputWriter6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    exec("chmod -R 755 "+getFilesDir());
                    try {
                        String OutputExec = exec(Shell0.getText().toString());
                        ExecuteOutput.setText(colorized_bash(OutputExec), EditText.BufferType.SPANNABLE);
                        String Filename = X11Name.getText().toString();
                        RunX11Display(exec("cat "+getFilesDir()+"/"+Filename+".bas"));
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                }
                X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
                X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
                CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
                DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
                Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
                ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
            }
        };
    }

    // more advanced shell code with cd, <, >, env variables, export, ./ etc.
    private View.OnClickListener ShellButtonClick; {

        ShellButtonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String ShellTX11Name = X11Name.getText().toString();
                String ShellTX11Content = RunX11.getText().toString();
                String ShellTCat1 = CatOutput.getText().toString();
                String ShellTDelete = Delete.getText().toString();
                String ShellTShell0 = Shell0.getText().toString();
                String ShellTShell = Shell.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("ShellToolsX11Name.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(ShellTX11Name);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("ShellToolsX11Content.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(ShellTX11Content);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("ShellToolsCat1.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(ShellTCat1);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("ShellToolsDelete.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(ShellTDelete);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("ShellToolsShell0.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ShellTShell0);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("ShellToolsShell.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(ShellTShell);
                    outputWriter6.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(Shell.getWindowToken(), 0);
                String Arguments = Shell.getText().toString();

                Arguments = Arguments.replace(" obabel ", " "+getApplicationInfo().nativeLibraryDir+"/libobabel.so ");
                Arguments = Arguments.replace(" cp2k ", " "+getApplicationInfo().nativeLibraryDir+"/libcp2k.so ");
                Arguments = Arguments.replace(" dbm_miniapp ", " "+getApplicationInfo().nativeLibraryDir+"/libdbm_miniapp.so ");
                Arguments = Arguments.replace(" dumpdcd ", " "+getApplicationInfo().nativeLibraryDir+"/libdumpdcd.so ");
                Arguments = Arguments.replace(" graph ", " "+getApplicationInfo().nativeLibraryDir+"/libgraph.so ");
                Arguments = Arguments.replace(" grid_miniapp ", " "+getApplicationInfo().nativeLibraryDir+"/libgrid_miniapp.so ");
                Arguments = Arguments.replace(" xbbc ", " "+getApplicationInfo().nativeLibraryDir+"/libxbbc.so ");
                Arguments = Arguments.replace(" xbvm ", " "+getApplicationInfo().nativeLibraryDir+"/libxbvm.so ");
                Arguments = Arguments.replace(" xyz2dcd ", " "+getApplicationInfo().nativeLibraryDir+"/libxyz2dcd.so ");


                new RunCommandTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Arguments);
                String Filename = X11Name.getText().toString();
                RunX11Display(exec("cat "+getFilesDir()+"/"+Filename+".bas"));
                LibDisplay(getApplicationInfo().nativeLibraryDir);

                X11NameDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Name.txt"));
                X11ContentDisplay(exec("cat "+getFilesDir()+"/ShellToolsX11Content.txt"));
                CatDisplay1(exec("cat "+getFilesDir()+"/ShellToolsCat1.txt"));
                DeleteDisplay(exec("cat "+getFilesDir()+"/ShellToolsDelete.txt"));
                Shell0Display(exec("cat "+getFilesDir()+"/ShellToolsShell0.txt"));
                ShellDisplay(exec("cat "+getFilesDir()+"/ShellToolsShell.txt"));
            }
        };
    }

    // Ignore the bad AsyncTask usage.
    final class RunCommandTask extends AsyncTask<String, Void, CommandResult> {

        //        private ProgressDialog dialog;
        private ProgressDialog progressDialog = new ProgressDialog(ShellTools.this);

        @Override protected void onPreExecute() {
//            dialog = ProgressDialog.show(ShellTools.this, "Running Command", "Please Wait...");
//            dialog.setCancelable(false);
//            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog2, int which) {
//                    dialog2.dismiss();
//                }
//            });
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Running command...");
            progressDialog.setCancelable(false);
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            progressDialog.show();
        }

        @Override protected CommandResult doInBackground(String... commands) {
            return com.jrummyapps.android.shell.Shell.SH.run(commands);
        }

        @Override protected void onPostExecute(CommandResult result) {
            if (!isFinishing()) {
//                dialog.dismiss();
                progressDialog.dismiss();
//                ExecuteOutput.setText(resultToHtml(result));
                String OutputofExecution = resultToHtml(result).toString();
                try {
                    FileOutputStream fileout = openFileOutput("LastExecutionOutput.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(OutputofExecution);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ExecuteOutput.setText(colorized_bash(OutputofExecution), EditText.BufferType.SPANNABLE);
            }
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




    private void X11NameDisplay(final String str996) {
        Runnable proc996 = new Runnable() {
            public void run() {
                X11Name.setText(colorized_numbers(str996), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc996);
    }

    private void X11ContentDisplay(final String str997) {
        Runnable proc997 = new Runnable() {
            public void run() {
                RunX11.setText(colorized_numbers(str997), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc997);
    }

    private void CatDisplay1(final String str998) {
        Runnable proc998 = new Runnable() {
            public void run() {
                CatOutput.setText(colorized_numbers(str998), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc998);
    }

    private void DeleteDisplay(final String str999) {
        Runnable proc999 = new Runnable() {
            public void run() {
                Delete.setText(colorized_numbers(str999), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc999);
    }

    private void Shell0Display(final String str1001) {
        Runnable proc1001 = new Runnable() {
            public void run() {
                Shell0.setText(colorized_bash(str1001), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1001);
    }

    private void ShellDisplay(final String str1002) {
        Runnable proc1002 = new Runnable() {
            public void run() {
                Shell.setText(colorized_bash(str1002), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1002);
    }

    private void LibDisplay(final String str1003) {
        Runnable proc1003 = new Runnable() {
            public void run() {
                NativeLibPath.setText(colorized_numbers(str1003), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1003);
    }

    private void CatDisplay(final String str1004) {
        Runnable proc1004 = new Runnable() {
            public void run() {
                CatResponse.setText(colorized_numbers(str1004), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1004);
    }

    private void X11Display(final String str1006) {
        Runnable proc1006 = new Runnable() {
            public void run() {
                RunX11Output.setText(colorized_numbers(str1006), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1006);
    }

    private void RunX11Display(final String str1007) {
        Runnable proc1007 = new Runnable() {
            public void run() {
                RunX11.setText(colorized_x11basic(str1007), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1007);
    }












    private View.OnClickListener QuitClick; {
        QuitClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                Intent intent = new Intent(ShellTools.this, MainActivity.class);
                startActivity(intent);
            }
        };
    }




}
