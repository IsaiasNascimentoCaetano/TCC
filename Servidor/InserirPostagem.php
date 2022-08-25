<?php

include_once "Conexao_Banco.php";
include_once "Postagem.php";

class Inserir_Postagem {

    private $postagem;
    private $banco;
    
    public function __construct(){
        
        $this->postagem = new Postagem();
        
    }
    
    public function getPostagem() {
        
        return $this->postagem;
        
    }

    public function setPostagem($postagem) {
        
        $this->postagem = $postagem;
        
    }

    public function Decodificar_Json($json){
                
        $string_js = json_decode($json);
                
        $this->postagem->setTitulo($string_js->{"titulo"});
        $this->postagem->setGenero($string_js->{"genero"});
        $this->postagem->setTexto($string_js->{"texto"});
        $this->postagem->setImagens($string_js->{"imagens"});
        $this->postagem->setId_usuario($string_js->{"id_usuario"});
                         
        date_default_timezone_set('America/Los_Angeles');
        //Pega a data
        $data = date("Y/m/d");	
	    $data = date("Y-m-d",strtotime(str_replace('/','-',$data)));
        $this->postagem->setData($data);
        $this->postagem->setCurtidas(1);
                                              
    }
    
    public function Inserir_Postagem(){
        
        $this->banco = new Conexao_Banco();
        
        $resultado = $this->banco->Inserir_Postagem($this->postagem);
        
        if($resultado != -1){
                         
            $this->Inserir_Imagem($resultado);
            
        }
         
    }
    
    private function Inserir_Imagem($id){
        
        $this->banco->Inserir_Imagens($this->postagem->getImagens(), $id); 
        
    }
    
}

?>