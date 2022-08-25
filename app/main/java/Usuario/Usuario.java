package Usuario;

/**
 * Created by isaias on 13/08/15.
 */
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private int idade;
    private int id_usuario;


    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }

    public String getSenha() {

        return senha;

    }

    public void setSenha(String senha) {

        this.senha = senha;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public int getIdade() {

        return idade;

    }

    public void setIdade(int idade) {

        this.idade = idade;

    }

    public int getId_usuario() {

        return id_usuario;

    }

    public void setId_usuario(int id_usuario) {

        this.id_usuario = id_usuario;

    }

}
