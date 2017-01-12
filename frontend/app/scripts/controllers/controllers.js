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


app.controller('LoginCtrl', ['$scope', 'UsuarioFactory','$location', function ($scope, UsuarioFactory, $location) {

  $scope.efetuarLogin = function (){
    UsuarioFactory.query($scope.usuario, function (data) {
      if(data){
        alert('sucesso');
        $location.path('/colaboradores');
      }else{
        alert('erro');
        $location.path('/login');
      }

    })
  }

}]);

app.controller('ColaboradoresCtrl', ['$scope', 'ColaboradoresViewFactory','$location','$rootScope',
  function ($scope, ColaboradoresViewFactory, $location, $rootScope) {
    $scope.page = 1;
    var paginacaoDTO = {};
    paginacaoDTO.page = $scope.page++;
    paginacaoDTO.filtro = $scope.filtro;

    $scope.colaboradores = ColaboradoresViewFactory.query(paginacaoDTO);

    var resultado = null;

    $scope.botaoMais = function (){
      paginacaoDTO = {};
      paginacaoDTO.page = $scope.page++;
      paginacaoDTO.filtro = $scope.filtro;
      resultado = ColaboradoresViewFactory.query(paginacaoDTO);

      setTimeout(function(){ $scope.dadosBotao(); }, 6000);
    }

    $scope.dadosBotao = function (){
      $scope.colaboradores = $scope.colaboradores.concat(resultado);
    }

    $scope.botaoMaisPesquisar = function (){
      paginacaoDTO = {};
      paginacaoDTO.page = 1;
      paginacaoDTO.filtro = $scope.filtro;
      $scope.colaboradores = ColaboradoresViewFactory.query(paginacaoDTO);
    }

    $scope.visualizarColaborador = function (selecionado){
      $rootScope.colaboradorSelecionado = selecionado;
      $location.path('/visualizar-colaborador');
    }

    $scope.novoColaborador = function (){
      $location.path('/novo-colaborador');
    }

  }]);

app.controller('CadastrarColaboradorCtrl', ['$scope', 'ColaboradorCadastroFactory', '$location', 'CargoFactory','DepartamentoFactory', 'TipoContatoFactory',
  function ($scope, ColaboradorCadastroFactory, $location, CargoFactory, DepartamentoFactory, TipoContatoFactory) {

    $scope.colaboradorSelecionado = {};
    $scope.colaboradorSelecionado.cargo = {};
    $scope.colaboradorSelecionado.departamento = {};
    $scope.contatos = new Array();
    $scope.cargos = CargoFactory.cargos();
    $scope.departamentos = DepartamentoFactory.departamentos();
    $scope.tiposContato = TipoContatoFactory.tiposContato();

    // $scope.adiconarLista = function(){
    //   $scope.listaContato.push(1);
    // }

    $scope.salvarDadosCadastro = function (){
      $scope.colaboradorSelecionado.contatos = $scope.contatos;
      ColaboradorCadastroFactory.salvarColaborador($scope.colaboradorSelecionado);
      $location.path('/colaboradores');
    };

    var inicial = {
      latitude: -15.7942287,
      longitude: -47.882165799999996
    };
    var divDoMapa = document.getElementById("map_canvas")

    var opcoes = { 
      center: new google.maps.LatLng(inicial.latitude, inicial.longitude)
      , zoom: 10
      , mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.inicializar = function () {
      $scope.geocoder = new google.maps.Geocoder();
      $scope.map = new google.maps.Map(divDoMapa, opcoes);
      $scope.adicionarContato();
    }

    var constroiContato = function(){
      var contato = {
        id : null,
        descricao : '',

        tipoContato : {
          id : null,
          descricao: null
        }
      };      
      return contato;
    };


    $scope.adicionarContato = function(){
      var contato = constroiContato();
      $scope.contatos.push(contato);
    };

    var imagens = {
     muitoBom: 'http://i.imgur.com/bFnWq8k.png', 
     bom: 'http://i.imgur.com/VnlbIoL.png', 
     medio: 'http://i.imgur.com/eNAvIvr.png', 
     ruim: 'http://i.imgur.com/uCRXqdV.png', 
     pessimo: 'http://i.imgur.com/biRJBNL.png'
   }

   var marcadores = [];

   $scope.criaMarcador = function(marcador, mapa) {
    var posicao = new google.maps.LatLng(marcador.latitude, marcador.longitude);
    var opcoes = {
      position: posicao
      , title: marcador.titulo
      , animation: google.maps.Animation.DROP
      , icon:{
        url: marcador.imagem || 'http://i.imgur.com/bFnWq8k.png'
        , scaledSize: new google.maps.Size(50, 50)
      }
      , map: mapa
    }

    var novoMarcador = new google.maps.Marker(opcoes);
    marcadores.push(novoMarcador);
    $scope.map.setCenter(novoMarcador.position);
  }

  $scope.adiciona = function(){
    var marcador = {
      latitude: -25.425777, 
      longitude: -49.3335829, 
      titulo: 'Novo marcador', 
      imagem: imagens.muitoBom
    }
    $scope.criaMarcador(marcador, $scope.map);
  }

  var imgArray = [imagens.muitoBom, imagens.bom, imagens.medio, imagens.ruim, imagens.pessimo];

  $scope.converte = function (){
    var endereco = document.getElementById('endereco').value;
    var seletor = document.getElementById("avaliacao");
    var avaliacao = 0;
    var imagemMarcador = imgArray[avaliacao];
    $scope.converteEndereco(endereco, imagemMarcador);
    $scope.map.setZoom(14);
  }

  $scope.converteEndereco = function (endereco, avaliacao) {
    $scope.geocoder.geocode( { 'address': endereco}, function(resultado, status) {
      if (status == google.maps.GeocoderStatus.OK) {

        var latitude = resultado[0].geometry.location.lat();
        var longitude = resultado[0].geometry.location.lng();

        $scope.colaboradorSelecionado.latitude = latitude;
        $scope.colaboradorSelecionado.longitude = longitude;

        var marcador = {
          latitude : latitude,
          longitude : longitude,
          titulo: 'Novo marcador',
          imagem: avaliacao
        }
        $scope.criaMarcador(marcador, $scope.map);
      } else {
        alert('Erro ao converter endereço: ' + status);
      }
    });
  }

  $scope.inicializar();


}]);

app.controller('VisualizarColaboradorCtrl', ['$scope', 'ColaboradorExcluirFactory', '$location', '$rootScope',
  function ($scope, ColaboradorExcluirFactory, $location, $rootScope) {

    $scope.colaboradorSelecionado = $rootScope.colaboradorSelecionado;

    $scope.alterarColaborador = function (){
      $location.path('/alterar-colaborador');
    };

    $scope.excluirColaborador = function(identificador){
      ColaboradorExcluirFactory.excluirColaborador({ id: identificador });
      $location.path('/colaboradores');
    }


    var latitude = $scope.colaboradorSelecionado.latitude;
    var longitude = $scope.colaboradorSelecionado.longitude;

    var inicial = {
      latitude: latitude,
      longitude: longitude
    };

    var divDoMapa = document.getElementById("map_canvas")

    var opcoes = { 
      center: new google.maps.LatLng(inicial.latitude, inicial.longitude)
      , zoom: 10
      , mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.inicializar = function () {
      $scope.geocoder = new google.maps.Geocoder();
      $scope.map = new google.maps.Map(divDoMapa, opcoes);
    }

    var imagens = {
     muitoBom: 'http://i.imgur.com/bFnWq8k.png', 
     bom: 'http://i.imgur.com/VnlbIoL.png', 
     medio: 'http://i.imgur.com/eNAvIvr.png', 
     ruim: 'http://i.imgur.com/uCRXqdV.png', 
     pessimo: 'http://i.imgur.com/biRJBNL.png'
   }

   var marcadores = [];

   $scope.criaMarcador = function(marcador, mapa) {
    var posicao = new google.maps.LatLng(marcador.latitude, marcador.longitude);
    var opcoes = {
      position: posicao
      , title: marcador.titulo
      , animation: google.maps.Animation.DROP
      , icon:{
        url: marcador.imagem || 'http://i.imgur.com/bFnWq8k.png'
        , scaledSize: new google.maps.Size(50, 50)
      }
      , map: mapa
    }

    var novoMarcador = new google.maps.Marker(opcoes);
    marcadores.push(novoMarcador);
    $scope.map.setCenter(novoMarcador.position);
  }

  $scope.adiciona = function(){
    var marcador = {
      latitude: -25.425777, 
      longitude: -49.3335829, 
      titulo: 'Novo marcador', 
      imagem: imagens.muitoBom
    }
    $scope.criaMarcador(marcador, $scope.map);
  }

  var imgArray = [imagens.muitoBom, imagens.bom, imagens.medio, imagens.ruim, imagens.pessimo];

  $scope.converte = function (){
    var endereco = document.getElementById('endereco').value;
    var seletor = document.getElementById("avaliacao");
    var avaliacao = 0;
    var imagemMarcador = imgArray[avaliacao];
    $scope.converteEndereco(endereco, imagemMarcador);
    $scope.map.setZoom(14);
  }

  $scope.inicializar();

}]);

app.controller('AlterarColaboradorCtrl', ['$scope', 'ColaboradorAtualizarFactory', '$location', '$rootScope', 'CargoFactory','DepartamentoFactory', 'TipoContatoFactory',
  function ($scope, ColaboradorAtualizarFactory, $location, $rootScope, CargoFactory,DepartamentoFactory, TipoContatoFactory) {

    $scope.colaboradorSelecionado = $rootScope.colaboradorSelecionado;

    $scope.contatos = new Array();
    $scope.cargos = CargoFactory.cargos();
    $scope.departamentos = DepartamentoFactory.departamentos();
    $scope.tiposContato = TipoContatoFactory.tiposContato();



    $scope.salvarDadosAlteracao = function (){
      ColaboradorAtualizarFactory.alterarColaborador($scope.colaboradorSelecionado);
      $location.path('/colaboradores');
    };


    var inicial = {
      latitude: -15.7942287,
      longitude: -47.882165799999996
    };
    var divDoMapa = document.getElementById("map_canvas")

    var opcoes = { 
      center: new google.maps.LatLng(inicial.latitude, inicial.longitude)
      , zoom: 10
      , mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.inicializar = function () {
      $scope.geocoder = new google.maps.Geocoder();
      $scope.map = new google.maps.Map(divDoMapa, opcoes);
      $scope.adicionarContato();
    }

    var constroiContato = function(){
      var contato = {
        id : null,
        descricao : '',

        tipoContato : {
          id : null,
          descricao: null
        }
      };      
      return contato;
    };


    $scope.adicionarContato = function(){
      var contato = constroiContato();
      $scope.contatos.push(contato);
    };

    var imagens = {
     muitoBom: 'http://i.imgur.com/bFnWq8k.png', 
     bom: 'http://i.imgur.com/VnlbIoL.png', 
     medio: 'http://i.imgur.com/eNAvIvr.png', 
     ruim: 'http://i.imgur.com/uCRXqdV.png', 
     pessimo: 'http://i.imgur.com/biRJBNL.png'
   }

   var marcadores = [];

   $scope.criaMarcador = function(marcador, mapa) {
    var posicao = new google.maps.LatLng(marcador.latitude, marcador.longitude);
    var opcoes = {
      position: posicao
      , title: marcador.titulo
      , animation: google.maps.Animation.DROP
      , icon:{
        url: marcador.imagem || 'http://i.imgur.com/bFnWq8k.png'
        , scaledSize: new google.maps.Size(50, 50)
      }
      , map: mapa
    }

    var novoMarcador = new google.maps.Marker(opcoes);
    marcadores.push(novoMarcador);
    $scope.map.setCenter(novoMarcador.position);
  }

  $scope.adiciona = function(){
    var marcador = {
      latitude: -25.425777, 
      longitude: -49.3335829, 
      titulo: 'Novo marcador', 
      imagem: imagens.muitoBom
    }
    $scope.criaMarcador(marcador, $scope.map);
  }

  var imgArray = [imagens.muitoBom, imagens.bom, imagens.medio, imagens.ruim, imagens.pessimo];

  $scope.converte = function (){
    var endereco = document.getElementById('endereco').value;
    var seletor = document.getElementById("avaliacao");
    var avaliacao = 0;
    var imagemMarcador = imgArray[avaliacao];
    $scope.converteEndereco(endereco, imagemMarcador);
    $scope.map.setZoom(14);
  }

  $scope.converteEndereco = function (endereco, avaliacao) {
    $scope.geocoder.geocode( { 'address': endereco}, function(resultado, status) {
      if (status == google.maps.GeocoderStatus.OK) {

        var latitude = resultado[0].geometry.location.lat();
        var longitude = resultado[0].geometry.location.lng();

        $scope.colaboradorSelecionado.latitude = latitude;
        $scope.colaboradorSelecionado.longitude = longitude;

        var marcador = {
          latitude : latitude,
          longitude : longitude,
          titulo: 'Novo marcador',
          imagem: avaliacao
        }
        $scope.criaMarcador(marcador, $scope.map);
      } else {
        alert('Erro ao converter endereço: ' + status);
      }
    });
  }

  $scope.inicializar();

}]);


angular.module('ng').filter('cut', function () {
  return function (value, wordwise, max, tail) {
    if (!value) return '';

    max = parseInt(max, 10);
    if (!max) return value;
    if (value.length <= max) return value;

    value = value.substr(0, max);
    if (wordwise) {
      var lastspace = value.lastIndexOf(' ');
      if (lastspace != -1) {
                  //Also remove . and , so its gives a cleaner result.
                  if (value.charAt(lastspace-1) == '.' || value.charAt(lastspace-1) == ',') {
                    lastspace = lastspace - 1;
                  }
                  value = value.substr(0, lastspace);
                }
              }

              return value + (tail || ' …');
            };
          });
