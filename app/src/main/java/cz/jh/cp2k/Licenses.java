package cz.jh.cp2k;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cz.jh.cp2k.MainActivity;

public class Licenses extends MainActivity {
    TextView license_label;
    Button license0;
    Button license5;
    Button license7;
    Button license10;
    Button license11;
    Button licenseGV;
    Button opsin;
    Button android_shell;
    Button license_cp2k;
    Button license_fftw;
    Button license_libint;
    Button license_libxc;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.licenses);

        license_label = (TextView) findViewById(R.id.license_label);

        license0 = (Button) findViewById(R.id.license0);
        license0.setOnClickListener(license0Click);
        license5 = (Button) findViewById(R.id.license5);
        license5.setOnClickListener(license5Click);

        license7 = (Button) findViewById(R.id.license7);
        license7.setOnClickListener(license7Click);
        license10 = (Button) findViewById(R.id.license10);
        license10.setOnClickListener(license10Click);
        license11 = (Button) findViewById(R.id.license11);
        license11.setOnClickListener(license11Click);
        licenseGV = (Button) findViewById(R.id.licenseGV);
        licenseGV.setOnClickListener(licenseGVClick);
        opsin = (Button) findViewById(R.id.opsin);
        opsin.setOnClickListener(opsinClick);
        android_shell = (Button) findViewById(R.id.android_shell);
        android_shell.setOnClickListener(android_shellClick);
        license_cp2k = (Button) findViewById(R.id.license_cp2k);
        license_cp2k.setOnClickListener(license_cp2kClick);
        license_fftw = (Button) findViewById(R.id.license_fftw);
        license_fftw.setOnClickListener(license_fftwClick);
        license_libint = (Button) findViewById(R.id.license_libint);
        license_libint.setOnClickListener(license_libintClick);
        license_libxc = (Button) findViewById(R.id.license_libxc);
        license_libxc.setOnClickListener(license_libxcClick);

        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Licenses.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private View.OnClickListener license_cp2kClick; {
        license_cp2kClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertcp2k();
            }
        };
    }

    public void alertcp2k() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-CP2K")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-CP2K.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_fftwClick; {
        license_fftwClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertfftw();
            }
        };
    }

    public void alertfftw() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-FFTW")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-FFTW.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_libintClick; {
        license_libintClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertlibint();
            }
        };
    }

    public void alertlibint() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LIBINT")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LIBINT.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_libxcClick; {
        license_libxcClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertlibxc();
            }
        };
    }

    public void alertlibxc() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LIBXC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LIBXC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener android_shellClick; {
        android_shellClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertAS();
            }
        };
    }

    public void alertAS() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-ANDROID-SHELL")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-ANDROID_SHELL.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener opsinClick; {
        opsinClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertOpsin();
            }
        };
    }

    public void alertOpsin() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-OPSIN")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-OPSIN.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener licenseGVClick; {
        licenseGVClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertGV();
            }
        };
    }

    public void alertGV() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-GRAPHVIEW")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-GRAPHVIEW.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license0Click; {
        license0Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert0();
            }
        };
    }

    public void alert0() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-ACPDFVIEW")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-ACPDFVIEW.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license5Click; {
        license5Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert5();
            }
        };
    }

    public void alert5() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LAPACK")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LAPACK.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license7Click; {
        license7Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert7();
            }
        };
    }

    public void alert7() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-OPENBABEL")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-OPENBABEL.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license10Click; {
        license10Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert10();
            }
        };
    }

    public void alert10() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-X11-BASIC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-X11-BASIC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license11Click; {
        license11Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert11();
            }
        };
    }

    public void alert11() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSING_TERMS_X11-BASIC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSING_TERMS-X11-BASIC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
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
