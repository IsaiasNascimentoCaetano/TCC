package mingi.mingi;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import Conexao.Conexao_servidor;
import Postagem.Postagem_para_feed;
import Postagem.Pagina_usuario;

public class Visualizar_Postagem extends ActionBarActivity {

    private int posicao;
    public static Context context;
    private ImageButton curtir = null;
    private boolean excluir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar__postagem);

        Visualizar_Postagem.context = this;
        Bundle d = getIntent().getExtras();

        posicao = d.getInt("posicao");

        curtir = (ImageButton)findViewById(R.id.curtir);

        //Confere se é para deletar ou não
        if(d.getString("fazer").equals("excluir")){

            excluir = true;

        }
        else if(d.getString("fazer").equals("curtir")){

            excluir = false;

        }

        adicionar_valores();

        //Curti ou deletar a postagem
        curtir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Conexao_servidor con = new Conexao_servidor();

                //Curte a postagem
                if(!excluir) {

                    con.Curtida(Postagem_para_feed.POSTAGENS.get(posicao).getId_postagem(), Visualizar_Postagem.context);

                }
                //Deleta a postagem
                else{
                    con.Excluir(Pagina_usuario.POSTAGENS.get(posicao).getId_postagem(),Visualizar_Postagem.context);

                }

            }

        });
    }

    private void adicionar_valores(){

        TextView titulo = (TextView)findViewById(R.id.titulo);
        TextView texto = (TextView)findViewById(R.id.texto);

        ImageView []imagens = new ImageView[3];

        imagens[0] = (ImageView)findViewById(R.id.imagem_1);
        imagens[1] = (ImageView)findViewById(R.id.imagem_2);
        imagens[2] = (ImageView)findViewById(R.id.imagem_3);

        if(!excluir) {

            //Se não for da tela de pesquisa
            if(!Categorias.is_categoria && !Tela_pesquisa.is_pesquisa) {

                //Adiciona os valores
                titulo.setText(Postagem_para_feed.POSTAGENS.get(posicao).getTitulo());
                texto.setText(Postagem_para_feed.POSTAGENS.get(posicao).getTexto());

                //Adiciona as imagens
                Bitmap[] img_temp = Postagem_para_feed.POSTAGENS.get(posicao).Transformar_Json_imagem();

                for (int a = 0; a < img_temp.length; a++) {

                    if (img_temp[a] != null) {

                        imagens[a].setImageBitmap(Nova_postagem.resizeImage(this, img_temp[a], 350, 280));

                    }

                }

            }
            else if(Categorias.is_categoria){

                //Adiciona os valores
                titulo.setText(Postagem_para_feed.POSTAGENS_CATEGORIA.get(posicao).getTitulo());
                texto.setText(Postagem_para_feed.POSTAGENS_CATEGORIA.get(posicao).getTexto());

                //Adiciona as imagens
                Bitmap[] img_temp = Postagem_para_feed.POSTAGENS_CATEGORIA.get(posicao).Transformar_Json_imagem();

                for (int a = 0; a < img_temp.length; a++) {

                    if (img_temp[a] != null) {

                        imagens[a].setImageBitmap(Nova_postagem.resizeImage(this, img_temp[a], 350, 280));

                    }

                }

                Categorias.is_categoria = false;

            }
            else if(Tela_pesquisa.is_pesquisa){

                //Adiciona os valores
                titulo.setText(Postagem_para_feed.POSTAGENS_PESQUISADAS.get(posicao).getTitulo());
                texto.setText(Postagem_para_feed.POSTAGENS_PESQUISADAS.get(posicao).getTexto());

                //Adiciona as imagens
                Bitmap[] img_temp = Postagem_para_feed.POSTAGENS_PESQUISADAS.get(posicao).Transformar_Json_imagem();

                for (int a = 0; a < img_temp.length; a++) {

                    if (img_temp[a] != null) {

                        imagens[a].setImageBitmap(Nova_postagem.resizeImage(this, img_temp[a], 350, 280));

                    }

                }

                Tela_pesquisa.is_pesquisa = false;

            }

        }
        else{

            //Adiciona os valores
            titulo.setText(Pagina_usuario.POSTAGENS.get(posicao).getTitulo());
            texto.setText(Pagina_usuario.POSTAGENS.get(posicao).getTexto());

            //Adiciona as imagens
            Bitmap[] img_temp = Pagina_usuario.POSTAGENS.get(posicao).Transformar_Json_imagem();

            for (int a = 0; a < img_temp.length; a++) {

                if (img_temp[a] != null) {

                    imagens[a].setImageBitmap(Nova_postagem.resizeImage(this,img_temp[a],350,280));

                }

            }

        }

    }

    protected void onPause(){

        super.onPause();

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar__postagem, menu);
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
