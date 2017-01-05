'use strict';

/* Controllers */

var app = angular.module('ngdemoApp.controllers', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
  $rootScope.$on('$viewContentLoaded', function () {
    $templateCache.removeAll();
  });
});


app.controller('DummyCtrl', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
  $scope.bla = 'bla from controller';
  DummyFactory.query({}, function (data) {
    $scope.foo = data.firstName;
  })
}]);

app.controller('UserListCtrl', ['$scope', 'UsersFactory', 'UserFactory', '$location',
  function ($scope, UsersFactory, UserFactory, $location) {

    /* callback for ng-click 'editUser': */
    $scope.editUser = function (userId) {
      $location.path('/user-detail/' + userId);
    };

    /* callback for ng-click 'deleteUser': */
    $scope.deleteUser = function (userId) {
      UserFactory.delete({ id: userId });
      $scope.users = UsersFactory.query();
    };

    /* callback for ng-click 'createUser': */
    $scope.createNewUser = function () {
      $location.path('/user-creation');
    };

    $scope.users = UsersFactory.query();
  }]);

app.controller('ColaboradoresCtrl', ['$scope', 'ColaboradoresViewFactory','$location','$rootScope',
  function ($scope, ColaboradoresViewFactory, $location, $rootScope) {
    $scope.page = 1;
    var paginacaoDTO = {};
    paginacaoDTO.page = $scope.page++;
    paginacaoDTO.filtro = $scope.filtro;

    $scope.colaboradores = ColaboradoresViewFactory.query(paginacaoDTO);

    $scope.visualizarColaborador = function (selecionado){
      $rootScope.colaboradorSelecionado = selecionado;
      $location.path('/visualizar-colaborador');
    }

    $scope.novoColaborador = function (){
      $location.path('/novo-colaborador');
    }

}]);

app.controller('CadastrarColaboradorCtrl', ['$scope', 'ColaboradorCadastroFactory', '$location',
  function ($scope, ColaboradorCadastroFactory, $location) {
    
     $scope.salvarDadosCadastro = function (){
      ColaboradorCadastroFactory.salvarColaborador($scope.colaboradorSelecionado);
      $location.path('/colaboradores');
    };

}]);

app.controller('VisualizarColaboradorCtrl', ['$scope', 'ColaboradorExcluirFactory', '$location', '$rootScope',
  function ($scope, ColaboradorExcluirFactory, $location, $rootScope) {
    
    $scope.colaboradorSelecionado = $rootScope.colaboradorSelecionado;

    $scope.alterarColaborador = function (){
      $location.path('/alterar-colaborador');
    };

    $scope.excluirColaborador = function(id){
      ColaboradorExcluirFactory.excluirColaborador({ id: id });
      $location.path('/colaboradores');
    }

}]);

app.controller('AlterarColaboradorCtrl', ['$scope', 'ColaboradorAtualizarFactory', '$location', '$rootScope',
  function ($scope, ColaboradorAtualizarFactory, $location, $rootScope) {
    
    $scope.colaboradorSelecionado = $rootScope.colaboradorSelecionado;

    $scope.salvarDadosAlteracao = function (){
      ColaboradorAtualizarFactory.salvarColaborador($scope.colaboradorSelecionado);
      $location.path('/colaboradores');
    };

}]);

app.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', '$location',
  function ($scope, $routeParams, UserFactory, $location) {

    /* callback for ng-click 'updateUser': */
    $scope.updateUser = function () {
      UserFactory.update($scope.user);
      $location.path('/user-list');
    };

    /* callback for ng-click 'cancel': */
    $scope.cancel = function () {
      $location.path('/user-list');
    };

    $scope.user = UserFactory.show({id: $routeParams.id});
  }]);

app.controller('UserCreationCtrl', ['$scope', 'UsersFactory', '$location',
  function ($scope, UsersFactory, $location) {

    /* callback for ng-click 'createNewUser': */
    $scope.createNewUser = function () {
      UsersFactory.create($scope.user);
      $location.path('/user-list');
    }
  }]);
