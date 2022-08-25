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
 * Created by isaias on 08/07/15.
 */
public class Lista_feeds extends RecyclerView.Adapter<Lista_feeds.ViewHolder>{

    //Referencias para o tipo de item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View Views) {

            super(Views);

            //Idiciona o click na lista
            Views.setOnClickListener(this);

        }

        //Método que faz o click na lista
        @Override
        public void onClick(View v) {

            Intent t = new Intent(Feeds.context,Visualizar_Postagem.class);
            t.putExtra("posicao", getPosition());
            t.putExtra("fazer", "curtir");

            t.setFlags(t.FLAG_ACTIVITY_CLEAR_TOP);

            Feeds.context.startActivity(t);


        }
    }

    //Cria novas views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Cria uma nova view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutfeed,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    //Este método altera os valores dos elementos de acordo com a posição
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Adiciona os valores
        ImageView v = (ImageView)holder.itemView.findViewById(R.id.imagem_postagem);
        TextView titulo = (TextView) holder.itemView.findViewById(R.id.titulo);
        TextView curtidas = (TextView) holder.itemView.findViewById(R.id.curtida);
        TextView data = (TextView) holder.itemView.findViewById(R.id.data);

        //Adiciona os valores
        titulo.setText(Postagem_para_feed.POSTAGENS.get(position).getTitulo());
        curtidas.setText( "Curtidas: " + String.valueOf(Postagem_para_feed.POSTAGENS.get(position).getCurtidas()));
        data.setText(Postagem_para_feed.POSTAGENS.get(position).getData());

        //Adiciona as imagens
        Bitmap[] ims = Postagem_para_feed.POSTAGENS.get(position).Transformar_Json_imagem();

        v.setImageBitmap(Nova_postagem.resizeImage(Feeds.context,ims[0],350, 280));

    }

    //Este método retorna a quantidade de itens
    @Override
    public int getItemCount() {

        return Postagem_para_feed.POSTAGENS.size();

    }

}

