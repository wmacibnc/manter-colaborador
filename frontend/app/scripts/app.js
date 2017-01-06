'use strict';

angular.module('ngdemoApp', [
  'ngdemoApp.services',
  'ngdemoApp.controllers'
  ])
.config(function ($routeProvider, $httpProvider) {
  // $routeProvider.when('/dummy', {templateUrl: 'views/dummy.html', controller: 'DummyCtrl'});
  // $routeProvider.when('/user-list', {templateUrl: 'views/user-list.html', controller: 'UserListCtrl'});
  // $routeProvider.when('/user-detail/:id', {templateUrl: 'views/user-detail.html', controller: 'UserDetailCtrl'});
  // $routeProvider.when('/user-creation', {templateUrl: 'views/user-creation.html', controller: 'UserCreationCtrl'});

  $routeProvider.when('/colaboradores', {templateUrl: 'views/colaboradores-view.html', controller: 'ColaboradoresCtrl'});
  $routeProvider.when('/visualizar-colaborador', {templateUrl: 'views/visualizar-colaborador-view.html', controller: 'VisualizarColaboradorCtrl'});
  $routeProvider.when('/alterar-colaborador', {templateUrl: 'views/alterar-colaborador-view.html', controller: 'AlterarColaboradorCtrl'});
  $routeProvider.when('/novo-colaborador', {templateUrl: 'views/novo-colaborador-view.html', controller: 'CadastrarColaboradorCtrl'});
  $routeProvider.when('/login', {templateUrl: 'views/login-view.html', controller: 'LoginCtrl'});

  $routeProvider.otherwise({redirectTo: '/colaboradores'});

    /* CORS... */
  /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
