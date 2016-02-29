/**
 * Created by Sandeep on 01/06/14.
 */

angular.module('diggerApp.services',[]).factory('Servidor',function($resource){
    return $resource('http://localhost:8080/digger/api/servidores/:id/:category',{id:'@id'},{
        update: {
            method: 'PUT'
        },
        aplicacoes: {
            params: {category: 'aplicacoes'},
            method: 'GET',
            isArray: true
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});