'use strict';

/* Controllers */

var app = angular.module('app', []);

app.controller('myCtrl',['$scope','$http', function($scope, $http) {
  $scope.nomeLeilao = "Leilão Online";
  $scope.descricaoLeilao = "Os melhores produtos, com os menores lances!";
  $scope.email = "";
  $scope.senha = "";
  $scope.cadastrarComo = "Cadastrar Como:";
  $scope.situacao = "Login";
  $scope.conexao = "";
  $scope.cadastrarCliente = true;
  $scope.cadastrarSocio = true;
  $scope.nomeUsuario = "Visitante";
  $scope.qtdLance = "5";
  $scope.valorLance = "0";
  
  $http.post('../listarProdutos').success(function(produto){
    $scope.produto = produto;
  });
  
  $http.post('../listarTodosProdutos').success(function(produto){
	    $scope.todosProdutos = produto;
  });
  
  $http.post('../listarCliente').success(function(cliente){
    $scope.editar = cliente;
  });
  
  $http.post('../findAllUsers').success(function(data){
	    $scope.todosClientes = data;
  });
  
  $http.post('../findAllSocios').success(function(data){
	    $scope.todosSocios = data;
  });
  
  $http.post('../listarSocio').success(function(data){
    $scope.socio = data;
  });
  
  $http.post('../isConnected').success(function(data){
	  $scope.conexao = data;
  });
  
  $http.post('../listarLance').success(function(data){
	  console.log(data);
	  $scope.lance = data;
  });
   
  $http.post('../nomeUsuario').success(function(data){
  	$scope.nomeUsuario = data;
  });
}]);