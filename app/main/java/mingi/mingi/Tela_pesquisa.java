package mingi.mingi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import Postagem.Postagem_para_feed;

public class Tela_pesquisa extends AppCompatActivity {

    public static Context context;
    public static boolean is_pesquisa = false;

    private android.support.v7.widget.Toolbar tollbar;
    private Lista_pesquisa_caregorias adapter;
    private ImageButton botao;
    private RecyclerView recyclerView;
    private EditText pesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa);

        Postagem_para_feed.POSTAGENS_PESQUISADAS = new ArrayList<>();

        tollbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(tollbar);

        Tela_pesquisa.context = this;

        pesquisar = (EditText)findViewById(R.id.pesquisa);

        botao = (ImageButton)findViewById(R.id.button);

        pesquisar();

        //Cria o recycle
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        //Cria o recycle view
        adapter = new Lista_pesquisa_caregorias(1);
        recyclerView.setAdapter(adapter);

    }

    public void pesquisar(){

        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!Postagem_para_feed.POSTAGENS_PESQUISADAS.isEmpty()) {

                    Postagem_para_feed.POSTAGENS_PESQUISADAS.clear();

                }

                //Pesquisa os títulos, e os títulos que forem iguais passa para o array
                for(int a = 0; a < Postagem_para_feed.POSTAGENS.size(); a++){

                    if(Postagem_para_feed.POSTAGENS.get(a).getTitulo().equals(pesquisar.getText().toString())){

                        Postagem_para_feed.POSTAGENS_PESQUISADAS.add(Postagem_para_feed.POSTAGENS.get(a));

                    }

                }

                if(Postagem_para_feed.POSTAGENS_PESQUISADAS.size() != 0) {

                    //Cria o recycle view
                    adapter = new Lista_pesquisa_caregorias(1);
                    recyclerView.setAdapter(adapter);

                    Tela_pesquisa.is_pesquisa = true;

                }
                else{

                    Toast.makeText(context,"Nada encontrado",Toast.LENGTH_LONG).show();

                }

            }

        });

    }

}
