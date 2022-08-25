package mingi.mingi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Postagem.Postagem_para_enviar;

public class Nova_postagem extends ActionBarActivity {

    private Context context;
    private Postagem_para_enviar postagem = null;
    private boolean[] foto_atual = new boolean[3];
    private boolean camera = false;
    private int soma_fotos_tiradas = 0;
    private Bitmap imagens[] = new Bitmap[3];
    private Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_postagem);

        //Pega o context
        context = this;

        //Cria o objeto para postagem
        postagem = new Postagem_para_enviar();

        //Pega os elementos
        Button enviar = (Button)findViewById(R.id.enviar);

        //Cria o spinner
        spinner = (Spinner)findViewById(R.id.categoria);

        List<String> lista = new ArrayList<>();

        lista.add("Plastico");
        lista.add("Metal");
        lista.add("Papel");
        lista.add("Vidro");

        ArrayAdapter<String> adptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,lista);
        adptador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adptador);

        Button pegar_imagens[] = new Button[6];

        //Imagens com a camera
        pegar_imagens[0] = (Button)findViewById(R.id.pegar_imagem1);
        pegar_imagens[1] = (Button)findViewById(R.id.pegar_imagem2);
        pegar_imagens[2] = (Button)findViewById(R.id.pegar_imagem3);

        //Imagens com da galeria
        pegar_imagens[3] = (Button)findViewById(R.id.pegar_imagem1_galeria);
        pegar_imagens[4] = (Button)findViewById(R.id.pegar_imagem2_galeria);
        pegar_imagens[5] = (Button)findViewById(R.id.pegar_imagem3_galeria);

        //Cria os eventos para cada botão
        pegar_imagens[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = true;
                foto_atual[0] = true;
                tirarFoto();

            }
        });

        pegar_imagens[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = true;
                foto_atual[1] = true;
                tirarFoto();

            }

        });

        pegar_imagens[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = true;
                foto_atual[2] = true;
                tirarFoto();

            }

        });

        pegar_imagens[3].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = false;
                foto_atual[0] = true;
                tirarFoto();

            }
        });

        pegar_imagens[4].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = false;
                foto_atual[1] = true;
                tirarFoto();

            }
        });

        pegar_imagens[5].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                camera = false;
                foto_atual[2] = true;
                tirarFoto();

            }
        });

        //Evento do botão enviar
        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final EditText titulo = (EditText)findViewById(R.id.titulo);
                final EditText texto = (EditText)findViewById(R.id.texto);

                if(soma_fotos_tiradas > 0) {

                    postagem.setTitulo(titulo.getText().toString());
                    postagem.setTexto(texto.getText().toString());
                    postagem.setGenero((spinner.getSelectedItemPosition() + 1));
                    postagem.setId_usuario(TelaInicial.USUARIO.getId_usuario());
                    postagem.setJson_imagem(passar_Para_64());

                    Toast.makeText(context,"Preparando para enviar",Toast.LENGTH_SHORT).show();

                    postagem.Enviar(context);

                }
                else{

                    Toast.makeText(getApplicationContext(),"Coloque alguma imagem",Toast.LENGTH_LONG).show();

                }

            }

        });


    }

    public void tirarFoto(){

        Intent galeriaIntent;

        if(camera){

            galeriaIntent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(galeriaIntent, 0);

        }

        else{

            galeriaIntent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galeriaIntent.setType("image/*");
            galeriaIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(galeriaIntent, 1);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Quando pega da galeria
        if (requestCode == 1 && !camera) {

            if(foto_atual[0]) {

                Uri fullPhotoUri = data.getData();

                InputStream is = null;

                try {

                    is = getContentResolver().openInputStream(fullPhotoUri);
                    imagens[0] = BitmapFactory.decodeStream(is);

                    is.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView imv = (ImageView) findViewById(R.id.imagem_1);
                imv.setImageBitmap(imagens[0]);
                soma_fotos_tiradas++;

            }

            else if(foto_atual[1]){

                Uri fullPhotoUri = data.getData();

                InputStream is = null;

                try {

                    is = getContentResolver().openInputStream(fullPhotoUri);
                    imagens[1] = BitmapFactory.decodeStream(is);

                    is.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView imv = (ImageView) findViewById(R.id.imagem_2);
                imv.setImageBitmap(imagens[1]);
                soma_fotos_tiradas++;

            }

            else if(foto_atual[2]){

                Uri fullPhotoUri = data.getData();

                InputStream is = null;

                try {

                    is = getContentResolver().openInputStream(fullPhotoUri);
                    imagens[2] = BitmapFactory.decodeStream(is);

                    is.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView imv = (ImageView) findViewById(R.id.imagem_3);
                imv.setImageBitmap(imagens[2]);
                soma_fotos_tiradas++;

            }

        }


        //Quando pega a imagem na hora
        if(data != null && camera){

            Bundle b = data.getExtras();

            if(b != null){

                if(foto_atual[0]){

                    imagens[0] = (Bitmap)b.get("data");

                    imagens[0] = Nova_postagem.resizeImage(this,imagens[0],300,200);

                    ImageView imv = (ImageView)findViewById(R.id.imagem_1);
                    imv.setImageBitmap(imagens[0]);

                    soma_fotos_tiradas++;

                }

                else if(foto_atual[1]){

                    imagens[1] = (Bitmap)b.get("data");

                    imagens[1] = Nova_postagem.resizeImage(this,imagens[1],300,200);

                    ImageView imv = (ImageView)findViewById(R.id.imagem_2);
                    imv.setImageBitmap(imagens[1]);

                    soma_fotos_tiradas++;

                }

                else if(foto_atual[2]){

                    imagens[2] = (Bitmap)b.get("data");

                    imagens[2] = Nova_postagem.resizeImage(this,imagens[2],300,200);

                    ImageView imv = (ImageView)findViewById(R.id.imagem_3);
                    imv.setImageBitmap(imagens[2]);

                    soma_fotos_tiradas++;

                }


            }

        }

        //Deixa todas os booleans false;
        for(int a = 0; a < foto_atual.length; a++){

            foto_atual[a] = false;

        }

    }

    private String[] passar_Para_64(){

        String[] fotos_em_64 = new String[imagens.length];

        for(int a = 0; a < imagens.length; a++){

            ByteArrayOutputStream array_de_bytes = new ByteArrayOutputStream();
            imagens[a].compress(Bitmap.CompressFormat.PNG,100,array_de_bytes);
            byte[] imagem_encode = array_de_bytes.toByteArray();
            fotos_em_64[a] = Base64.encodeToString(imagem_encode, Base64.DEFAULT);

        }

        return fotos_em_64;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nova_postagem, menu);
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

    public static Bitmap resizeImage(Context context, Bitmap bmpOriginal,
                                      float newWidth, float newHeight) {
        Bitmap novoBmp = null;

        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();

        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newHeight * densityFactor;

        //Calcula escala em percentagem do tamanho original para o novo tamanho
        float scalaW = novoW / w;
        float scalaH = novoH / h;

        // Criando uma matrix para manipulação da imagem BitMap
        Matrix matrix = new Matrix();

        // Definindo a proporção da escala para o matrix
        matrix.postScale(scalaW, scalaH);

        //criando o novo BitMap com o novo tamanho
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);

        return novoBmp;

    }


}
