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

public class Feeds extends ActionBarActivity {

    public static Context context;
    private android.support.v7.widget.Toolbar tollbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        tollbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(tollbar);

        Feeds.context = this;

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
        PrimaryDrawerItem mais_curtidos = new PrimaryDrawerItem().withName("Mais curtidos").withIdentifier(4);
        SecondaryDrawerItem categoria_platico = new SecondaryDrawerItem().withName("Plástico").withIdentifier(5);
        SecondaryDrawerItem categoria_metal = new SecondaryDrawerItem().withName("Metal").withIdentifier(6);
        SecondaryDrawerItem categoria_papel = new SecondaryDrawerItem().withName("Papel").withIdentifier(7);
        SecondaryDrawerItem categoria_vidro = new SecondaryDrawerItem().withName("Vidro").withIdentifier(8);

        Drawer result = new DrawerBuilder().withActivity(this).withToolbar(tollbar).addDrawerItems(

                conta,
                new DividerDrawerItem(),
                nova_postagem, procurar,mais_curtidos,
                new DividerDrawerItem(),
                categoria_platico.withIcon(R.drawable.plastico),
                categoria_metal.withIcon(R.drawable.metal),
                categoria_papel.withIcon(R.drawable.papel_icone),
                categoria_vidro.withIcon(R.drawable.vidro)).

                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {

                        Intent mudar = null;

                        switch (i){

                            case 0:

                                mudar = new Intent(Feeds.this,Pagina_Usuario.class);
                                startActivity(mudar);

                                break;

                            case 2:

                                mudar = new Intent(Feeds.this, Nova_postagem.class);
                                startActivity(mudar);

                                break;

                            case 3:

                                mudar = new Intent(Feeds.this, Tela_pesquisa.class);
                                startActivity(mudar);

                                break;

                            case 4:

                                mudar = new Intent(Feeds.this, Mais_curtidas.class);
                                startActivity(mudar);

                                break;

                            case 6:

                                mudar = new Intent(Feeds.this,Categorias.class);
                                mudar.putExtra("categoria",1);

                                startActivity(mudar);

                                break;

                            case 7:

                                mudar = new Intent(Feeds.this,Categorias.class);
                                mudar.putExtra("categoria",2);
                                startActivity(mudar);

                                break;

                            case 8:

                                mudar = new Intent(Feeds.this,Categorias.class);
                                mudar.putExtra("categoria",3);
                                startActivity(mudar);

                                break;

                            case 9:

                                mudar = new Intent(Feeds.this,Categorias.class);
                                mudar.putExtra("categoria",4);
                                startActivity(mudar);

                                break;

                        }

                        return false;

                    }
                })

        .build();

    }

    @Override
    protected void onPause(){

        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feeds, menu);
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
