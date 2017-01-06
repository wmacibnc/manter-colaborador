'use strict';

var services = angular.module('ngdemoApp.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.factory('DummyFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/dummy', {}, {
        query: { method: 'GET', params: {} }
    })
});

services.factory('UsersFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/users', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('CargoFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/cargos', {}, {
        cargos: { method: 'GET', isArray: true },
    })
});

services.factory('DepartamentoFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/departamentos', {}, {
        departamentos: { method: 'GET', isArray: true },
    })
});

services.factory('TipoContatoFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/tipos-contato', {}, {
        tiposContato: { method: 'GET', isArray: true },
    })
});

services.factory('ColaboradoresViewFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/lista-colaboradores', {}, {
        query: { method: 'POST', isArray: true}
    })
});

services.factory('ColaboradorAtualizarFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/atualizar-colaborador', {}, {
        alterarColaborador: { method: 'PUT'}
    })
});

services.factory('ColaboradorCadastroFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/salvar-colaborador', {}, {
        salvarColaborador: { method: 'POST'}
    })
});

services.factory('ColaboradorExcluirFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/colaborador/excluir-colaborador/:id', {}, {
        excluirColaborador: { method: 'DELETE', params: {id: '@id'} }
    })
});

services.factory('UserFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/users/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
