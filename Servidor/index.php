<?php
		
	ini_set('display_errors', true); error_reporting(E_ALL);
		
	require_once "Conexao_Banco.php";
	require_once "Usuario.php";
	require_once "Curtida.php";
	require_once "Excluir.php";
	require_once "InserirPostagem.php";
	require_once "Login.php";
	require_once "Pegar_Postagens.php";
	require_once "Postagem.php";
	require_once "Registro.php";	
	
	//Constantes
	define("LOGIN","1");
	define("REGISTRAR","2");
	define("INSERIR_POSTAGEM","3");
	define("PEGAR_POSTAGEM","4");
	define("CURTIR", "5");
	define("EXCLUIR","6");
				
	//Recebe o que vai fazer
	$funcao = $_POST["acao"];	
			
	switch($funcao){
		
		case LOGIN:
		
			$login = new Login();
			$usuario = new Usuario();

			$usuario->setEmail($_POST["email"]);
			$usuario->setSenha($_POST["senha"]);
			
			$login->Logar($usuario);
			
			break;
			
		case REGISTRAR:
		
			$registro = new Registro();
			
			$registro->Decodificar_Json($_POST["registro"]);
			
			break;
			
		case INSERIR_POSTAGEM:
						
			$postagem = new Inserir_Postagem();
			$postagem->Decodificar_Json($_POST["postagem"]);
			$postagem->Inserir_Postagem();
									
			break;
			
		case PEGAR_POSTAGEM:
			
			$postagem = new Pegar_Postagens($_POST["quantidade"]);
			$postagem->Pegar_Postagens();
		
			break;
			
		case CURTIR:
		
			$curtir = new Curtida();
			
			$curtir->Curtir($_POST["id_postagem"]);
			
			break;			
		
		case EXCLUIR:
		
			$excluir = new Excluir();
			
			$excluir->Excluir_Postagem($_POST["id_postagem"]);
			
			break;
		
	}
			
?>