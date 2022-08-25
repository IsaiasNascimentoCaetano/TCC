<?php
    
class Postagem {

    private $titulo;
    private $genero;
    private $texto;
    private $imagens = array();
    //A data Ã© pega pelo php
    private $data;
    private $id_usuario;
    private $curtidas;
    private $id_postagem;
    
    public function __construct(){
        
        //$this->imagens = array();
        
    }
    
    public function getTitulo() {
        
        return $this->titulo;
        
    }

    public function getGenero() {
        
        return $this->genero;
        
    }

    public function getTexto() {
        
        return $this->texto;
        
    }

    public function getImagens() {
        
        return $this->imagens;
        
    }

    public function getData() {
        
        return $this->data;
        
    }

    public function getId_usuario() {
        
        return $this->id_usuario;
        
    }

    public function getCurtidas() {
        
        return $this->curtidas;
        
    }

    public function getId_postagem() {
        
        return $this->id_postagem;
        
    }

    public function setTitulo($titulo) {
        
        $this->titulo = $titulo;
        
    }

    public function setGenero($genero) {
        
        $this->genero = $genero;
        
    }

    public function setTexto($texto) {
        
        $this->texto = $texto;
        
    }

    public function setImagens($imagens) {
        
        $this->imagens = $imagens;
        
    }

    public function setData($data) {
        
        $this->data = $data;
        
    }

    public function setId_usuario($id_usuario) {
        
        $this->id_usuario = $id_usuario;
        
    }

    public function setCurtidas($curtidas) {
        
        $this->curtidas = $curtidas;
        
    }

    public function setId_postagem($id_postagem) {
        
        $this->id_postagem = $id_postagem;
        
    }
    
}
?>