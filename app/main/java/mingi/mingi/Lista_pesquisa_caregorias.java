package mingi.mingi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import Postagem.Postagem_para_feed;

/**
 * Created by isaias on 07/11/15.
 */
public class Lista_pesquisa_caregorias extends RecyclerView.Adapter<Lista_pesquisa_caregorias.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View Views) {

            super(Views);

            //Idiciona o click na lista
            Views.setOnClickListener(this);

        }

        //Método que faz o click na lista
        @Override
        public void onClick(View v) {

            if(Categorias.is_categoria){

                Intent t = new Intent(Categorias.context, Visualizar_Postagem.class);
                t.putExtra("posicao", getPosition());
                t.putExtra("fazer", "curtir");

                Categorias.context.startActivity(t);

            }
            else {

                Intent t = new Intent(Feeds.context, Visualizar_Postagem.class);
                t.putExtra("posicao", getPosition());
                t.putExtra("fazer", "curtir");

                Feeds.context.startActivity(t);

            }

        }
    }

    private int tipo;

    public Lista_pesquisa_caregorias(int tipo){

        this.tipo = tipo;

    }

    @Override
    public Lista_pesquisa_caregorias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Cria uma nova view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutfeed,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(Lista_pesquisa_caregorias.ViewHolder holder, int position) {

        switch (tipo){

            case 1:

                if(!Postagem_para_feed.POSTAGENS_PESQUISADAS.isEmpty()) {

                    //Adiciona os valores
                    ImageView v = (ImageView) holder.itemView.findViewById(R.id.imagem_postagem);
                    TextView titulo = (TextView) holder.itemView.findViewById(R.id.titulo);
                    TextView curtidas = (TextView) holder.itemView.findViewById(R.id.curtida);
                    TextView data = (TextView) holder.itemView.findViewById(R.id.data);

                    //Adiciona os valores
                    titulo.setText(Postagem_para_feed.POSTAGENS_PESQUISADAS.get(position).getTitulo());
                    curtidas.setText("Curtidas: " + String.valueOf(Postagem_para_feed.POSTAGENS_PESQUISADAS.get(position).getCurtidas()));
                    data.setText(Postagem_para_feed.POSTAGENS_PESQUISADAS.get(position).getData());

                    //Adiciona as imagens
                    Bitmap[] ims = Postagem_para_feed.POSTAGENS_PESQUISADAS.get(position).Transformar_Json_imagem();

                    v.setImageBitmap(ims[0]);

                }

                break;

            case 2:

                if(!Postagem_para_feed.POSTAGENS_CATEGORIA.isEmpty()) {

                    //Adiciona os valores
                    ImageView img = (ImageView) holder.itemView.findViewById(R.id.imagem_postagem);
                    TextView title = (TextView) holder.itemView.findViewById(R.id.titulo);
                    TextView likes = (TextView) holder.itemView.findViewById(R.id.curtida);
                    TextView date = (TextView) holder.itemView.findViewById(R.id.data);

                    //Adiciona os valores
                    title.setText(Postagem_para_feed.POSTAGENS_CATEGORIA.get(position).getTitulo());
                    likes.setText("Curtidas: " + String.valueOf(Postagem_para_feed.POSTAGENS_CATEGORIA.get(position).getCurtidas()));
                    date.setText(Postagem_para_feed.POSTAGENS_CATEGORIA.get(position).getData());

                    //Adiciona as imagens
                    Bitmap[] ims2 = Postagem_para_feed.POSTAGENS_CATEGORIA.get(position).Transformar_Json_imagem();

                    img.setImageBitmap(Nova_postagem.resizeImage(Categorias.context, ims2[0], 350, 280));

                }

                break;

        }


    }

    @Override
    public int getItemCount() {

        if(tipo == 1){

            return Postagem_para_feed.POSTAGENS_PESQUISADAS.size();

        }
        else{

            return Postagem_para_feed.POSTAGENS_CATEGORIA.size();

        }


    }
}
