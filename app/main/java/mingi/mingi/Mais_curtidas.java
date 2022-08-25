package mingi.mingi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import Postagem.Postagem_para_feed;

public class Mais_curtidas extends AppCompatActivity {

    public static Context context;
    private android.support.v7.widget.Toolbar tollbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_curtidas2);

        ordenar_postagens();

        tollbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        tollbar.setTitle("Mais curtidas");
        setSupportActionBar(tollbar);

        Mais_curtidas.context = this;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //Specify and adapter
        Lista_feeds adapter = new Lista_feeds();
        recyclerView.setAdapter(adapter);


        //Drawer
        PrimaryDrawerItem conta = new PrimaryDrawerItem().withName("Minha conta").withIdentifier(1);
        PrimaryDrawerItem nova_postagem = new PrimaryDrawerItem().withName("Criar postagem").withIdentifier(2);
        PrimaryDrawerItem procurar = new PrimaryDrawerItem().withName("Procurar postagem").withIdentifier(3);

        Drawer result = new DrawerBuilder().withActivity(this).withToolbar(tollbar).addDrawerItems(

                conta,
                new DividerDrawerItem(),
                nova_postagem, procurar).

                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {

                        Intent mudar = null;

                        switch (i){

                            case 0:

                                mudar = new Intent(Mais_curtidas.this,Pagina_Usuario.class);
                                startActivity(mudar);

                                break;

                            case 2:

                                mudar = new Intent(Mais_curtidas.this, Nova_postagem.class);
                                startActivity(mudar);

                                break;

                            case 3:

                                break;


                        }

                        return false;

                    }
                })

                .build();

    }

    private void ordenar_postagens(){

        Postagem_para_feed.POSTAGENS_ORDENADAS = Postagem_para_feed.POSTAGENS;

        for(int a = 0; a < Postagem_para_feed.POSTAGENS_ORDENADAS.size(); a++){

            for(int b = 0; b < (Postagem_para_feed.POSTAGENS_ORDENADAS.size() - 1); b++){

                if(Postagem_para_feed.POSTAGENS_ORDENADAS.get(b).getCurtidas() < Postagem_para_feed.POSTAGENS_ORDENADAS.get(b+1).getCurtidas()){

                    Postagem_para_feed aux = Postagem_para_feed.POSTAGENS_ORDENADAS.get(b);
                    Postagem_para_feed.POSTAGENS_ORDENADAS.set(b, Postagem_para_feed.POSTAGENS_ORDENADAS.get(b + 1));
                    Postagem_para_feed.POSTAGENS_ORDENADAS.set((b + 1), aux);

                }

            }

        }

    }

    protected void onPause(){

        super.onPause();

        finish();

    }
}
