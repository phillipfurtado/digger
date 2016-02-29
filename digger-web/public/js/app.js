/**
 * Created by Sandeep on 01/06/14.
 */

angular.module('diggerApp',['ui.router','ngResource','diggerApp.controllers','diggerApp.services']);

angular.module('diggerApp').config(function($stateProvider,$httpProvider){
    $stateProvider.state('servidores',{
        url:'/servidores',
        templateUrl:'partials/servidores.html',
        controller:'ServidorListController'
    }).state('viewServidor',{
       url:'/servidores/:id/view',
       templateUrl:'partials/servidor-view.html',
       controller:'ServidorViewController'
    }).state('newServidor',{
        url:'/servidores/new',
        templateUrl:'partials/servidor-add.html',
        controller:'ServidorCreateController'
    }).state('editServidor',{
        url:'/servidores/:id/edit',
        templateUrl:'partials/servidor-edit.html',
        controller:'ServidorEditController'
    });
}).run(function($state){
   $state.go('servidores');
});