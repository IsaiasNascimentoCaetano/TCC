package mingi.mingi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Postagem.Postagem_para_feed;

public class Categorias extends AppCompatActivity {

    public static Context context;
    public static boolean is_categoria = false;
    private android.support.v7.widget.Toolbar tollbar;

    private int categoria = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Bundle d = getIntent().getExtras();
        categoria = d.getInt("categoria");

        criar_lista();

        tollbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        mudar_titulo();
        setSupportActionBar(tollbar);

        Categorias.context = this;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //Specify and adapter
        Lista_pesquisa_caregorias adapter = new Lista_pesquisa_caregorias(2);
        recyclerView.setAdapter(adapter);


    }

    //Cria a lista da categoria específica
    private void criar_lista(){

        Postagem_para_feed.POSTAGENS_CATEGORIA = new ArrayList<>();

        for(int a = 0; a < Postagem_para_feed.POSTAGENS.size(); a++){

            if(Postagem_para_feed.POSTAGENS.get(a).getGenero() == categoria){

                Postagem_para_feed.POSTAGENS_CATEGORIA.add(Postagem_para_feed.POSTAGENS.get(a));

            }

        }

        Categorias.is_categoria = true;

    }

    private void mudar_titulo(){

        tollbar.setTitleTextColor(Color.WHITE);

        //Muda o titulo e a cor de fundo
        switch (categoria){

            case 1:

                tollbar.setTitle("Plastico");
                tollbar.setBackgroundColor(Color.rgb(109,25,25));
                break;

            case 2:

                tollbar.setTitle("Metal");
                tollbar.setBackgroundColor(Color.rgb(208,237,19));
                break;

            case 3:

                tollbar.setTitle("Papel");
                tollbar.setBackgroundColor(Color.rgb(12,34,143));
                break;

            case 4:

                tollbar.setTitle("Vidro");
                tollbar.setBackgroundColor(Color.rgb(0,128,0));
                break;


        }

    }

    protected void onPause(){

        super.onPause();

        finish();

    }

}
