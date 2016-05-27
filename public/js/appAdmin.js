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
        templateUrl: '../public/partials/paginaInicial.html',
        controller: 'myCtrl'
      }).
      otherwise({
        redirectTo: '/home'
      });
  }]);