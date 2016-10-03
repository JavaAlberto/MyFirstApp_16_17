package berufsschulefreising.de.login_version2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{

    private Button login;
    private EditText user;
    private EditText password;
    // Erweiterung
    private SharedPreferences prefs;
    // xml-Dateiname der SharedPreferences-Datei
    String prefName ="sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridLayout g1 = (GridLayout)findViewById(R.id.grid);
        g1.setBackgroundColor(getResources().getColor(R.color.gelb,null));

        user = (EditText)findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_pw);
        login = (Button)findViewById(R.id.button);
        login.setOnClickListener(this);

        // Bildung einer Instanz von prefs
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //    key/value-PAare bilden: username, password
        editor.putString("Mustermann","123456");
        editor.putString("Pfleiderer","bs-fs");
        // Speichern
        editor.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        // id der angeklickten View ermitteln
        switch(v.getId())
        {
            case R.id.button:
                 // hier wird das Passwort verarbeitet
                String pw_input  = password.getText().toString();
                String user_input = user.getText().toString();
              //  Toast.makeText(this,"Ihre Logindaten sind " + user_value + " " + pw_value, Toast.LENGTH_LONG) .show();
                // Logindaten prüfen
                String pw_read = prefs.getString(user_input,null);
                if (pw_read == null) {
                    Toast.makeText(this, "user ist unbekannt ", Toast.LENGTH_LONG).show();
                }
                else
                {
                        if (pw_read.equals(pw_input))
                        {
                            Toast.makeText(this, "Login erfolgreich", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(this, "Passwort falsch", Toast.LENGTH_LONG).show();
                        }

                }
                break;

        }

           // http://androidexample.com/Android_Session_Management_Using_SharedPreferences_-_Android_Example/index.php?view=article_discription&aid=127&aaid=147
    }
}
