<?php

class Usuario {

    private $nome;
    private $email;
    private $senha;
    private $idade;
    private $id_usuario;
    
    public function getNome() {
        
        return $this->nome;
        
    }

    public function getEmail() {
        
        return $this->email;
        
    }

    public function getSenha() {
        
        return $this->senha;
        
    }

    public function getIdade() {
        
        return $this->idade;
        
    }

    public function getId_usuario() {
        
        return $this->id_usuario;
        
    }

    public function setNome($nome) {
        
        $this->nome = $nome;
        
    }

    public function setEmail($email) {
        
        $this->email = $email;
        
    }

    public function setSenha($senha) {
        
        $this->senha = $senha;
        
    }

    public function setIdade($idade) {
        
        $this->idade = $idade;
        
    }

    public function setId_usuario($id_usuario) {
        
        $this->id_usuario = $id_usuario;
        
    }
        
}

?>