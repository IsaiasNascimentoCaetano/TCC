package Postagem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

/**
 * Created by isaias on 13/08/15.
 */
public class Postagem_para_feed extends Postagem {

    public static boolean LISTA_COMPLETA = false;
    public static ArrayList<Postagem_para_feed> POSTAGENS = null;
    public static ArrayList<Postagem_para_feed> POSTAGENS_ORDENADAS = null;
    public static ArrayList<Postagem_para_feed> POSTAGENS_PESQUISADAS = null;
    public static ArrayList<Postagem_para_feed> POSTAGENS_CATEGORIA = null;

    private int id_postagem;
    private int curtidas;

    public int getId_postagem() {
        return id_postagem;
    }

    public void setId_postagem(int id_postagem) {
        this.id_postagem = id_postagem;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public Bitmap[] Transformar_Json_imagem(){

        Bitmap[] aux = new Bitmap[this.imagens_texto.length];

        for(int a = 0; a < this.imagens_texto.length; a++) {

            if (!this.imagens_texto[a].equals("")) {

                byte[] decodedByte = Base64.decode(this.imagens_texto[a], 0);
                aux[a] = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);

            }

        }

        return aux;

    }

}
