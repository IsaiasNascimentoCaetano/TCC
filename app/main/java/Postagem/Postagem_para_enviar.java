package Postagem;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import Conexao.Conexao_servidor;

/**
 * Created by isaias on 13/08/15.
 */
public class Postagem_para_enviar extends Postagem{

    private String[] json_imagem;
    private boolean postado = false;

    public Postagem_para_enviar(){

        //Id padrão do anonimo
        id_usuario = 1;

    }

    public void setJson_imagem(String[] json_imagem) {

        this.json_imagem = json_imagem;

    }

    private JSONArray Criar_json_imagem(){

        JSONArray array = new JSONArray();

        for(int a = 0; a < json_imagem.length; a++){

            array.put(json_imagem[a]);

        }

        return array;

    }

    private String Criar_json(){

        JSONObject json = null;

        try {

            json = new JSONObject();

            json.put("titulo", this.getTitulo());
            json.put("genero", this.getGenero());
            json.put("texto",this.getTexto());
            json.put("id_usuario",this.getId_usuario());
            json.put("imagens",Criar_json_imagem());

        }
        catch (Exception e){

            Log.e("erro","Erro ao criar json \n" + e.toString());

        }

        return json.toString();

    }

    public void Enviar(Context context){

        Conexao_servidor conexao_servidor = new Conexao_servidor();

        conexao_servidor.Enviar_postagem(Criar_json(),context);

    }

}
