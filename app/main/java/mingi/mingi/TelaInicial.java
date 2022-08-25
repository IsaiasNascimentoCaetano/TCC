package mingi.mingi;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Usuario.*;

public class TelaInicial extends ActionBarActivity {

    public static Context context;
    public static Usuario USUARIO;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        TelaInicial.context = this;
        c = this;

        final EditText email = (EditText)findViewById(R.id.email);
        final EditText senha = (EditText)findViewById(R.id.senha);
        Button logar = (Button)findViewById(R.id.botao_logar);

        //Evento que faz login
        logar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Usuario usuario = new Usuario();

                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                Login login = new Login();
                login.Logar(usuario,c);

            }

        });


        //Registro de usuário
        final EditText nome = (EditText) findViewById(R.id.nome_registrar);
        final EditText idade = (EditText) findViewById(R.id.idade_registrar);
        final EditText emailR = (EditText) findViewById(R.id.email_registrar);
        final EditText senhaR = (EditText) findViewById(R.id.senha_registrar);

        Button botao_registrar = (Button) findViewById(R.id.botao_registrar);

        botao_registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Usuario usuario = new Usuario();

                usuario.setNome(nome.getText().toString());
                usuario.setIdade(Integer.parseInt(idade.getText().toString()));
                usuario.setEmail(emailR.getText().toString());
                usuario.setSenha((senhaR.getText().toString()));

                TelaInicial.USUARIO = usuario;

                Registro registro = new Registro();
                registro.Resgistrar(usuario,c);

            }

        });

    }

    protected void onPause(){

        super.onPause();

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_inicial, menu);
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
}
