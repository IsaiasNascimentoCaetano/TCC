package Usuario;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import Conexao.Conexao_servidor;

/**
 * Created by isaias on 13/08/15.
 */
public class Registro {

    public void Resgistrar(Usuario usuario,Context context){

        Conexao_servidor conexao = new Conexao_servidor();
        conexao.Registrar(Criar_Json(usuario),context);

    }

    private String Criar_Json(Usuario usuario){

        JSONObject objeto = null;

        try {

            objeto = new JSONObject();

            objeto.put("nome", usuario.getNome());
            objeto.put("idade",usuario.getIdade());
            objeto.put("email",usuario.getEmail());
            objeto.put("senha",usuario.getSenha());

        }
        catch (Exception e){

            Log.e("erro",e.toString());

        }

        return objeto.toString();

    }

}
