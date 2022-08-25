package mingi.mingi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import Postagem.Pagina_usuario;
import Postagem.Postagem_para_feed;

public class Pagina_Usuario extends ActionBarActivity {

    public static Context context;
    private android.support.v7.widget.Toolbar tollbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina__usuario);

        Pagina_Usuario.context = this;
        this.Criar_lista_usuario();

        //Toolbar
        tollbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        tollbar.setTitle(TelaInicial.USUARIO.getNome());
        setSupportActionBar(tollbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //Especifica e adapta
        Lista_postagens_usuario lista = new Lista_postagens_usuario();
        recyclerView.setAdapter(lista);


        PrimaryDrawerItem conta = new PrimaryDrawerItem().withName("Minha conta").withIdentifier(1);
        PrimaryDrawerItem nova_postagem = new PrimaryDrawerItem().withName("Criar postagem").withIdentifier(2);
        PrimaryDrawerItem procurar = new PrimaryDrawerItem().withName("Procurar postagem").withIdentifier(3);
        PrimaryDrawerItem mais_curtidos = new PrimaryDrawerItem().withName("Mais curtidos").withIdentifier(4);
        SecondaryDrawerItem categoria_platico = new SecondaryDrawerItem().withName("Plástico").withIdentifier(5);
        SecondaryDrawerItem categoria_metal = new SecondaryDrawerItem().withName("Metal").withIdentifier(6);
        SecondaryDrawerItem categoria_papel = new SecondaryDrawerItem().withName("Papel").withIdentifier(7);
        SecondaryDrawerItem categoria_vidro = new SecondaryDrawerItem().withName("Vidro").withIdentifier(8);

        Drawer result = new DrawerBuilder().withActivity(this).withToolbar(tollbar).addDrawerItems(

                conta,
                new DividerDrawerItem(),
                nova_postagem, procurar, mais_curtidos,
                new DividerDrawerItem(),
                categoria_platico, categoria_metal, categoria_papel, categoria_vidro).

                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {

                        Intent mudar = null;

                        switch (i) {

                            case 0:

                                mudar = new Intent(Pagina_Usuario.this, Feeds.class);
                                startActivity(mudar);

                                break;

                            case 2:

                                mudar = new Intent(Pagina_Usuario.this, Nova_postagem.class);
                                startActivity(mudar);

                                break;

                            case 3:

                                mudar = new Intent(Pagina_Usuario
                                        .this, Tela_pesquisa.class);
                                startActivity(mudar);

                                break;

                            case 4:

                                mudar = new Intent(Pagina_Usuario.this, Mais_curtidas.class);
                                startActivity(mudar);

                                break;

                        }

                        return false;

                    }
                })

                .build();


    }

    //Cria a lista das postagens que o usuário criou
    private void Criar_lista_usuario(){

        Pagina_usuario.POSTAGENS = new ArrayList<>();

        for(int a = 0; a < Postagem_para_feed.POSTAGENS.size(); a++){

            //Confere se o id é o mesmo
            if(Postagem_para_feed.POSTAGENS.get(a).getId_usuario() == TelaInicial.USUARIO.getId_usuario()){

                Pagina_usuario.POSTAGENS.add(Postagem_para_feed.POSTAGENS.get(a));

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
        getMenuInflater().inflate(R.menu.menu_pagina__usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks her. The action bar will
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
