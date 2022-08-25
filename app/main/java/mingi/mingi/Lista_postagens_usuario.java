package mingi.mingi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import Postagem.Pagina_usuario;

/**
 * Created by isaias on 27/09/15.
 */
public class Lista_postagens_usuario extends RecyclerView.Adapter<Lista_postagens_usuario.ViewHolder> {

    //Referencias para o tipo de item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View Views) {

            super(Views);
            Views.setOnClickListener(this);

        }

        //Método que faz o click na lista
        @Override
        public void onClick(View v) {

            Intent t = new Intent(Pagina_Usuario.context,Visualizar_Postagem.class);
            t.putExtra("posicao", getPosition());
            t.putExtra("fazer","excluir");

            Pagina_Usuario.context.startActivity(t);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Cria uma nova view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutfeed,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Confere se há postagens
        if(Pagina_usuario.POSTAGENS.size() > 0) {

            //Adiciona os valores
            ImageView v = (ImageView) holder.itemView.findViewById(R.id.imagem_postagem);
            TextView titulo = (TextView) holder.itemView.findViewById(R.id.titulo);
            TextView curtidas = (TextView) holder.itemView.findViewById(R.id.curtida);
            TextView data = (TextView) holder.itemView.findViewById(R.id.data);

            //Adiciona os valores
            titulo.setText(Pagina_usuario.POSTAGENS.get(position).getTitulo());
            curtidas.setText("Curtidas: " + String.valueOf(Pagina_usuario.POSTAGENS.get(position).getCurtidas()));
            data.setText(Pagina_usuario.POSTAGENS.get(position).getData());

            //Adiciona as imagens
            Bitmap[] ims = Pagina_usuario.POSTAGENS.get(position).Transformar_Json_imagem();
            v.setImageBitmap(Nova_postagem.resizeImage(Feeds.context, ims[0], 350, 280));

        }

    }

    @Override
    public int getItemCount() {

        return Pagina_usuario.POSTAGENS.size();

    }
}
