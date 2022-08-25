<?php

include_once "Usuario.php";
include_once ("Postagem.php");

define("LOCAL_SERVIDOR","localhost");
define("USUARIO_BANCO","root");
define("SENHA_BANCO","");
define("NOME_BANCO","u287541009_banco");

class Conexao_Banco {

    private $con;
            
    function __construct() {        
                        
        //Se conecta ao servidor
        $this->con = mysqli_connect(LOCAL_SERVIDOR,USUARIO_BANCO,SENHA_BANCO,NOME_BANCO);
                      
        //Se deu erro posta a mensagem
        if(mysqli_connect_errno()){
               
            echo "Não conseguiu se conectar ao banco <br>" . mysqli_connect_error();                
               
        }       
                     
    }
    
    //Destrutor que desconecta do banco
    function __destruct(){
        
        $this->con->close();
        
    }
    
    public function Logar($usuario){
                
        $retorno = false;
        
        $email = $usuario->getEmail();
        $senha = $usuario->getSenha();
                        
        $email = $this->con->real_escape_string($email);
        $senha = $this->con->real_escape_string($senha);
                                                
        //Busca o usuÃ¡rio e senha
        $resultado = $this->con->query("SELECT * FROM Usuario WHERE Senha = '$senha' and Email = '$email'");
                           
        //Se nenhuma linha foi achada, nao existe o usuario
        if($resultado->num_rows == 0){
                        
            $retorno = false;
             
        }
        else{
                
            //Pega a linha afetada
            while($linhas = $resultado->fetch_assoc()){
                
                $usuario->setId_usuario($linhas["id_Usuario"]);
                $usuario->setNome($linhas["Nome"]);
                $usuario->setIdade($linhas["Idade"]);
                            
            }
                            
            $retorno = true;
                            
         }
        
        return $retorno;
        
    }
    
    public function Registrar($usuario){
        
        $nome = $this->con->real_escape_string($usuario->getNome());
        $email = $this->con->real_escape_string($usuario->getEmail());
        $senha = $this->con->real_escape_string($usuario->getSenha());
        $idade = $this->con->real_escape_string($usuario->getIdade());
        
        $sql = "INSERT INTO Usuario(Nome,Email,Senha,Idade) VALUES('$nome','$email','$senha',$idade)";
        
        if($this->con->query($sql)){
                        
            return true;
            
        }
        else{
            
            return false;
            
        }
        
    }
    
    public function Inserir_Postagem($postagem){
        
        $titulo = $this->con->real_escape_string($postagem->getTitulo());
        $genero = $this->con->real_escape_string($postagem->getGenero());
        $texto = $this->con->real_escape_string($postagem->getTexto());
        $data = $this->con->real_escape_string($postagem->getData());
        $id_usuario = $this->con->real_escape_string($postagem->getId_usuario());
        $curtidas = $this->con->real_escape_string($postagem->getCurtidas());
        
        $sql = "INSERT INTO Postagem (Titulo,Texto,Dia,Curtidas,id_Usuario,id_Genero) VALUES('$titulo','$texto','$data',$curtidas,$id_usuario,'$genero')";
                     
        if($this->con->query($sql)){
                        
            //Retorna o id da postagem
            $sql = "SELECT id_Postagem FROM Postagem WHERE Texto = '$texto'";
            
            $resultado = $this->con->query($sql);
            $linhas = $resultado->fetch_assoc();
            
            return $linhas["id_Postagem"];
            
        }    
        else{
            
            #Se nÃ£o tiver, retorna menos 1 que serÃ¡ tratado depois
            return -1;
                        
        }
        
    }
    
    public function Inserir_Imagens($imagens, $id_postagem){
        
        for($a = 0; $a < count($imagens); $a++){
            
            $code = $this->con->real_escape_string($imagens[$a]);
            $id = $this->con->real_escape_string($id_postagem);
            
            $sql = "INSERT INTO Imagem(id_Postagem,Link) VALUES('$id','$code')";
            
            if($this->con->query($sql)){
                
                echo "Postagem inserida com sucesso";
                                
            }
            else{
                
                echo "Erro ao inserir imagem " . $a;
                
            }
            
        }
                        
    }
        
    public function Pegar_Postagens($quantidade){
                
        $quantidade_db = $this->con->query("SELECT COUNT(*) as total FROM Postagem");
        $row = $quantidade_db->fetch_assoc();
        
        $quantidade_no_banco = $row["total"];
        
        //Confere se tem postagens suficiente
        if($quantidade > $quantidade_no_banco){
            
            $quantidade = $quantidade_no_banco;
                        
        }
        
        $postagens = array();
                
        //Pega uma determinada quantidade de postagens e as mais recentes
        $sql = "SELECT * FROM Postagem ORDER BY Dia DESC";
        
        //Resultado da busca
        $resultado = NULL;
        
        if($resultado = $this->con->query($sql)){
                                    
            //Contador de postagens
            $cont = 0;
                        
            //Pega as postagens
            while($linhas = $resultado->fetch_assoc()){
            
                 //Pega os valores
                $postagens[] = new Postagem(); 
                                            
                $postagens[$cont]->setTitulo($linhas["Titulo"]);
                $postagens[$cont]->setId_postagem($linhas["id_Postagem"]);
                $postagens[$cont]->setTexto($linhas["Texto"]);
                $postagens[$cont]->setData($linhas["Dia"]);
                $postagens[$cont]->setCurtidas($linhas["Curtidas"]);
                $postagens[$cont]->setId_usuario($linhas["id_Usuario"]);
                $postagens[$cont]->setGenero($linhas["id_Genero"]);
                
                //Pega as imagens
                $sql = "SELECT Link FROM Imagem WHERE id_Postagem = " . $postagens[$cont]->getId_postagem();
                
                //Aqui fica o resultado da pesquisa das imagens
                $resultado2 = $this->con->query($sql);    
                
                //Array com as imagens
                $array_img = array();
                
                while($lista_img = $resultado2->fetch_assoc()){
                                        
                    $array_img[] = $lista_img["Link"];
                                                            
                }
                                                
                //O objeto recebe o array de imagens
                $postagens[$cont]->setImagens($array_img);
                                
                //Confere o limite
                if($cont < $quantidade){
                                        
                    $cont++;   
                    
                }
                else{
                    
                    break;
                    
                }
                                
            }
                        
        }
        else{
            
            echo "Erro ao buscar postagens";
            
        }
        
        return $postagens;
                        
    }
    
    public function Curtida($id){
        
        $tabela =  $this->con->real_escape_string($id);
        
        $sql = "UPDATE Postagem SET Curtidas = Curtidas+1 WHERE id_Postagem = $tabela";
        
        if($this->con->query($sql)){
            
            echo "Postagem curtida";
            
        } 
        else{
            
            echo "Erro ao curtir postagem";
            
        }
        
    }
    
    public function Excluir_Postagem($id){
        
        $tabela = $this->con->real_escape_string($id);
                
        $sql = "DELETE FROM Imagem WHERE id_Postagem = $tabela";
                  
        $this->con->query($sql);
                
        $sql = "DELETE FROM Postagem WHERE id_Postagem = $tabela";
        
        $this->con->query($sql);
        
        echo "Postagem excluida";
                    
    }
    
}

?>
