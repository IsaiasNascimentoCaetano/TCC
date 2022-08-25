<?php

include_once "Conexao_Banco.php";

class Excluir {

    public function Excluir_Postagem($id_postagem){
        
        $banco = new Conexao_Banco();
        $banco->Excluir_Postagem($id_postagem);
        
    }
    
}

?>