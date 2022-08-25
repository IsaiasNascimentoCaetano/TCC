<?php

include_once "Conexao_Banco.php";

class Curtida {

    public function Curtir($id_postagem){
        
        $banco = new Conexao_Banco();
        $banco->Curtida($id_postagem);
                
    }
    
}
?>