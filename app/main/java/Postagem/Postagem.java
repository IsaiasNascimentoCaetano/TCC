package Postagem;

import android.graphics.Bitmap;

/**
 * Created by isaias on 13/08/15.
 */
public abstract class Postagem {

    protected String titulo;
    protected String texto;
    protected String data;
    protected int genero;
    protected int id_usuario;
    protected Bitmap[] imagens;
    protected String[] imagens_texto;

    //Constantes de genero
    public static final int PLASTICO = 1;
    public static final int METAL = 2;
    public static final int PAPEL = 3;
    public static final int VIDRO = 4;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {

        //Concerta a data
        char[] troca = data.toCharArray();

        String temp = "";

        temp += String.valueOf(troca[8]) + String.valueOf(troca[9]) + "/";
        temp += String.valueOf(troca[5]) + String.valueOf(troca[6]) + "/";

        for(int a = 0; a < 4; a++){

            temp += String.valueOf(troca[a]);

        }

        this.data = temp;

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String[] getImagens_texto(){

        return this.imagens_texto;

    }

    public void setImagens_texto(String[] imagens_texto){

        this.imagens_texto = imagens_texto;

    }

    public Bitmap[] getImagens() {
        return imagens;
    }

}
