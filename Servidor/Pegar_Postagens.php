<?php

include_once "Conexao_Banco.php";

class Pegar_Postagens {

    private $postagem = array();
    private $quantidade = 0;
    private $banco = NULL;
    
    public function __construct($quantidade){
        
        $this->quantidade = $quantidade;
        
    }
    
    public function getPostagem() {
        return $this->postagem;
    }

    public function setPostagem($postagem) {
        $this->postagem = $postagem;
    }

    public function Pegar_Postagens(){
        
        $this->banco = new Conexao_banco();
        $this->postagem = $this->banco->Pegar_Postagens($this->quantidade);
        
        echo $this->Codificar_Json(); 
        
    }    
    
    public function Codificar_Json(){
        
        $json_final = array();
               
        $cont = 0;
                
        //Cria o json com as postagens
        for($a = 0; $a < sizeof($this->postagem); $a++){
        
            $postagens = array();
            
            //Cria o array de imagens primeiro
            $imagens_json = array();
            $imagens = array();
            
            $imagens = $this->postagem[$a]->getImagens();
            
            for($b = 0; $b < sizeof($this->postagem[$a]->getImagens()); $b++){
                
                $imagens_json["imagem$b"] = $imagens[$b]; 
                                
            }
                                                            
            $postagens["titulo"] = $this->postagem[$a]->getTitulo();
            $postagens["id_postagem"] = $this->postagem[$a]->getId_postagem();
            $postagens["texto"] = $this->postagem[$a]->getTexto();
            $postagens["dia"] = $this->postagem[$a]->getData();
            $postagens["curtidas"] = $this->postagem[$a]->getCurtidas();
            $postagens["id_usuario"] = $this->postagem[$a]->getId_usuario();
            $postagens["genero"] = $this->postagem[$a]->getGenero();
            $postagens["imagens"] = $imagens_json;
                        
            $json_final["postagem$cont"] = $postagens;
            
            $cont++;
                                    
        }
            
        $json_final["quantidade"] = sizeof($this->postagem);    
                           
        return json_encode($json_final);
        
    }
    
}

?>