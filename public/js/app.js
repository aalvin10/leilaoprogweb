'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'app'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: '../public/partials/produtos.html',
        controller: 'myCtrl'
      }).
      when('/cadastrar', {
        templateUrl: '../public/partials/cadastro.html',
        controller: 'myCtrl'
      }).
      when('/termos', {
        templateUrl: '../public/partials/termos.html',
        controller: 'myCtrl'
      }).
      when('/proxitens', {
        templateUrl: '../public/partials/proxitens.html',
        controller: 'myCtrl'
      }).
      when('/arrematados', {
        templateUrl: '../public/partials/arrematados.html',
        controller: 'myCtrl'
      }).
      when('/cadastrar&objeto', {
        templateUrl: '../public/partials/cadastrarObjeto.html',
        controller: 'myCtrl'
      }).
      when('/configuracoes',{
      	templateUrl: '../public/partials/configuracoes.html',
      	controller: 'myCtrl'
      }).
      when('/editarUsuario',{
      	templateUrl: '../public/partials/editarConta.html',
      	controller: 'myCtrl'
      }).
      when('/editarSocio',{
      	templateUrl: '../public/partials/editarSocio.html',
      	controller: 'myCtrl'
      }).
      when('/editarProduto',{
        	templateUrl: '../public/partials/modificarProduto.html',
        	controller: 'myCtrl'
        }).
      when('/home',{
        templateUrl: '../public/partials/home.html',
        controller: 'myCtrl'
      }).
      when('/comprarLance',{
          templateUrl: '../public/partials/comprarLance.html',
          controller: 'myCtrl'
        }).
      otherwise({
        redirectTo: '/home'
      });
  }]);