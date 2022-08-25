package Usuario;

import android.content.Context;

import Conexao.Conexao_servidor;

/**
 * Created by isaias on 13/08/15.
 */
public class Login {

    public static boolean status_login = false;

    public boolean get_Status_login() {

        return Login.status_login;

    }

    public void setStatus_login(boolean status_login) {

        Login.status_login = status_login;

    }

    public void Logar(Usuario usuario,Context context){

        Conexao_servidor conexao = new Conexao_servidor();
        conexao.Logar(usuario, context);

    }

}