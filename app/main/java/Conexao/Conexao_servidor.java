package Conexao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Postagem.Postagem_para_feed;
import Usuario.Usuario;
import mingi.mingi.Feeds;
import mingi.mingi.TelaInicial;
import mingi.mingi.Visualizar_Postagem;

/**
 * Created by isaias on 13/08/15.
 */
public class Conexao_servidor {

    private int QUANTIDADE_POSTAGENS = 10;

    //private static final String URL = "http://10.0.2.2";
    //Esse é usado quando acessa o servidor
    private static final String URL = "http://mingitcc.esy.es";
    private static final String LOGIN = "1";
    private static final String REGISTRAR = "2";
    private static final String INSERIR_POSTAGEM = "3";
    private static final String PEGAR_POSTAGENS = "4";
    private static final String CURTIR = "5";
    private static final String EXCLUIR = "6";

    private HashMap<String,String> parametros;
    private String resposta = "";

    public void Logar(final Usuario usuario,final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                resposta = response;

                //Confere se o usuário foi logado
                if(!resposta.equals("Usuario ou senha incorreto")) {

                    try {

                        //Pega os valores do json
                        JSONObject jsonArray = new JSONObject(resposta);

                        usuario.setNome(jsonArray.getString("nome"));
                        usuario.setSenha(jsonArray.getString("senha"));
                        usuario.setEmail(jsonArray.getString("email"));
                        usuario.setIdade(jsonArray.getInt("idade"));
                        usuario.setId_usuario(jsonArray.getInt("id_usuario"));

                        TelaInicial.USUARIO = usuario;

                        //Pega as postagens
                        Pegar_feed(context);

                        Toast.makeText(context, "Logado com sucesso", Toast.LENGTH_LONG).show();
                        Toast.makeText(context, "Carregando Postagens", Toast.LENGTH_LONG).show();

                        //Thread para conferir se as postagens foram pegas
                        Thread t = new Thread(new Runnable() {

                            @Override
                            public void run() {

                                while(true){

                                    if(Postagem_para_feed.LISTA_COMPLETA){

                                        //Muda de tela
                                        Intent t = new Intent(context, Feeds.class);
                                        context.startActivity(t);

                                        break;

                                    }

                                    else{

                                        try {

                                            Thread.sleep(5);

                                        }
                                        catch (InterruptedException e) {

                                            Log.e("erro","Erro linha 109");

                                        }

                                    }

                                }

                            }

                        });

                        t.start();

                    }
                    catch (Exception e) {

                        Log.e("erro", e.toString());

                    }

                }
                else{

                    Toast.makeText(context,"Não foi possível logar",Toast.LENGTH_LONG).show();

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro",error.toString());

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",LOGIN);
                parametros.put("email",usuario.getEmail());
                parametros.put("senha",usuario.getSenha());

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

    public void Registrar(final String json, final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                resposta = response;

                if (resposta.equals("Usuario inserido com sucesso")){

                    //Pega as postagens
                    Pegar_feed(context);

                    Toast.makeText(context, "Usuario inserido com sucesso", Toast.LENGTH_LONG).show();

                    //Faz o login
                    Logar(TelaInicial.USUARIO,context);

                }
                else {

                    Toast.makeText(context, "Não foi possível inserir o usuário", Toast.LENGTH_LONG).show();

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro",error.toString() + "\nErro na linha 206");

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",REGISTRAR);
                parametros.put("registro",json);

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

    public void Pegar_feed(final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    JSONObject o = new JSONObject(response);
                    Postagem_para_feed.POSTAGENS = new ArrayList<>();

                    int cont = 0;

                    int qtd = 0;

                    qtd = o.getInt("quantidade");

                    for(int a = 0; a < qtd; a++){

                        JSONObject objeto = o.getJSONObject("postagem"+a);

                        Postagem_para_feed.POSTAGENS.add(new Postagem_para_feed());

                        Postagem_para_feed.POSTAGENS.get(cont).setTitulo(objeto.getString("titulo"));
                        Postagem_para_feed.POSTAGENS.get(cont).setGenero(objeto.getInt("genero"));
                        Postagem_para_feed.POSTAGENS.get(cont).setData(objeto.getString("dia"));
                        Postagem_para_feed.POSTAGENS.get(cont).setTexto(objeto.getString("texto"));
                        Postagem_para_feed.POSTAGENS.get(cont).setId_postagem(objeto.getInt("id_postagem"));
                        Postagem_para_feed.POSTAGENS.get(cont).setCurtidas(objeto.getInt("curtidas"));
                        Postagem_para_feed.POSTAGENS.get(cont).setId_usuario(objeto.getInt("id_usuario"));

                        JSONObject array_imagens = objeto.getJSONObject("imagens");

                        String img_str[] = new String[3];

                        for(int b = 0; b < img_str.length; b++){

                            img_str[b] = "";
                            img_str[b] = array_imagens.getString("imagem"+b);

                        }

                        Postagem_para_feed.POSTAGENS.get(a).setImagens_texto(img_str);
                        cont++;

                    }

                    Postagem_para_feed.LISTA_COMPLETA = true;

                }
                catch (JSONException e) {

                    Log.e("erro","Erro na linha 287" + "\n" + e.toString());

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro","Erro na linha 299");

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",PEGAR_POSTAGENS);
                parametros.put("quantidade", String.valueOf(QUANTIDADE_POSTAGENS));

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

    public void Enviar_postagem(final String json, final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                resposta = response;

                Log.e("erro",response);

                if (resposta.equals("Postagem inserida com sucessoPostagem inserida com sucessoPostagem inserida com sucesso")){

                    Toast.makeText(context,"Postagem enviada com sucesso",Toast.LENGTH_LONG).show();

                    Intent t = new Intent(context, Feeds.class);
                    context.startActivity(t);

                }
                else {

                    Toast.makeText(context,"Não foi possível enviar a postagem",Toast.LENGTH_LONG).show();

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro",error.toString());

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",INSERIR_POSTAGEM);
                parametros.put("postagem", json);

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

    public void Curtida(final int id_postagem, final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                resposta = response;

                if(resposta.equals("Postagem curtida")){

                    Toast.makeText(context,"Curtido",Toast.LENGTH_LONG).show();

                    //Limpa as anteriores
                    Postagem_para_feed.LISTA_COMPLETA = false;

                    //Pega as postagens novas
                    Pegar_feed(context);

                    //Thread para conferir se as postagens foram pegas
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            while(true){

                                if(Postagem_para_feed.LISTA_COMPLETA){

                                    //Muda de tela
                                    Intent t = new Intent(Visualizar_Postagem.context, Feeds.class);
                                    Visualizar_Postagem.context.startActivity(t);

                                    break;

                                }

                                else{

                                    try {

                                        Thread.sleep(5);

                                    }
                                    catch (InterruptedException e) {

                                        Log.e("erro","Erro na linha 430");

                                    }

                                }

                            }

                        }

                    });

                    t.start();

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro","Erro na linha 454");

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",CURTIR);
                parametros.put("id_postagem", String.valueOf(id_postagem));

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

    public void Excluir(final int id_postagem, final Context context){

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                resposta = response;

                if(resposta.equals("Postagem excluida")){

                    Toast.makeText(context,"Excluído",Toast.LENGTH_LONG).show();

                    Postagem_para_feed.LISTA_COMPLETA = false;

                    //Pega as postagens novas
                    Pegar_feed(context);

                    //Thread para conferir se as postagens foram pegas
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            while(true){

                                if(Postagem_para_feed.LISTA_COMPLETA){

                                    //Muda de tela
                                    Intent t = new Intent(Visualizar_Postagem.context, Feeds.class);
                                    Visualizar_Postagem.context.startActivity(t);

                                    break;

                                }

                                else{

                                    try {

                                        Thread.sleep(5);

                                    }
                                    catch (InterruptedException e) {

                                        e.printStackTrace();

                                    }

                                }

                            }

                        }

                    });

                    t.start();

                }

            }

        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("erro",error.toString());

                    }

                }

        ){

            public Map<String,String> getParams() throws AuthFailureError {

                parametros = new HashMap<>();
                parametros.put("acao",EXCLUIR);
                parametros.put("id_postagem", String.valueOf(id_postagem));

                return parametros;

            }

        };

        mRequestQueue.add(stringRequest);

    }

}
