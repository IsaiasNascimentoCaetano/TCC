<?php

include_once "Conexao_Banco.php";
include_once "Usuario.php";

class Registro {

    private $usuario;
    
    public function getUsuario() {

        return $this->usuario;
            
    }
    
    public function setUsuario($usuario) {

        $this->usuario = $usuario;
            
    }

    public function Decodificar_Json($json){
        
        $obj = json_decode($json);
       
        $this->usuario = new Usuario();                         
        $this->usuario->setNome($obj->{"nome"});
        $this->usuario->setIdade($obj->{"idade"});
        $this->usuario->setEmail($obj->{"email"});
        $this->usuario->setSenha($obj->{"senha"});
        
        $this->Registrar();
        
    }
    
    private function Registrar(){
        
        $banco = new Conexao_Banco();
        
        if($banco->Registrar($this->usuario)){
            
            echo "Usuario inserido com sucesso";
            
        }
        else{
            
            echo "Erro ao inserir usuário";
            
        }
        
    }
    
}

?>
