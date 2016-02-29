angular.module('diggerApp.controllers',[]).controller('ServidorListController',function($scope,$state,popupService,$window,Servidor){

    $scope.servidores=Servidor.query();

    $scope.deleteServidor=function(servidor){
        if(popupService.showPopup('Really delete this?')){
            servidor.$delete(function(){
                $window.location.href='';
            });
        }
    }

}).controller('ServidorViewController',function($scope,$stateParams,Servidor){

    $scope.servidor = Servidor.get({id:$stateParams.id});
    $scope.aplicacoes = Servidor.aplicacoes({id:$stateParams.id});

}).controller('ServidorCreateController',function($scope,$state,$stateParams,Servidor){

    $scope.servidor=new Servidor();

    $scope.addServidor=function(){
        $scope.servidor.$save(function(){
            $state.go('servidores');
        });
    }

}).controller('ServidorEditController',function($scope,$state,$stateParams,Servidor){

    $scope.updateServidor=function(){
        $scope.servidor.$update(function(){
            $state.go('servidores');
        });
    };

    $scope.loadServidor=function(){
        $scope.servidor=Servidor.get({id:$stateParams.id});
    };

    $scope.loadServidor();
});