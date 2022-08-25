<?php

include_once "Conexao_Banco.php";

class Login {

    public function Logar($usuario){
        
        $conexao = new Conexao_Banco();
         
        if($conexao->Logar($usuario)){
            
            $this->Codificar_Json($usuario);
        
        }
        else{
            
            echo "Usuario ou senha incorreto";
            
        }
                       
    }
    
    private function Codificar_Json($usuario){
                
        $json_array = array(
            
            "status" => true,
            "nome" => $usuario->getNome(),
            "email" => $usuario->getEmail(),
            "senha" => $usuario->getSenha(),
            "idade" => $usuario->getIdade(),
            "id_usuario" => $usuario->getId_usuario()
            
        );
                        
        $json = json_encode($json_array);
                       
        echo $json;
        
    }

    
}

?>
